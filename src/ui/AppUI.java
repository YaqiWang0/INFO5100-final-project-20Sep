package ui;

import dao.Special;
import dao.Vehicle;
import dao.VehicleModel;
import dto.AbstractPersistent;
import dto.DataPersistence;
import service.IncentiveApi;
import service.IncentiveApiImpl;
import service.InventiveTimeJob;
import service.SalesIsEndedObservable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AppUI extends AppUIAbstract implements Observer {

    private IncentiveApi incentiveApi;
    private JPanel centerPanel;

    // sub panel group
    private IncentiveUI incentiveUI;
    private InventiveTimeJob timejob;

    /* I allocated every vehicle a pop button, so that disabling one
    button will not influence other buttons.
     */
    private JButton popButtons[];
    private SalesIsEndedObservable salesIsEnded;

    public AppUI() {
        incentiveUI = new IncentiveUI();
        timejob = new InventiveTimeJob();
        timejob.addObserver(incentiveUI);
    }

    /**
     * Simulating Case2
     * @return
     */
    @Override
    protected JPanel getCenterPanel() {
        // init panel
        centerPanel = new JPanel(new GridLayout(0, 2));

        if(incentiveApi == null)
            incentiveApi = new IncentiveApiImpl();

        AbstractPersistent dao = new DataPersistence();
        List<Vehicle> vehicles = dao.getAllVehicles();
        // initialize the popButtons.
        popButtons = new JButton[vehicles.size()];

        // add Component to centerPanel
        int count = 0;

        for (Vehicle vehicle: vehicles) {
            VehicleModel vehicleModel = incentiveApi.updateSpecialPrice(vehicle);
            vehicleModel.getSpecial().setEndDate(new Date(new Date().getTime() + 1000* count*10));

            centerPanel.add(new JLabel("Car " + (++count), JLabel.CENTER));
            centerPanel.add(new JLabel(vehicle.getVehicleId()));

            centerPanel.add(new JLabel("Special Price", JLabel.CENTER));
            centerPanel.add(new JLabel(vehicleModel.getSpecialPrice() + ""));

            centerPanel.add(new JLabel("end date", JLabel.CENTER));
            centerPanel.add(new JLabel(vehicleModel.getSpecial().getEndDate() + ""));

            centerPanel.add(new JLabel("", JLabel.CENTER));
//            centerPanel.add(getPopupBtn(vehicleModel.getSpecial()));
            addIncentiveButton(count - 1, vehicleModel.getSpecial());
        }

        return centerPanel;
    }

    private void addIncentiveButton(int index, Special special) {
        salesIsEnded = new SalesIsEndedObservable(index, special.getEndDate());
        salesIsEnded.addObserver(this);

        popButtons[index] = new JButton("Learn About Discount!!!");
        popButtons[index].addActionListener((ActionEvent e) -> {
            timejob.start(special);
            JOptionPane.showConfirmDialog(centerPanel, incentiveUI, "Incentive details",
                    JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            timejob.stop();

//            if (new Date().getTime() > special.getEndDate().getTime()) {
//                popBtn.setText("discount expired");
//                popBtn.setEnabled(false);
//            }
         });
        centerPanel.add(popButtons[index]);
    }

    /**
     * pop-up incentive details ---Case6
     * @param special
     * @return
     */
    private JButton getPopupBtn(Special special) {
        JButton popBtn = new JButton("Learn About Discount!!!");

        popBtn.addActionListener((ActionEvent e) -> {
            timejob.start(special);
            JOptionPane.showConfirmDialog(centerPanel, incentiveUI, "Incentive details",
                    JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            timejob.stop();

            if (new Date().getTime() > special.getEndDate().getTime()) {
                popBtn.setText("discount expired");
                popBtn.setEnabled(false);
            }
        });

        return popBtn;
    }

    @Override
    public void update(Observable o, Object arg) {
        // if sales is ended, change the test of pop-up button and the disable the button.
        if ((boolean) arg) {
            popButtons[((SalesIsEndedObservable) o).getIndex()].setText("Discount expired");
            popButtons[((SalesIsEndedObservable) o).getIndex()].setEnabled(false);
        }
    }
}

