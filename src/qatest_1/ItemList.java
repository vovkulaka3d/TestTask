package qatest_1;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ItemList {
    
    private static String csv = "inputfile//data.csv";
    private static String rpt = "outputfile//report.txt";
    
    public static ArrayList<Item> getAllItems (){
       ArrayList <Item> items= new ArrayList <>();
          
       
       try (CSVReader reader = new CSVReader(new FileReader(csv), ',' , '"' , 0)){
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
        
        try (FileWriter writer = new FileWriter(csv, false)){
            for(int i = 0; i < itemsList.size();i++){
                writer.write(itemsList.get(i).toString() + System.getProperty("line.separator"));
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public static void setReaport (ArrayList<Item> itemsList){
        
        float totalSellPrice = 0;
        float totalBasePrice = 0;
        float itemPurchasePrice = 0;
        try (FileWriter writer = new FileWriter(rpt, false)){
            for(int i=0; i<itemsList.size(); i++){
                writer.write(itemsList.get(i).itemName + " продано " + itemsList.get(i).itemSoldQuantity + " шт. ");
                writer.write(" закуплено " + itemsList.get(i).itemBuyQuantity + " шт." + System.getProperty("line.separator"));
                totalSellPrice += itemsList.get(i).totalPrice;
                totalBasePrice += itemsList.get(i).itemSoldQuantity*itemsList.get(i).itemPrice;
                itemPurchasePrice += itemsList.get(i).itemBuyQuantity*itemsList.get(i).itemPrice;
            }
            writer.write(System.getProperty("line.separator") + "Прибыль магазина от продаж составила " + (totalSellPrice-totalBasePrice) + System.getProperty("line.separator"));
            writer.write("Затраченные средства на дозакупку товара составила " + itemPurchasePrice);
            } 
            catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            
    }
}
