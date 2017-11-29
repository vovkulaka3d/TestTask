package qatest_1;

public class Item {
    
    String itemName;
    float itemPrice;
    String itemVolume;
    String itemDescription;
    String itemComposit;
    int itemAmount;
    
    int itemSoldQuantity;
    int itemBuyQuantity;
    float totalPrice;
    

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemVolume(String itemVolume) {
        this.itemVolume = itemVolume;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    
    public void setItemComposit(String itemComposit) {
        this.itemComposit = itemComposit;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    
    public String getItemName() {
        return itemName;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public String getItemVolume() {
        return itemVolume;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemComposit() {
        return itemComposit;
    }
    public int getItemAmount() {
        return itemAmount;
    }
    
    @Override
    public String toString() {
        char dm = (char) 34; 
        return  dm + itemName + dm + ", " + 
                     itemPrice + ", " +
                dm + itemDescription + dm + ", " +
                     itemVolume + ", " +
                dm + itemComposit + dm + ", " +
                     itemAmount;
    }
}
