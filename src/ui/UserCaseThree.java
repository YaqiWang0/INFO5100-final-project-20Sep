package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class UserCaseThree {
    static JFrame frame;

    static Map<String, List<String>> dataMap;
    static String csvFile = "./data/car.csv";
    static String data;


    public static void main(String[] args) {

        data = CSVReaderTest.read(csvFile);

        dataMap = new HashMap<>();
        for (String line : data.split("\n")) {
            String[] tokens = line.split(" ");
            String key = tokens[0].toLowerCase().replaceAll("\\s", "");
            if (!dataMap.containsKey(key)) {
                dataMap.put(key, new ArrayList<>());
            }
            dataMap.get(key).add(line);
            System.out.println(key + ": " + line);
        }
        frame = new JFrame("My First Swing Example");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);

        placeComponents(panel);

        // Setting the frame visibility to true
        frame.setVisible(true);


    }

    private static void placeComponents(JPanel panel) {

        panel.setLayout(new GridLayout(5,1));

        JLabel searchTermLabel = new JLabel("Input your search term");
        panel.add(searchTermLabel);

        JTextField searchTermText = new JTextField();
        panel.add(searchTermText);

        JButton searchButton = new JButton("Search");
        searchButton.setForeground(Color.GREEN);
        searchButton.setBackground(Color.BLACK);
        panel.add(searchButton);

        JPanel searchResultPanel = new JPanel();
        panel.add(searchResultPanel);

        JLabel detailsText = new JLabel("Details");
        panel.add(detailsText);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button is clicked");
                searchResultPanel.removeAll();
                String searchText = searchTermText.getText().toLowerCase();
                List<String> results = dataMap.get(searchText);

                for (String result : results) {
                    System.out.print(result + " ");
                    String[] tokens = result.split(" ");
                    JButton resultButton = new JButton(tokens[0] + ": " + tokens[1]);
                    resultButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            detailsText.setText(result);
                        }
                    });
                    searchResultPanel.add(resultButton);
                }
                searchResultPanel.revalidate();
                searchResultPanel.repaint();
            }
        });
    }

}
