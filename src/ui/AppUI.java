package ui;

import dao.Special;
import dao.Vehicle;
import dao.VehicleModel;
import dto.AbstractPersistent;
import dto.DataPersistence;
import service.IncentiveApi;
import service.IncentiveApiImpl;
import service.CountdownTimeJob;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;

public class AppUI extends AppUIAbstract {

    private IncentiveApi incentiveApi;
    private JPanel centerPanel;

    // sub panel group
    private IncentiveUI incentiveUI;
    private CountdownTimeJob timeJob;

    public AppUI() {
        incentiveUI = new IncentiveUI();
        timeJob = new CountdownTimeJob();
        timeJob.addObserver(incentiveUI);
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

        // add Component to centerPanel
        int count = 0;
        for (Vehicle vehicle: vehicles) {
            VehicleModel vehicleModel = incentiveApi.updateSpecialPrice(vehicle);
            Special special = vehicleModel.getSpecial();

            centerPanel.add(new JLabel("Car " + (++count), JLabel.CENTER));
            centerPanel.add(new JLabel(vehicle.getVehicleId()));

            centerPanel.add(new JLabel("Price " + (count), JLabel.CENTER));
            centerPanel.add(new JLabel(vehicle.getPrice()));

            if (special != null) {
                centerPanel.add(new JLabel("Special Price", JLabel.CENTER));
                centerPanel.add(new JLabel(vehicleModel.getSpecialPrice() + ""));

                Date startDate = special.getStartDate();
                Date endDate = special.getEndDate();
                Date now = new Date();

                centerPanel.add(new JLabel("end date", JLabel.CENTER));
                centerPanel.add(new JLabel(endDate + ""));

                centerPanel.add(new JLabel("", JLabel.CENTER));

                if ((now.after(startDate) && now.before(endDate))
                    || now.equals(startDate) || now.equals(startDate)) {
                    centerPanel.add(getPopupBtn(vehicleModel));
                }
            }

            /// just for easy to look, removable.
            centerPanel.add(new JLabel(""));
            centerPanel.add(new JLabel(""));
        }
        return centerPanel;
    }

    /**
     * pop-up incentive details ---Case6
     * @param vehicleModel
     * @return
     */
    private JButton getPopupBtn(VehicleModel vehicleModel) {
        JButton popBtn = new JButton("Learn About Discount!!!");

        popBtn.addActionListener((ActionEvent e) -> {
            timeJob.start(vehicleModel);
            JOptionPane.showConfirmDialog(centerPanel, incentiveUI, "Incentive details",
                    JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

            // if sales promotion is ended, the button could not be clicked on.
            if (vehicleModel.getSpecial().getEndDate().getTime() < new Date().getTime()) {
                popBtn.setEnabled(false);
                popBtn.setText("Discount Expired");
            }
            timeJob.stop();
        });

        return popBtn;
    }

}