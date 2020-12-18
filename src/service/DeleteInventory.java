package service;

import java.io.*;

public class DeleteInventory {
    public static void deleteInventoryByDealer(String vehicleId,String webId){
        String tempFile = "data/temp";
        String path = "data/"+webId;
        File newFile = new File(tempFile);

        String line = "";

        BufferedReader br=null;
        FileWriter fw =null;
        BufferedWriter bw= null;
        PrintWriter pw= null;
        try{
            //read
            br = new BufferedReader(new FileReader(path));
            //write
            fw = new FileWriter(tempFile,true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

//      pw.println();
            while ((line = br.readLine()) != null){
                String[] values = line.split("~");
                if (values[0].equals(vehicleId) && values[1].equals(webId)){
                    continue;
                }
                else {
                    pw.println(values[0] + "~" + values[1] + "~" + values[2] + "~" + values[3] + "~" + values[4] + "~" + values[5] + "~" + values[6] + "~" + values[7] + "~" + values[8] + "~" + values[9]);
                }
            }

            pw.flush();
            pw.close();
            //bw.close();
            //fw.close();
            br.close();
            File old = new File(path);
            old.delete();
            newFile.renameTo(old);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }




    }
}
