/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qatest_1;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ItemList {
    
    private static String csv = "inputfile//data.csv";
    
    public static ArrayList<Item> getAllItems (){
       ArrayList <Item> items= new ArrayList <>();
       
       try {
            CSVReader reader = new CSVReader(new FileReader(csv), ',' , '"' , 0);
            String[] nextLine;
            while((nextLine = reader.readNext()) != null){
                Item item = new Item();
                if (nextLine != null){
                   item.setItemName(nextLine[0].trim());
                   item.setItemPrice(Float.parseFloat(nextLine[1].trim()));
                   item.setItemDescription(nextLine[2].trim());
                   item.setItemVolume(nextLine[3].trim());
                   item.setItemComposit(nextLine[4].trim());
                   item.setItemAmount(Integer.parseInt(nextLine[5].trim()));
                   
                   items.add(item);
                } 
            }
        } catch (IOException ex) {
            System.err.println("Can't found input file");
        }
    
       return items;
    }
    public static void setAllItems (ArrayList<Item> itemsList){
        
        try (FileWriter writer = new FileWriter(csv)){
            for(int i = 0; i < itemsList.size();i++){
                writer.write(itemsList.get(i).toString() + System.getProperty("line.separator"));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
