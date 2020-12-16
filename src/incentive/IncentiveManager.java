package incentive;

import dao.Special;
import dao.Vehicle;
import dto.DataPersistence;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IncentiveManager extends JFrame {
    private JButton button1;
    private JPanel panelMain;
    private JTabbedPane tabbedPanel;
    private JPanel Inventory;
    private JPanel Details;
    private JPanel Description;
    private JComboBox startYear;
    private JComboBox endYear;
    private JComboBox startDay;
    private JComboBox startMonth;
    private JComboBox endDay;
    private JComboBox endMonth;
    private JCheckBox cashPaymentCheckBox;
    private JCheckBox checkPaymentCheckBox;
    private JCheckBox loanCheckBox;
    private JCheckBox leaseCheckBox;
    private JRadioButton flatValue;
    private JTextField inputValue;
    private JRadioButton percentValue;
    private JTextField inputPercent;
    private JLabel categoryJLabel;
    private JComboBox categoryComboBox;
    private JTextField vinTextField;
    private JComboBox yearComboBox;
    private JComboBox makeComboBox;
    private JComboBox modelComboBox;
    private JRadioButton applyRadioButton;
    private JRadioButton manuallyRadioButton;
    private JComboBox priceOperatorComboBox;
    private JComboBox mileageOperatorComboBox;
    private JTextField priceTextField;
    private JTextField mileageTextField;
    private JButton clearCriteriaButton;
    private JTable vehicleTable;
    private JLabel vinJLabel;
    private JLabel yearJLabel;
    private JLabel makeJLabel;
    private JLabel modelJLabel;
    private JLabel searchJLabel;
    private JButton searchTheResultButton;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JTextArea disclaimerArea;
    private ButtonGroup specialScopeButtonGroup;
    private ButtonGroup discountValueButtonGroup;

    Special spl;
    Vehicle veh;
    private List<Vehicle> vehicleList;
    private List<Vehicle> filteredVehicleList;
    private DataPersistence dataPersistence;
    private boolean applyToAllVehicles = true;

    public IncentiveManager(String dealerId) {
        spl = new Special();
        dataPersistence = new DataPersistence();
        this.spl.setDealerId(dealerId);
        initComponents();
    }

    public IncentiveManager(){

    }

    private void initComponents() {
        this.vehicleList = dataPersistence
                            .getVehicles(this.spl.getDealerId())
                            .stream()
                            .filter(vehicle ->
                            vehicle.getDealerId().equals(this.spl.getDealerId())).collect(Collectors.toList());
        publish();
        addItemsToComboBoxes();
        clearCriteria();
        searchResult();
        selectMake();
        specialScopeButtonGroup();
        discountButtonGroup();
        createTable();
    }

    public void discountButtonGroup(){
        discountValueButtonGroup = new ButtonGroup();
        discountValueButtonGroup.add(flatValue);
        discountValueButtonGroup.add(percentValue);
        flatValue.setSelected(true);
    }

    public void specialScopeButtonGroup() {
        specialScopeButtonGroup = new ButtonGroup();
        specialScopeButtonGroup.add(applyRadioButton);
        specialScopeButtonGroup.add(manuallyRadioButton);
        applyRadioButton.setSelected(true);
    }

    public void clearCriteria() {
        clearCriteriaButton.addActionListener(e -> {
            categoryComboBox.setSelectedIndex(0);
            vinTextField.setText("");
            yearComboBox.setSelectedIndex(0);
            makeComboBox.setSelectedIndex(0);
            modelComboBox.setSelectedIndex(0);
            clearModelItems();
            priceOperatorComboBox.setSelectedIndex(0);
            priceTextField.setText("");
        });
    }

    public void searchResult() {
        searchTheResultButton.addActionListener(e -> createTable());
    }

    private void createTable() {
        filteredVehicleList = getFilteredVehicleList();
        Object[][] data = new Object[filteredVehicleList.size()][7]; //filters : string, int
        for (int i = 0; i < filteredVehicleList.size(); i++) {
            Vehicle vehicle = filteredVehicleList.get(i);
            String status = vehicle.getStatus() ? "New" : "Used";
            data[i] = new Object[]{vehicle.getVehicleId(),
                    status,
                    vehicle.getYear(),
                    vehicle.getBrand(),
                    vehicle.getModel(),
                    vehicle.getPrice()};
        }

        vehicleTable.setModel(new DefaultTableModel(
                data,
                new String[]{"VehicleID", "Category", "Year", "Make", "Model", "Price"}
        ));
    }

    //gets the make and adds models to the combo box
    public void selectMake() {
        //var dataPersistence = this.dataPersistence;
        makeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String brand = (String) makeComboBox.getSelectedItem();
                clearModelItems();
                if (brand != null && !brand.equals("All Makes")) {
                    Set<String> models = new HashSet<>();
                    vehicleList.stream().filter(vehicle -> vehicle.getBrand()
                            .equals(brand))
                            .map(Vehicle::getModel)
                            .sorted()
                            .forEach(model -> {
                                models.add(model);
                                //modelComboBox.addItem(model);
                            });
                    for(String s : models){
                        modelComboBox.addItem(s);
                    }
                }
            }
        });
    }

    private void clearModelItems() {
        modelComboBox.setSelectedIndex(0);
        int itemsCount = modelComboBox.getItemCount();
        for (int i = 1; i < itemsCount; i++) {
            modelComboBox.removeItemAt(i);
        }
    }

    //all or select manually
    public void setSpecialScope() {
        if (applyRadioButton.isSelected()) {
            spl.setScope(filteredVehicleList.stream()
                    .map(Vehicle::getVehicleId).collect(Collectors.toList()));
        } else {
            int selectedRow = vehicleTable.getSelectedRow();
            List<String> specialScope = new ArrayList<>();
            if (selectedRow > -1) {
                specialScope.add(filteredVehicleList.get(selectedRow).getVehicleId());
            }
            spl.setScope(specialScope);
        }
    }

    public void addItemsToComboBoxes() {
        //startMonth.setPreferredSize(80px);
        //add items for start month combo box
        for (int i = 1; i <= 12; i++) {
            startMonth.addItem(i);
            endMonth.addItem(i);
        }

        //add items for start day combo box
        for (int i = 1; i <= 31; i++) {
            startDay.addItem(i);
            endDay.addItem(i);
            //filter as/month
        }

        for (int i = 2020; i < 2050; i++) {
            startYear.addItem(i);
            endYear.addItem(i);
        }

        // add items for make combo box
        vehicleList.stream().map(Vehicle::getBrand)
                .distinct()
                .sorted()
                .forEach(brand -> makeComboBox.addItem(brand));
    }

    //setting information
    public void setDates() throws ParseException {
        int sMonth = (int) startMonth.getSelectedItem();
        int sDay = (int) startDay.getSelectedItem();
        int sYear = (int) startYear.getSelectedItem();
        String startDate = sDay + "/" + sMonth + "/" + sYear;
        Date sDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);

        int eMonth = (int) endMonth.getSelectedItem();
        int eDay = (int) endDay.getSelectedItem();
        int eYear = (int) endYear.getSelectedItem();
        String endDate = eDay + "/" + eMonth + "/" + eYear;
        Date eDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);

        if(!eDate.after(sDate)){
            JOptionPane.showMessageDialog(null, "Invalid end date!");
        }
        else {
            //System.out.println(sDate.toString());
            spl.setStartDate(sDate);
            spl.setEndDate(eDate);
        }
    }

    public void setDiscountValue() {
        if (flatValue.isSelected()) {
            System.out.println("#1");
            checkValues(inputValue.getText());
            int value = Integer.parseInt(inputValue.getText());
            spl.setDiscountValue(value);
        } else if (percentValue.isSelected()) {
            checkValues(inputPercent.getText());
            int percent = Integer.parseInt(inputPercent.getText());
            spl.setDiscountPercent(percent);
        }
    }

    public void checkValues(String val){
        if(!val.matches("[0-9]+") || val.length() <= 0){
            //pop up
            JOptionPane.showMessageDialog(null, "Please enter valid discount value");
        }
    }

    public void setPaymentValidity() {
        if (cashPaymentCheckBox.isSelected()) {
            spl.setValidOnCashPayment(true);
        }
        if (checkPaymentCheckBox.isSelected()) {
            spl.setValidOnCheckPayment(true);
        }
        if (loanCheckBox.isSelected()) {
            spl.setValidOnLoan(true);
        }
        if (leaseCheckBox.isSelected()) {
            spl.setValidOnLease(true);
        }
    }

    public void setTitleAndDescription() {
        spl.setTitle(titleField.getText());
        spl.setDescription(descriptionArea.getText());
        spl.setDisclaimer(disclaimerArea.getText());
    }

    public void setVehicleFilters(){
        String year = String.valueOf(yearComboBox.getSelectedItem());
        spl.setYear(year);
        String valueOfVehicle = priceTextField.getText();
        spl.setValueOfVehicle(valueOfVehicle);
        if(categoryComboBox.getSelectedItem()=="New"){
            spl.setIsNew(String.valueOf(true));
        } else {
            spl.setIsNew(String.valueOf(false));
        }
        spl.setBrand((String) makeComboBox.getSelectedItem());
        spl.setBodyType((String) modelComboBox.getSelectedItem());
    }

    public Special publish() {
        button1.addActionListener(e -> {

            //call the setters here
            try {
                setDates();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            setDiscountValue();
            setPaymentValidity();
            setTitleAndDescription();
            setSpecialScope();
            setVehicleFilters();

            DataPersistence dp = new DataPersistence();
            //add this special to database
            System.out.println("#2 " + spl.getDiscountValue());
            ;
            dp.writeSpecial(spl, this.spl.getDealerId());

            JOptionPane.showMessageDialog(null, "Incentive Created!");

        });

        return spl;
    }

    private List<Vehicle> getFilteredVehicleList() {
        String vin = vinTextField.getText();
        if (vin.length() > 0) {
            return vehicleList.stream().filter(vehicle -> vehicle.getVehicleId().equals(vin)).collect(Collectors.toList());
        }

        return vehicleList.stream().filter(vehicle -> {
            // Category filter
            String selectedCategory = (String) categoryComboBox.getSelectedItem();
            boolean status = vehicle.getStatus();
            if (selectedCategory == null || selectedCategory.equals("New")) {
                return status;
            }

            if (selectedCategory.equals("Used")) {
                return !status;
            }

            return true;
        }).filter(vehicle -> {
            // Year filter
            String selectedYear = (String) yearComboBox.getSelectedItem();
            if (selectedYear == null || selectedYear.equals("All Years")) {
                return true;
            }

            return vehicle.getYear().equals(selectedYear);
        }).filter(vehicle -> {
            // Make and model filter
            String selectedMake = (String) makeComboBox.getSelectedItem();
            String selectedModel = (String) modelComboBox.getSelectedItem();
            if (selectedMake == null || selectedMake.equals("All Makes")) {
                return true;
            }
            boolean makeCondition = vehicle.getBrand().equals(selectedMake);
            if (selectedModel == null || selectedModel.equals("All Models")) {
                return makeCondition;
            }

            return makeCondition && selectedModel.equals(vehicle.getModel());
        }).filter(vehicle -> {
            // Price filter
            double price = 0;
            try {
                price = Double.parseDouble(priceTextField.getText());
            } catch (Exception e) {
                return true;
            }
            String operator = (String) priceOperatorComboBox.getSelectedItem();
            if (price == 0) {
                return true;
            }

            double vehiclePrice = 0;
            try {
                vehiclePrice = Double.parseDouble(vehicle.getPrice());
            } catch (Exception e) {
                return true;
            }
            if (operator == null || operator.equals("< or =")) {
                return vehiclePrice <= price;
            }
            return vehiclePrice >= price;
        }).collect(Collectors.toList());
    }

    /*
    dealer: id:xiang with password:12345 or id:jing with the same password.
    This method will be called from DealerHome
     */
    public void mainCaller(String dealerId) throws ParseException {
        IncentiveManager frame = new IncentiveManager(dealerId);
        frame.setTitle("Create Incentive");
        frame.setContentPane(frame.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 400));
        frame.pack();
        frame.setVisible(true);
    }

    /*
    This method is only for testing the case#5 independently
     */
    public static void main(String[] args) throws ParseException {
        String dealerId = "a199e835-4514-43c3-9ba9-c028e61bb81a";
        IncentiveManager frame = new IncentiveManager(dealerId);
        frame.setTitle("Create Incentive");
        frame.setContentPane(frame.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 400));
        frame.pack();
        frame.setVisible(true);
    }
}
