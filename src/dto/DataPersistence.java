package dto;

import dao.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataPersistence implements AbstractPersistent {

    private static final String DATA_PATH = "./INFO5100-final-project-20Sep/data/";
    // Reads dealers file in data directory and returns a map of dealers with
    // the dealer's ids as its keys and its corresponding Dealer object as the value
    @Override
    public List<Dealer> getAllDealers() throws IOException, FileNotFoundException{
        List<Dealer> result = new ArrayList<>();
        String dealerFilePath = DATA_PATH + "dealers.csv";
        File csv = new File(dealerFilePath);
        BufferedReader br = new BufferedReader(new FileReader(csv));

        String line = br.readLine();
        while (line != null) {

            String[] fields = line.split(",");

            Dealer d = new Dealer();
            d.setDealerId(fields[0]);
            d.setDealerName(fields[1]);
            Address a = new Address();
            a.setAddressInfo(fields[2], fields[3]);
            a.setCity(fields[4]);
            a.setState(fields[5]);
            a.setZipCode(fields[6]);
            d.setDealerAddress(a);
            result.add(d);
            line = br.readLine();
        }
        return result;

    }

    @Override
    public void writeDealers(List<Dealer> dealers) throws IOException{
        String dealerFilePath = DATA_PATH + "dealers.csv";
        File csv = new File(dealerFilePath);
        if (!csv.exists()) csv.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));

        for (Dealer d: dealers) {
            bw.write(d.toCSVLine());
            bw.newLine();
        }
        bw.close();

    }

    /**
     * Overwrite specials.csv with the given specials.
     * @param allSpecials are the specials to be saved in the specials.csv
     */
    public void saveSpecials(Map<String, Special> allSpecials) throws IOException {
        File csv = new File(DATA_PATH + "specials.csv");
        if (!csv.exists()) csv.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(csv,true)); // create buffered writer

        // create a new specials.csv and write each special into the file
        for (Special special : allSpecials.values()) {
            bw.write(special.toCSVLine());
            bw.newLine();
        }

        bw.close();
    }


    /**
     * Read all specials in the file.
     * @return a map of all specials saved in the specials.csv (key: specialId, value: special)
     */
    public List<Special> readSpecials() throws IOException{
        // setting the csv file
        File csv = new File(DATA_PATH + "specials.csv");
        BufferedReader br = new BufferedReader(new FileReader(csv));

        // iterate through each Special in the file
        List<Special> allSpecials = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            // converting escaped Strings {title, description, disclaimer} to unescaped Strings
            String[] escSymbols = new String[]{"ti", "de", "di"};
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

            allSpecials.add(i); // add the converted special to the map
            line = br.readLine(); // read the next line of special
        }

        br.close();
        return allSpecials;
    }


    public Map<String, Vehicle> readVehicleFile() throws FileNotFoundException, IOException {
        Map<String, Vehicle> result = new HashMap<>();
        String vehicleFilePath = DATA_PATH + "vehicles.csv";
        File csv = new File(vehicleFilePath);
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(csv));

        String line = br.readLine();
        while (line != null) {
            String[] fields = line.split(",");
            String vehicleId = fields[0];
            String dealerId = fields[1];
            String year = fields[2];
            String brand = fields[3];
            String model = fields[4];
            boolean isNew = Boolean.parseBoolean(fields[5]);
            String price = fields[6];
            String exteriorColor = fields[7];
            String interiorColor = fields[8];
            BodyType bodyType = BodyType.valueOf(fields[9]);
            String miles = fields[10];
            String[] features = fields[11].split("\t");
            String[] imgUrls = fields[12].split("\t");

            Vehicle v = new Vehicle(dealerId, year, brand, model,
                    isNew, price, exteriorColor, interiorColor, bodyType, miles);
            v.setVehicleId(vehicleId);
            for (String feature : features) {
                v.addFeatures(feature);
            }
            for (String imgUrl : imgUrls) {
                v.addImgUrl(imgUrl);
            }

            result.put(v.getVehicleId(), v);
            line = br.readLine();
        }
        return result;
    }

    public void saveVehiclesToFile(Map<String, Vehicle> vehicleMap) throws IOException{
        String vehicleFilePath = DATA_PATH + "vehicles.csv";
        File csv = new File(vehicleFilePath);
        BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));

        for (Vehicle vehicle: vehicleMap.values()) {
            bw.write(vehicle.toCSVLine());
            bw.newLine();
        }
        bw.close();
    }
}
