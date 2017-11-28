
package qatest_1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trading {
    
    // Публічний список товарів 
    ArrayList<Item> itemsList = ItemList.getAllItems();

    
    
    public ArrayList<Item> getItemsList() {
        return itemsList;
    }
    
    
    // Умови визначення ціни
    public float itemsPrice(float startCost, int amount, int time, int day){
            
            float price = 0;
            float priceForAll = 0;
            
            if ((8<=time)&&(time<18)){
                if ( (day == 5)|| (day == 6)){
                    price = startCost * (float)1.15;
                } else {
                    price =  startCost * (float)1.10;
                }
            } else { if ((18<=time)&&(time<=20))
                price =  startCost * (float)1.08;
            }
            
            if (amount <= 2){
                priceForAll = price * amount;
            } else {
                priceForAll = price * 2 + startCost * (amount-2)*(float)1.07;
            }
        return  priceForAll;
    }
   //Докупка товаров
     public void dayEnd (){
        Item item = new Item();
        for(int i=0; i < itemsList.size(); i++){
            if(itemsList.get(i).itemAmount < 10 ){
                item = itemsList.get(i);
                item.itemAmount = item.itemAmount + 150;
                itemsList.add(i, item);
                itemsList.remove(i+1);
            }
        }
     }
    
    // Товарооборот за день
     
    public void oneDay (int day){
        Item item = null;
        for(int i=8; i<21; i++){
            Random random = new Random();
            int customers = random.nextInt(10);
            for(int j=0; j<=customers; j++){                          //1->10
                int itemId = random.nextInt(itemsList.size());
                int amount = random.nextInt(11);                      //0->10
                if (amount <= itemsList.get(itemId).itemAmount){
                    
                    System.out.println(itemsList);
                    
                    item = itemsList.get(itemId);
                    item.itemAmount = itemsList.get(itemId).itemAmount - amount;// покупем выпавшее количество товаров
                    
                    
                    itemsList.add(itemId, item);
                    itemsList.remove(itemId+1);
                    
                    
                    System.out.println(itemsPrice(itemsList.get(itemId).itemPrice, amount, i, day));
                    System.out.println(amount);
                    System.out.println(itemId);
                    System.out.println(itemsList);
                    
                } else {
                    System.out.println("Нет в наличии достаточного количества товаров");
                }
            }
        }
        dayEnd();
    }
    // Товарооборот за месяц
    
    public void oneMonse (){
        for(int i=0;i <2; i++){
            oneDay(i%7);
        }
        ItemList.setAllItems(itemsList);
    }
}
