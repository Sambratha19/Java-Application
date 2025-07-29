public class Booking {
    String riderId;
    String pickupTime;
    String dropTime;
    int amount;
    int customerId;

    Booking(String riderId,String pickupTime, String dropTime, int amount, int customerId){
        this.amount=amount;
        this.pickupTime=pickupTime;
        this.dropTime=dropTime;
        this.riderId=riderId;
        this.customerId=customerId;
    }

    public String getRiderId() {
        return riderId;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public String getDropTime() {
        return dropTime;
    }

    public int getAmount() {
        return amount;
    }

    public int getCustomerId() {
        return customerId;
    }
}
