package ui;

import service.DeleteInventory;

import java.awt.event.WindowEvent;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private JFrame frame;

    public String[] InitialData(String dealerId, int count) throws IOException {
        String[] urls = new String[count + 1];
        int c = 0;
        File file = new File("data");
        File[] fileList = file.listFiles();


        FileInputStream a=null;
        InputStreamReader b =null;
        BufferedReader br =null;
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].getName().equals(dealerId)){

                // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/"+dealerId)));

                a =new FileInputStream("data/"+dealerId);
                b =  new InputStreamReader(a);
                br = new BufferedReader(b);

                String line = "";
                int j =0;
                while((line=br.readLine())!=null){
                    String[] info = line.split("~");

                    String vehicleImagePath = info[9];
                    urls[c] = info[9];
                    c++;
//                    System.out.println(urls[i] + "*");
                    ImageIcon imageIcon;

//                    URL url = new URL(vehicleImagePath);
//                    HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
//                    urlcon.setRequestMethod("POST");
//                    urlcon.setRequestProperty("Content-type",
//                            "application/x-www-form-urlencoded");
                    try {
                        URL url = new URL(vehicleImagePath);
                        HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
                        urlcon.setRequestMethod("POST");
                        urlcon.setRequestProperty("Content-type",
                                "application/x-www-form-urlencoded");
                        imageIcon = new ImageIcon(new URL(vehicleImagePath));
                        url = new URL(vehicleImagePath);
//                        if(String.valueOf(url).equals("src/ui/pictures/default-60x60.png")){
//                            vehicleImagePath = "src/ui/pictures/default-60x60.png";
//                            imageIcon = new ImageIcon(vehicleImagePath);
//                        }
//                        else{
                        Image image = ImageIO.read(url);
                        imageIcon = new ImageIcon(image);
//                        }
                    } catch (MalformedURLException e) {
                        //e.printStackTrace();
                        vehicleImagePath = "src/ui/pictures/default-60x60.png";
                        imageIcon = new ImageIcon(vehicleImagePath);
                    } catch (IOException e) {
                        vehicleImagePath = "src/ui/pictures/default-60x60.png";
                        imageIcon = new ImageIcon(vehicleImagePath);
                    }catch (NullPointerException e){
                        vehicleImagePath = "src/ui/pictures/default-60x60.png";
                        imageIcon = new ImageIcon(vehicleImagePath);
                    }
//                    if(urlcon.getResponseCode() >= 400){
//                        imageIcon = new ImageIcon("src/ui/pictures/default-60x60.png");
//                    }
                    System.out.println(j);
                    Object[] o = new Object[]{info[0],info[3],info[4],info[5],info[2],info[8],info[7],info[6],imageIcon};
                    this.data.add(o);
                    j++;
                }
                a.close();
                br.close();
                break;
            }

        }

//        String[] columnNames = {"ID", "Year", "Brand", "Model", "New/Used", "Price", "Body Type", "Images"};
//        TableModel tableModel = new DefaultTableModel(tableVales, columnNames){
//            public Class getColumnClass(int column) {
//                return getValueAt(0, column).getClass();
//            }
//        };
        tableVales = new Object[data.size()][9];
        for (int i = 0; i < data.size(); i++) {
            tableVales[i] = data.get(i);
        }

//        System.out.println(urls[0]);
        return urls;
    }

    public InventoryManagementJPanel(String dealerId) throws IOException {
        super();
        frame = new JFrame("Inventory Management");
        frame.setContentPane(InventoryMgmtPanel);
        idLabel.setText("Welcome, "+dealerId);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1200, 1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        String[] columnNames = {"ID", "Year", "Brand", "Model", "New/Used", "Price", "Body Type", "Features", "Images"};
        data = new ArrayList<Object[]>();
        File f = new File("data");
        File[] fileList = f.listFiles();
        int count = 0;

        FileInputStream fis=null;
        InputStreamReader isr =null;
        BufferedReader br =null;
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].getName().equals(dealerId)) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/" + dealerId)));
                fis =new FileInputStream("data/"+dealerId);
                isr =  new InputStreamReader(fis);
                br = new BufferedReader(isr);
                String line = br.readLine();
                while ((line = br.readLine()) != null) {
                    count++;
                }
                br.close();
                fis.close();
            }
        }
        String[] urls = InitialData(dealerId, count);

//        tableVales[0] = new Object[]{"2960297373", "2016", "Buick", "Cascada", "New", "37400.0", "CAR", new ImageIcon(new URL("http://inventory-dmg.assets-cdk.com/RTT/Buick/2016/2945603/default/ext_GAR_deg01x90.jpg"))};
//        tableVales = new Object[][]{{"2960297373", "2016", "Buick", "Cascada", "New", "37400.0", "Black", "Black", "CAR", "2dr Conv Premium", "0", new ImageIcon(new URL("http://inventory-dmg.assets-cdk.com/RTT/Buick/2016/2945603/default/ext_GAR_deg01x90.jpg"))}, {"2966525563", "2017", "Buick", "Enclave", "New", "46660.0", "Brown", "Black", "SUV", "Leather FWD", "0", new ImageIcon(new URL("http://inventory-dmg.assets-cdk.com/RTT/Buick/2017/3273383/default/ext_G1F_deg01x90.jpg"))}, {"2932765103", "2017", "Chevrolet", "Malibu", "New", "24140.0", "Black", "Black", "CAR", "1LS", "0", new ImageIcon(new URL("http://inventory-dmg.assets-cdk.com/RTT/Chevrolet/2017/3343993/default/ext_GAZ_deg01x90.jpg"))}};
        TableModel tableModel = new DefaultTableModel(tableVales, columnNames){
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        table1.setModel(tableModel);
        table1.getColumn(columnNames[8]).setPreferredWidth(100);
        table1.setRowHeight(60);

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
                System.out.println(urls[row]);
                InventoryUpdateJPanel panel = new InventoryUpdateJPanel(frame, InventoryMgmtPanel, table1.getValueAt(row, 0).toString(),dealerId,table1.getValueAt(row, 1).toString(),table1.getValueAt(row, 2).toString(),table1.getValueAt(row, 3).toString(),table1.getValueAt(row, 4).toString(),table1.getValueAt(row, 5).toString(),table1.getValueAt(row, 6).toString(),table1.getValueAt(row, 7).toString(), urls[row]);
                System.out.println(table1.getValueAt(row, 0).toString() + " " + table1.getValueAt(row, 1).toString());
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventoryCreationJPanel panel = new InventoryCreationJPanel(dealerId, frame, InventoryMgmtPanel);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                DeleteInventory.deleteInventoryByDealer(String.valueOf(table1.getValueAt(row, 0)), dealerId);
                try {
                    frame.dispose();
                    InventoryManagementJPanel panel = new InventoryManagementJPanel(dealerId);
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

//    public static boolean isCommonUrl(String urls) {
//        boolean isurl = false;
//        String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
//                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";
//
//        Pattern pat = Pattern.compile(regex.trim());
//        Matcher mat = pat.matcher(urls.trim());
//        isurl = mat.matches();
//        if (isurl) {
//            isurl = true;
//        }
//        return isurl;
//    }

    public static void main(String[] args) throws IOException {
        InventoryManagementJPanel im = new InventoryManagementJPanel("gmps-ernievon");
    }

}
