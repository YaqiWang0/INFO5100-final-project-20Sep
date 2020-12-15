package ui;

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
        idLabel.setText("Welcome, "+dealerId);
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

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventoryCreationJPanel panel = new InventoryCreationJPanel(dealerId);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                table1.remove(table1.getSelectedRow());
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        InventoryManagementJPanel im = new InventoryManagementJPanel("gmps-ernievon");
    }

}
