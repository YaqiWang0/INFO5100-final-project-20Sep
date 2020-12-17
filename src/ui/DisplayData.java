package ui;


import dto.DataPersistence;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class DisplayData extends JFrame {
    private final int DATA_LENGTH = 10;

    public DisplayData(ArrayList<String[]> sortedList) {
        Object[][] data = new Object[sortedList.size()][DATA_LENGTH];
        JTable table = null;
        for(int i = 0; i < sortedList.size(); i++) {

                data[i][0] = sortedList.get(i)[5];
                data[i][1] = sortedList.get(i)[7];
                data[i][2] = sortedList.get(i)[3];
                data[i][3] = sortedList.get(i)[8];

        }
        String[] columns = new String[] {"Model","Type","Year", "Price"};
        table=new JTable(data, columns);
        table.setRowHeight(100);
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(800, 600));
        table.setFont(new Font("Serif", Font.PLAIN, 15));
        this.add(pane);
        this.setTitle("Sorted Data");
        this.pack();
        this.setVisible(true);
    }

}
