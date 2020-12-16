package service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateInventory {

  public static void updateInventoryByDealer(String vehicleId,String webId, String category,
                                             String year, String make, String model,
                                             String trim, String type,String price, String photo
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
        //update data by dealerId and VehicleId
        if (values[0].equals(vehicleId)){
          //print new data
          pw.println( values[0]+"~"+values[1]+"~"+ category+"~"+year+"~"+make+"~"+ model+"~"+  trim+"~"+ type+"~"+ price+"~"+ photo);
        }else {
          //copy old data
          pw.println(values[0]+"~"+values[1]+"~"+values[2]+"~"+values[3]+"~"+values[4]+"~"+values[5]+"~"+values[6]+"~"+values[7]+"~"+values[8]+"~"+values[9]);
        }

      }
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

  public static boolean isHttpUrl(String urls) {
    boolean isurl = false;
    String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
            + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";

    Pattern pat = Pattern.compile(regex.trim());
    Matcher mat = pat.matcher(urls.trim());
    isurl = mat.matches();
    if (isurl) {
      isurl = true;
    }
    return isurl;
  }

  public static void main(String[] args) {
    updateInventoryByDealer("2957999103","gmps-shaheen","used","999","Chevrolet","Equinox","FWD LS","SUV","22","http://inventory-dmg.assets-cdk.com/RTT/Chevrolet/2018/3436853/default/ext_G7Q_deg01x90.jpg");
  }

}
