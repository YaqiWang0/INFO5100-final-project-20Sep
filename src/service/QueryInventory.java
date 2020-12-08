package service;

import static dao.BodyType.CAR;

import dao.BodyType;
import dao.Vehicle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueryInventory {

//  public List<Vehicle> inventory;

  public static List<Vehicle> queryInventoryByDealer(String dealerId){
    String path = "data/vehicles.csv";
    String line = "";
    List<Vehicle> inventory = new ArrayList<Vehicle>();

    try{
      BufferedReader br = new BufferedReader(new FileReader(path));

      while ((line = br.readLine()) != null){
        String[] values = line.split(",");
        if (values[1].equals(dealerId) ){
          Vehicle vehicle = new Vehicle(values[1],values[2],values[3],values[4],values[5]=="true",values[6],values[7],values[8], BodyType.valueOf(values[9]),values[10]);
          inventory.add(vehicle);
          System.out.println(inventory.get(0).getDealerId());
        }

      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e){
      e.printStackTrace();
    }

    return inventory;
  }




  public static void main(String[] args) {
    queryInventoryByDealer("gmps-aj2");
//    updateInventoryByDealer("070853c1-750c-406c-9679-201bd8fa7c47","gmps-aj-dohmann","9999","BMW","S",true,"62440.0","Black","Black",CAR,"0","2dr Cpe LT w/1LT	Test feature","http://inventory-dmg.assets-cdk.com/4/6/5/13411476564x90.jpg	http://inventory-dmg.assets-cdk.com/2/3/5/13411476532x90.jpg");
  }

}
