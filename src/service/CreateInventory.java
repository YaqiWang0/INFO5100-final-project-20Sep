package service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateInventory {
    public static void createInventoryByDealer(String vehicleId,String webId, String category,
                                               String year, String brand, String model,
                                               String features, String type,String price, String photo
    ){



        String tempFile = "data/temp";
        String path = "data/"+webId;
        File newFile = new File(tempFile);

        String line = "";

        try{
            //read
            BufferedReader br = new BufferedReader(new FileReader(path));
            //write
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

//      pw.println();
            while ((line = br.readLine()) != null){
                String[] values = line.split("~");
                pw.println(values[0]+"~"+values[1]+"~"+values[2]+"~"+values[3]+"~"+values[4]+"~"+values[5]+"~"+values[6]+"~"+values[7]+"~"+values[8]+"~"+values[9]);
            }

//            bw.newLine();
//            if(!isImage(photo)){
//                System.out.println("false");
//                pw.println(vehicleId + "~" + webId + "~" + category + "~" + year + "~" + brand + "~" + model + "~" + features + "~" + type + "~" + price+"~" + "http://inventory-dmg.assets-cdk.com/6/7/1/14217691176x90.jpg");
//            }
//            else{
//                System.out.println("true");
//            String photoUrl = returnImage(photo);
            pw.println(vehicleId + "~" + webId + "~" + category + "~" + year + "~" + brand + "~" + model + "~" + features + "~" + type + "~" + price+"~" + photo);
//            }

            pw.flush();
            pw.close();
            File old = new File(path);
            old.delete();
            newFile.renameTo(old);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

//    public static String returnImage(String vehicleImagePath) throws IOException {
//        String result = vehicleImagePath;
//        ImageIcon imageIcon;
//
//        try {
//            URL url = new URL(vehicleImagePath);
//            Image image = ImageIO.read(url);
//        } catch (MalformedURLException e) {
//            //e.printStackTrace();
//            result = "http://inventory-dmg.assets-cdk.com/6/7/1/14217691176x90.jpg";
//        } catch (IOException e) {
//            result = "http://inventory-dmg.assets-cdk.com/6/7/1/14217691176x90.jpg";
//        }
//
//        return result;
//    }

}
