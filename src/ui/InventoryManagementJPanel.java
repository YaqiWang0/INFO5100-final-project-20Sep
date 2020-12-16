package ui;

import dao.BodyType;
import dao.Vehicle;

import java.awt.event.WindowEvent;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class InventoryManagementJPanel {
    private JPanel InventoryMgmtPanel;
    private JButton logoutButton;
    private JTextField searchTextField;
    private JButton allButton;
    private JButton searchButton;
    public JTable table1;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JLabel searchLabel;
    private JPanel MainPanel;
    private JPanel SplitPanel;
    private JPanel TablePanel;
    private JLabel idLabel;
    private Object[][] tableVales;
    private ArrayList<Object[]> data;


    //YU-CHUN CHOU Edit
    private ArrayList<Vehicle> vehicleList;
    private ArrayList<String> vehicleIdList;
    private String[] columnNames2 = { "Id", "Year", "Brand", "Model", "New/Used", "Price", "BodyType", "Images" };
    private Object[][] data2;
    private DefaultTableModel tableModel2;
    public static final String url = new File("").getAbsolutePath() + "/data/";


    public void InitialData(String dealerId) throws IOException {
        File file = new File("data");
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].getName().equals(dealerId)){
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/"+dealerId)));
                String line = br.readLine();
                int j =0;
                while((line=br.readLine())!=null){
                    String[] info = line.split("~");

                    String vehicleImagePath = info[9];
                    ImageIcon imageIcon;

                    URL url = new URL(vehicleImagePath);
                    HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
                    urlcon.setRequestMethod("POST");
                    urlcon.setRequestProperty("Content-type",
                            "application/x-www-form-urlencoded");
//                    if (urlcon.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                        Image image = ImageIO.read(url);
                    //load imageUrl in the file
                    imageIcon = new ImageIcon(new URL(vehicleImagePath));
//                    } else {
////                        vehicleImagePath = "src/ui/pictures/default-60x60.png";
////                        imageIcon = new ImageIcon(vehicleImagePath);
//                        //online image
//                        vehicleImagePath = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=654480783,3410226424&fm=26&gp=0.jpg";
//                        imageIcon = new ImageIcon(new URL(vehicleImagePath));
//                    }

                    System.out.println(j);
                    Object[] o = new Object[]{info[0],info[3],info[4],info[5],info[2],info[8],info[7],imageIcon};
                    this.data.add(o);
                    j++;
                }
                break;
            }
        }
        tableVales = new Object[data.size()][8];
        for (int i = 0; i < data.size(); i++) {
            tableVales[i] = data.get(i);
        }


    }

    public InventoryManagementJPanel(String dealerId) throws IOException {
        super();
        JFrame frame = new JFrame("Inventory Management");
        frame.setContentPane(InventoryMgmtPanel);
        //idLabel.setText("Welcome, "+dealerId);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        String[] columnNames = {"ID", "Year", "Brand", "Model", "New/Used", "Price", "Body Type", "Images"};
        data = new ArrayList<Object[]>();
        InitialData(dealerId);

        tableVales[0] = new Object[]{"2960297373", "2016", "Buick", "Cascada", "New", "37400.0", "CAR", new ImageIcon(new URL("http://inventory-dmg.assets-cdk.com/RTT/Buick/2016/2945603/default/ext_GAR_deg01x90.jpg"))};
//        tableVales = new Object[][]{{"2960297373", "2016", "Buick", "Cascada", "New", "37400.0", "Black", "Black", "CAR", "2dr Conv Premium", "0", new ImageIcon(new URL("http://inventory-dmg.assets-cdk.com/RTT/Buick/2016/2945603/default/ext_GAR_deg01x90.jpg"))}, {"2966525563", "2017", "Buick", "Enclave", "New", "46660.0", "Brown", "Black", "SUV", "Leather FWD", "0", new ImageIcon(new URL("http://inventory-dmg.assets-cdk.com/RTT/Buick/2017/3273383/default/ext_G1F_deg01x90.jpg"))}, {"2932765103", "2017", "Chevrolet", "Malibu", "New", "24140.0", "Black", "Black", "CAR", "1LS", "0", new ImageIcon(new URL("http://inventory-dmg.assets-cdk.com/RTT/Chevrolet/2017/3343993/default/ext_GAZ_deg01x90.jpg"))}};
        TableModel tableModel = new DefaultTableModel(tableVales, columnNames){
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        table1.setModel(tableModel);
        table1.getColumn(columnNames[7]).setPreferredWidth(100);
        table1.setRowHeight(60);


        //YU-CHUN CHOU Edit (change the img cell weith, heigh)
        table1.setRowHeight(((ImageIcon) tableModel.getValueAt(0, 7)).getIconHeight());
        TableColumnModel columnModel = table1.getColumnModel();
        columnModel.getColumn(7).setPreferredWidth(((ImageIcon) tableModel.getValueAt(0, 7)).getIconWidth());
        table1.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                System.out.println(e);
            }
        });

        //YU-CHUN CHOU Edit
        vehicleList = new ArrayList<Vehicle>();
        vehicleIdList = new ArrayList<String>();
        readFromData(dealerId);
        data2 = convert2Data();

        //YU-CHUN CHOU Edit
        tableModel2 = new DefaultTableModel(data2, columnNames2){
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 7:
                        return ImageIcon.class;
                    default:
                        return Object.class;
                }
            }
        };


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchContent = searchTextField.getText();
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table1.getModel()));
                sorter.setRowFilter(RowFilter.regexFilter(searchTextField.getText()));

                table1.setRowSorter(sorter);

                //reload data
                if (searchTextField.getText().equals("")){
                    try {
//                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        InventoryManagementJPanel im = new InventoryManagementJPanel(dealerId);
                        frame.dispatchEvent(new WindowEvent(frame,WindowEvent.WINDOW_CLOSING) );
                    }catch (IOException e1){
                        e1.printStackTrace();
                    }

                }

            }
        });
        allButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table1.getModel()));
                sorter.setRowFilter(RowFilter.regexFilter(""));

                table1.setRowSorter(sorter);
            }
        });

        //upload button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                InventoryUpdateJPanel panel = new InventoryUpdateJPanel(table1.getValueAt(row, 0).toString(),dealerId,table1.getValueAt(row, 1).toString(),table1.getValueAt(row, 2).toString(),table1.getValueAt(row, 3).toString(),table1.getValueAt(row, 4).toString(),table1.getValueAt(row, 5).toString(),table1.getValueAt(row, 6).toString(),"4dr Sdn Turbo FWD",table1.getValueAt(row, 7).toString());
                System.out.println(table1.getValueAt(row, 0).toString() + " " + table1.getValueAt(row, 1).toString());
            }
        });


        //YU-CHUN CHOU Edit
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventoryCreationJPanel panel = new InventoryCreationJPanel(dealerId);

                try {
                    showData(dealerId);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }


        });


        //YU-CHUN CHOU Edit
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //table1.remove(table1.getSelectedRow());
                // check for selected row first
                if (table1.getSelectedRow() != -1) {
                    // remove selected row from the model
                    tableModel2.removeRow(table1.getSelectedRow());
                    toData(new File(url+dealerId), dealerId);
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                }
                try {
                    showData(dealerId);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    //YU-CHUN CHOU Edit
    public void showData(String dealerId) throws IOException {
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        readFromData(dealerId);
        data2 = convert2Data();
        tableModel.setDataVector(data2, columnNames2);
    }

    //YU-CHUN CHOU Edit
    private void toData(File file,String dealerId) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("id~webId~category~year~make~model~trim~type~price~photo");
            bw.write(System.getProperty( "line.separator" ));
            for (int i = 0; i < tableModel2.getRowCount(); i++) {
                for (int j = 0; j < tableModel2.getColumnCount(); j++) {
                    String data = tableModel2.getValueAt(i, j) + "";
                    if (data.equals("null") || data == null || data.isEmpty()) {
                        data = "null";
                    }
                    if (j == 0) {
                        bw.write(data);
                        bw.write("~" + dealerId+"~"+tableModel2.getValueAt(i, 4)+"");
                    }else if(j ==4){
                        bw.write("");

                    }else if(j ==5){
                        bw.write("~null" );
                    }
                    else if(j==tableModel2.getColumnCount()-1){
                        //TODO
                        ImageIcon b = (ImageIcon) tableModel2.getValueAt(i, 7);
                        //System.out.println(excelJL.getDescription());
                        bw.write("~"+tableModel2.getValueAt(i, 5)+"~" + b.getDescription());
                    }
                    else {
                        bw.write("~" + data);
                    }


                }


                bw.newLine();
            }
            //JOptionPane.showMessageDialog(null, "Sucessful Export!");
            System.out.println("Sucessful Export!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //YU-CHUN CHOU Edit
    private void readFromData(String dealerId) {
        vehicleList.clear();
        vehicleIdList.clear();
        File file = new File(url+dealerId);
        String line = "";
        try (BufferedReader reader  = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String line1=null;
            while ((line1 = reader.readLine()) != null) {

                String[] tokens = line1.trim().split("~");
                String vehicleId = tokens[0].trim();
                String year = tokens[3].trim();
                String brand = tokens[4].trim();
                String model = tokens[5].trim();
                Boolean isNew = Boolean.parseBoolean(tokens[2].trim());
                String price = tokens[8].trim();
                String bodyTypeStr = tokens[7].trim();
                BodyType bodyType = null;
                if (!bodyTypeStr.equals("null")) {
                    if (bodyTypeStr.equals("CARGO VAN")) {
                        bodyTypeStr = "VAN";
                    }
                    bodyType = BodyType.valueOf(bodyTypeStr);
                }
                String imgUrl = tokens[9].trim();
                System.out.println("imgUrl="+imgUrl);
                Vehicle v = new Vehicle(dealerId, year, brand, model, isNew, price, null, null,
                        bodyType, null);
                v.addImgUrl(imgUrl);
                //v.addFeatures(features);
                vehicleList.add(v);
                vehicleIdList.add(vehicleId);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    //YU-CHUN CHOU Edit
    private Object[][] convert2Data() throws IOException {
        data2 = new Object[vehicleList.size()][8];
        for (int i = 0; i < vehicleList.size(); i++) {
            data2[i][0] = vehicleIdList.get(i);
            //data[i][1] = vehicleList.get(i).getDealerId();
            data2[i][1] = vehicleList.get(i).getYear();
            data2[i][2] = vehicleList.get(i).getBrand();
            data2[i][3] = vehicleList.get(i).getModel();
            if (vehicleList.get(i).getStatus()) {
                data2[i][4] = "new";
            } else {
                data2[i][4] = "used";
            }
            data2[i][5] = vehicleList.get(i).getPrice();
            data2[i][6] = vehicleList.get(i).getBodyType();

            ImageIcon icon = null;

            URL url1 = null;
            URL url2 = null;
            BufferedImage img = null;
            Image newImg = null;



            try {
                url1 = new URL(vehicleList.get(i).getImg().get(0));
                img = ImageIO.read(url1);
                newImg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                icon = new ImageIcon(newImg);  // transform it back
                icon.setDescription(vehicleList.get(i).getImg().get(0));
            } catch (IOException e1) {
                url2 = new URL("https://i1.wp.com/fremontgurdwara.org/wp-content/uploads/2020/06/no-image-icon-2.png");
                img = ImageIO.read(url2);
                newImg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                icon = new ImageIcon(newImg);  // transform it back
                icon.setDescription(vehicleList.get(i).getImg().get(0));

            }
            data2[i][7] = icon;
        }
        return data2;
    }

    public static void main(String[] args) throws IOException {
        InventoryManagementJPanel im = new InventoryManagementJPanel("gmps-ernievon");
    }

}