public class Customer {
    static int i=1;
    int id;
    char restaurant;
    char destination;
    String time;
    Customer(char restaurant, char destination, String time){
        this.id=i++;
        this.restaurant=restaurant;
        this.destination=destination;
        this.time=time;
    }

    public char getRestaurant() {
        return restaurant;
    }

    public char getDestination() {
        return destination;
    }

    public String getTime() {
        return time;
    }

    public int getId() {
        return id;
    }
}
