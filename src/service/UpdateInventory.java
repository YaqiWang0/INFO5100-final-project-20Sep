package service;

import static dao.BodyType.CAR;

import dao.BodyType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateInventory {

  public static void updateInventoryByDealer(String vehicleId,String dealerId, String year,
      String brand, String model, boolean isNew,
      String price, String exteriorColor,
      String interiorColor, BodyType bodyType,
      String miles,String feature,String image){


    String tempFile = "data/temp.csv";
    String path = "data/vehicles.csv";
    File newFile = new File(tempFile);

    String line = "";

    try{
      //read
      BufferedReader br = new BufferedReader(new FileReader(path));
      //write
      FileWriter fw = new FileWriter(tempFile,true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);

      while ((line = br.readLine()) != null){
        String[] values = line.split(",");
        //update data by dealerId and VehicleId
        if (values[0].equals(vehicleId) && values[1].equals(dealerId)){
          //print new data
          pw.println( vehicleId+","+ dealerId+","+year+","+ brand+","+  model+","+  isNew+","+ price+","+  exteriorColor+","+ interiorColor+","+  bodyType+","+ miles+","+feature+","+ image);
        }else {
          //copy old data
          pw.println(values[0]+","+values[1]+","+values[2]+","+values[3]+","+values[4]+","+values[5]+","+values[6]+","+values[7]+","+values[8]+","+values[9]+","+values[10]+","+values[11]+","+values[12]);
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



  public static void main(String[] args) {
    updateInventoryByDealer("9b572b09-807f-4b7e-83f0-47473edb9366","gmps-aj-dohmann","9999","Benz","S",true,"62440.0","Black","Black",CAR,"0","2dr Cpe LT w/1LT	Test feature","http://.jpg");
  }

}
