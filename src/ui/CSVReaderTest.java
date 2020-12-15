package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVReaderTest {

    public static final String delimiter = ",";
    public static String read(String csvFile) {
        StringBuilder res = new StringBuilder();
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;

            while((line = br.readLine()) != null) {
                line = line.replaceAll("[^\\p{Graph}\n\r\t ]", "");
                tempArr = line.split(delimiter);
                for(String tempStr : tempArr) {
                    res.append(tempStr).append(" ");
                }
                res.append("\n");
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return res.toString();

    }
}
