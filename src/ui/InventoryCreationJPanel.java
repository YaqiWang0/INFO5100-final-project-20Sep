package ui;

import dao.BodyType;
import dao.Vehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;

public class InventoryCreationJPanel {
    private JTextField idTextField;
    private JTextField dealerIdTextField;
    private JTextField yearTextField;
    private JTextField brandTextField;
    private JTextField modelTextField;
    private JTextField isNewTextField;
    private JTextField priceTextField;
    private JTextField exteriorColorTextField;
    private JTextField interiorColorTextField;
    private JComboBox  bodyTypeTextField;
    private JTextField featuresTextField;
    private JTextField milesTextField;
    private JTextField imagesTextField;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel CreateJPanel;

    public InventoryCreationJPanel (String dealerId){
        super();
        JFrame frame = new JFrame("Inventory Management");
        frame.setContentPane(CreateJPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Todo add comboBox not yet
        String combox[] = {"CAR","SUV","TRUCK","VAN"};
        bodyTypeTextField = new JComboBox(combox);



        confirmButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String XXX=dealerIdTextField.getText();
                String year=yearTextField.getText();
                String brand=brandTextField.getText();
                String model=modelTextField.getText();

                String isNew=isNewTextField.getText();
                String price=priceTextField.getText();
                //String exteriorColor=exteriorColorTextField.getText();
                //String interiorColor=interiorColorTextField.getText();
                String bodyType=bodyTypeTextField.getSelectedItem().toString();

                //String miles=milesTextField.getText();
                String images=imagesTextField.getText();
                String features=featuresTextField.getText();

                if(isNullOrEmpty(XXX))
                    XXX="null";
                if(isNullOrEmpty(year))
                    year="null";
                if(isNullOrEmpty(brand))
                    brand="null";
                if(isNullOrEmpty(model))
                    model="null";
                if(isNullOrEmpty(isNew))
                    isNew="null";
                if(isNullOrEmpty(price))
                    price="null";
                //if(isNullOrEmpty(exteriorColor))
                //    exteriorColor="null";
                //if(isNullOrEmpty(interiorColor))
                //    interiorColor="null";
                if(isNullOrEmpty(bodyType))
                    bodyType="null";
                //if(isNullOrEmpty(miles))
                //    miles="null";
                if(isNullOrEmpty(images))
                    images="null";
                if(isNullOrEmpty(features))
                    features="null";

                try (FileWriter fw = new FileWriter(InventoryManagementJPanel.url+dealerId, true);
                     BufferedWriter bw = new BufferedWriter(fw)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(idTextField.getText());
                    sb.append('~');
                    sb.append(dealerId);
                    sb.append('~');
                    sb.append(isNew);
                    sb.append('~');
                    sb.append(year);
                    sb.append('~');
                    sb.append(brand);
                    sb.append('~');
                    sb.append(model);
                    sb.append('~');
                    sb.append("null");
                    sb.append('~');
                    sb.append(bodyType);
                    sb.append('~');
                    sb.append(price);
                    sb.append('~');
                    sb.append(images);
                    bw.write(System.getProperty( "line.separator" ));
                    bw.write(sb.toString());

                    System.out.println("done!");

                } catch (IOException fnf) {
                    System.out.println(fnf.getMessage());
                }

                frame.setVisible(false);

            }

        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
    public static void main(String[] args){

    }
}
