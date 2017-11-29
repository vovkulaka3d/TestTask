package qatest_1;

import java.util.ArrayList;
import java.util.Random;

public class Trading {
   
    private static final String[] DAY_OF_WEEK = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Субота", "Воскресенье"};
    
    // Ініціалізуєм список товарів  список товарів 
    private final ArrayList<Item> itemsList = ItemList.getAllItems();
    public ArrayList<Item> getItemsList() {
        return itemsList;
    }
    
    
    // Умови визначення ціни
    public float itemsPrice(float startCost, int amount, int time, int day, int itemId){
            
            float price = 0;
            float priceForAll = 0;
            String markup = null;
            
            if ((8<=time)&&(time<18)){
                if ( (day == 5)|| (day == 6)){
                    price = startCost * (float)1.15;
                    markup = "15%";
                } else {
                    price =  startCost * (float)1.10;
                    markup = "10%";
                }
            } else { if ((18<=time)&&(time<=20)){
                        price =  startCost * (float)1.08;
                        markup = "8%";
                     }
            }
            
            if (amount <= 2){
                priceForAll = price * amount;
                System.out.println("Продано товара " + itemsList.get(itemId).itemName + 
                                   " в количестве " + amount + " шт., по цене " + price + 
                                   " с наценкой " + markup + " на общюю сумму " + priceForAll);
            } else {
                priceForAll = price * 2 + startCost * (amount-2)*(float)1.07;
                System.out.println("Продано товара " + itemsList.get(itemId).itemName + 
                                   " 2 шт., по цене " + price + 
                                   " с наценкой " + markup + 
                                   " и " + (amount-2) + " шт. товара по цене " + startCost *(float)1.07 + 
                                   " с наценкой 7%" + " на общюю сумму " + priceForAll);
            }
        return  priceForAll;
    }
    
  
    
    // Товарооборот за день 
    public void oneDay (int day){
        Item item = new Item();
        for(int i=8; i<21; i++){
            System.out.println("С " + i + ":00" + " до " + (i+1) + ":00");
            Random random = new Random();
            int customers = random.nextInt(10);
            for(int j=0; j<=customers; j++){                          //1->10
                int itemId = random.nextInt(itemsList.size());
                int amount = random.nextInt(11);                      //0->10
                if (amount <= itemsList.get(itemId).itemAmount){
                    item = itemsList.get(itemId);
                    
                    //формируем отчет
                    item.itemAmount = itemsList.get(itemId).itemAmount - amount;// покупем выпавшее количество товаров
                    item.itemSoldQuantity += amount;
                    item.totalPrice += itemsPrice(itemsList.get(itemId).itemPrice, amount, i, day, itemId);
                    itemsList.add(itemId, item);
                    itemsList.remove(itemId+1);
                } else {
                    //System.out.println("Не достаточное количество товара " + itemsList.get(itemId).itemName + " в магазине");
                }
            }
        }
        dayEnd();
    }
    
     //Докупка товаров
     public void dayEnd (){
        Item item = new Item();
        for(int i=0; i < itemsList.size(); i++){
            if(itemsList.get(i).itemAmount < 10 ){ //проверка наличия 10 штук товара
                item = itemsList.get(i);
                item.itemAmount += 150;
                item.itemBuyQuantity += 150;
                itemsList.add(i, item);
                itemsList.remove(i+1);
                
            }
        }
     }
    // Товарооборот за месяц
    
    public void oneMonse (){
        for(int i=0;i <30; i++){
            System.out.println(DAY_OF_WEEK[i%7]);
            oneDay(i%7);
        }
        ItemList.setAllItems(itemsList);
        ItemList.setReaport(itemsList);
    }
}
