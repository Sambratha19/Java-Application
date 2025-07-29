public class Item {
    String product_name;
    int product_id;
    int price;
    Item( int id,String name, int price){
        this.price=price;
        this.product_id=id;
        this.product_name=name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getPrice() {
        return price;
    }


}
