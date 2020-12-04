package dto;

import dao.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataPersistence implements AbstractPersistent {

    private static final String DATA_PATH = "./INFO5100-final-project-20Sep/data/";
    // Reads dealers file in data directory and returns a map of dealers with
    // the dealer's ids as its keys and its corresponding Dealer object as the value
    @Override
    public List<Dealer> getAllDealers() {
        List<Dealer> result = new ArrayList<>();
        String dealerFilePath = DATA_PATH + "dealers.csv";
        File csv = new File(dealerFilePath);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(csv));
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {br.close(); } catch (IOException e) {e.printStackTrace();}
            }
        }

        return result;

    }

    @Override
    public void writeDealers(List<Dealer> dealers) {
        String dealerFilePath = DATA_PATH + "dealers.csv";
        File csv = new File(dealerFilePath);
        BufferedWriter bw = null;
        if (!csv.exists()) {
            try {csv.createNewFile(); } catch (IOException e) {e.printStackTrace();}
        }
        try {
            bw = new BufferedWriter(new FileWriter(csv, true));
            for (Dealer d: dealers) {
                bw.write(d.toCSVLine());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {bw.close(); } catch (IOException e) {e.printStackTrace();}
            }
        }
    }


    /**
     * Read all specials in the file.
     * @return a map of all specials saved in the specials.csv (key: specialId, value: special)
     */
    @Override
    public List<Special> getAllSpecials() {
        File csv = new File(DATA_PATH + "specials.csv");
        BufferedReader br = null;
        List<Special> allSpecials = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(csv));
            String line = br.readLine();
            // iterate through each Special in the file
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return allSpecials;
    }


    /**
     * Overwrite specials.csv with the given specials.
     * @param allSpecials are the specials to be saved in the specials.csv
     */
    @Override
    public void writeSpecials(List<Special> allSpecials) {
        File csv = new File(DATA_PATH + "specials.csv");
        if (!csv.exists()) {
            try {csv.createNewFile(); } catch (IOException e) {e.printStackTrace();}
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csv,true));
            // create a new specials.csv and write each special into the file
            for (Special special : allSpecials) {
                bw.write(special.toCSVLine());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> result = new ArrayList<>();
        String vehicleFilePath = DATA_PATH + "vehicles.csv";
        File csv = new File(vehicleFilePath);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(csv));

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                String[] features = fields[11].split("\t");
                String[] imgUrls = fields[12].split("\t");

                Vehicle v = new Vehicle(fields[1]);
                v.setVehicleId(fields[0]);
                v.setYear(fields[2]);
                v.setBrand(fields[3]);
                v.setModel(fields[4]);
                v.setIsNew(Boolean.parseBoolean(fields[5]));
                v.setPrice(fields[6]);
                v.setExteriorColor(fields[7]);
                v.setInteriorColor(fields[8]);
                v.setBodyType(BodyType.valueOf(fields[9]));
                v.setMiles(fields[10]);
                for (String feature : features) {
                    v.addFeatures(feature);
                }
                for (String imgUrl : imgUrls) {
                    v.addImgUrl(imgUrl);
                }

                result.add(v);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public void writeVehicles(List<Vehicle> vehicles) {
        String vehicleFilePath = DATA_PATH + "vehicles.csv";
        File csv = new File(vehicleFilePath);
        BufferedWriter bw = null;
        if (!csv.exists()) {
            try {csv.createNewFile(); } catch (IOException e) {e.printStackTrace();}
        }
        try {
            bw = new BufferedWriter(new FileWriter(csv, true));
            for (Vehicle vehicle: vehicles) {
                bw.write(vehicle.toCSVLine());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
