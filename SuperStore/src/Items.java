public class Items {
    int itemId;
    static int i=1;
    int stock;
    String itemName;
    int price;
    Items(String itemName, int stock, int price){
        this.itemId=i++;
        this.itemName=itemName;
        this.stock=stock;
        this.price=price;
    }

    Items(int id, String itemName, int stock, int price){
        this.itemId=id;
        this.itemName=itemName;
        this.stock=stock;
        this.price=price;
    }

    public int getItemId() {
        return itemId;
    }

    public int getStock() {
        return stock;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }
}
