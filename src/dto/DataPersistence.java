package dto;

import dao.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DataPersistence implements AbstractPersistent {

    private String dataPath;

    // constructor for the DataPersistence model. Reads the absolute path of the current project
    // and uses it as the path to read and write csv file.
    public DataPersistence() {
        this.dataPath = new File("").getAbsolutePath() + "/data/";
    }
    // Reads dealers file in data directory and returns a map of dealers with
    // the dealer's ids as its keys and its corresponding Dealer object as the value
    @Override
    public List<Dealer> getAllDealers() {
        List<Dealer> result = new ArrayList<>();
        String dealerFilePath = this.dataPath + "dealers.csv";
        File csv = new File(dealerFilePath);
        if (!csv.exists()) {
            try {csv.createNewFile(); } catch (IOException e) {e.printStackTrace();}
        }
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
        String dealerFilePath = this.dataPath + "dealers.csv";
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
        File csv = new File(this.dataPath + "specials.csv");
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
                String[] fields = line.split("\\,", -1);
                Special i = new Special();
                i.setSpecialId(fields[0]); // added to Special.java
                i.setDealerId(fields[1]);
                i.setStartDate(new Date(Long.parseLong(fields[2])));
                i.setEndDate(new Date(Long.parseLong(fields[3])));
                i.setDiscountValue(Integer.parseInt(fields[4]));
                i.setDiscountPercent(Integer.parseInt(fields[5]));
                i.setValidOnCashPayment(Boolean.parseBoolean(fields[6]));
                i.setValidOnCheckPayment(Boolean.parseBoolean(fields[7]));
                i.setValidOnLoan(Boolean.parseBoolean(fields[8]));
                i.setValidOnLease(Boolean.parseBoolean(fields[9]));
                i.setValue(fields[10]);
                i.setYear(fields[11]);
                i.setBrand(fields[12]);
                i.setBodyType(fields[13]);
                i.setIsNew(fields[14]);
                i.setScopeParameter(fields[15]);
                // use single word only in special scopes for parsing purposes
                i.setScope(Arrays.asList(fields[16].split("\\s")));

                i.setTitle(unescaped[0]);
                i.setDescription(unescaped[1]);
                i.setDisclaimer(unescaped[2]);

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
     * @param special are the specials to be saved in the specials.csv
     */
    @Override
    public void writeSpecials(Special special) {
        File csv = new File(this.dataPath + "specials.csv");
        if (!csv.exists()) {
            try {csv.createNewFile(); } catch (IOException e) {e.printStackTrace();}
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csv,true));
            // create a new specials.csv and write each special into the file
            bw.write(special.toCSVLine());
            bw.newLine();
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
        String vehicleFilePath = this.dataPath + "vehicles.csv";

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
        String vehicleFilePath = this.dataPath + "vehicles.csv";
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