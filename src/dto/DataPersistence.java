package dto;

import dao.Address;
import dao.Dealer;
import dao.GenericModel;
import dao.Special;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataPersistence {

    private static final String DATA_PATH = "./INFO5100-final-project-20Sep/data/";
    // Reads dealers file in data directory and returns a map of dealers with
    // the dealer's ids as its keys and its corresponding Dealer object as the value
    public Map<String, Dealer> readDealerFile() throws IOException, FileNotFoundException{
        Map<String, Dealer> result = new HashMap<>();
        String dealerFilePath = DATA_PATH + "dealers.csv";
        File csv = new File(dealerFilePath);
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(csv));

        String line = "";
        line = br.readLine();
        while (line != null) {
            String[] fields = line.split(",");
            for (int i = 0; i < fields.length; i++) {
                System.out.print(i + " " + fields[i] + " ");
            }
            System.out.println();
            String dealerId = fields[0];
            String dealerName = fields[1];
            String address1 = fields[2];
            String address2 = fields[3];
            String city = fields[4];
            String state = fields[5];
            String dealerZipCode = fields[6];
            Address dealerAddress = new Address(address1, address2, city, state, dealerZipCode);
            Dealer d = new Dealer(dealerId, dealerName, dealerAddress);
            result.put(d.getDealerId(), d);
            line = br.readLine();
        }
        return result;

    }

    public void saveDealersToFile(Map<String, Dealer> dealerMap) throws IOException{
        String dealerFilePath = DATA_PATH + "dealers.csv";
        File csv = new File(dealerFilePath);
        BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));

        for (String dealerId: dealerMap.keySet()) {
            Dealer d = dealerMap.get(dealerId);
            bw.write(d.toCSVLine());
            bw.newLine();
        }
        bw.close();

    }

    /**
     * Save the given special in the file.
     * @param i the Special to be saved
     * by Tianyu Bai
     */
    public static String specialToCSV(Special i) throws IOException {
        // escape comma and double quotes in title, description and disclaimer
        // other variables of Special should not contain any comma or double quotes
        String title = "\"<ti>" + i.getTitle() + "</ti>\"";
        String description = i.getDescription() == null ? "\"<de></de>\"" : "\"<de>" + i.getDescription() + "</de>\"";
        String disclaimer = i.getDisclaimer() == null ? "\"<di></di>\"" : "\"<di>" + i.getDisclaimer() + "</di>\"";

        // convert a Special to csv data
        String row = i.getSpecialId() + ","
                + i.getDealerId() + ","
                + i.getStartDate() + ","
                + i.getEndDate() + ","
                + title + ","
                + description + ","
                + disclaimer + ","
                + i.getValue() + ","
                + i.getYear() + ","
                + i.getBrand() + ","
                + i.getBodyType() + ","
                + i.isNew() + "," // method naming (not getIsNew() in Special.java)
                + i.getScopeParameter() + ","
                + i.getScope(); // method naming (not getSpecialScope() in Special.java)

        return row;
    }


    /**
     * Overwrite specials.csv with the given specials.
     * @param allSpecials for overwriting the specials.csv
     * by Tianyu Bai
     */
    public static void writeSpecialsToFile(Map<String, Special> allSpecials) throws IOException {
        File csv = new File(DATA_PATH + "specials.csv");
        if (!csv.exists()) csv.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(csv,true)); // create buffered writer

        // create a new specials.csv and write each special into the file
        for (Special special : allSpecials.values()) {
            bw.write(specialToCSV(special));
            bw.newLine();
        }

        bw.close();
    }


    /**
     * Read all specials in the file.
     * @return a map of all specials with specialID as the key
     * by Tianyu Bai
     */
    public static Map<String, Special> readSpecialsFromFile() throws IOException{
        // setting the csv file
        File csv = new File(DATA_PATH + "specials.csv");
        BufferedReader br = new BufferedReader(new FileReader(csv));

        // iterate through each Special in the file
        Map<String, Special> allSpecials = new HashMap<>();
        String line = br.readLine();
        while (line != null) {
            // converting escaped Strings {title, description, disclaimer} to unescaped Strings
            String[] escSymbols = new String[]{"ti","de", "di"};
            String[] unescaped = new String[3];
            for (int i = 0; i < 3; i++) {
                int escStart = line.indexOf(",\"<" + escSymbols[i] + ">") + 6;
                unescaped[i] = line.substring(escStart); // substring after first double quote
                int escEnd = unescaped[i].indexOf("</" + escSymbols[i] + ">\",");
                unescaped[i] = unescaped[i].substring(0, escEnd); // substring in between first double quotes
                // remove first occurrence of substring with double quotes
                line = line.replaceFirst(",\"<" + escSymbols[i] + ">" + unescaped[i] + "</" + escSymbols[i] + ">\"", "");
            }

            // converting csv data to a Special
            String[] fields = line.split(",");
            Special i = new Special(fields[1],fields[2],fields[3],unescaped[0],fields[4]);
            i.setSpecialId(fields[0]); // added to Special.java
            i.setDescription(unescaped[1]);
            i.setDisclaimer(unescaped[2]);
            i.setYear(fields[5]);
            i.setBrand(fields[6]);
            i.setBodyType(fields[7]);
            i.setIsNew(fields[8]);
            i.setScopeParameter(fields[9]);
            if (!fields[10].equals("null")) i.setScope(SpecialScope.valueOf(fields[10]));

            allSpecials.put(fields[0], i); // add the converted special to the map
            line = br.readLine(); // read the next line of special
        }

        br.close();
        return allSpecials;
    }
}
