public class Booking {

    int customerId;
    char pickup, drop;
    int pickupTime, dropTime;
    int amount;

    Booking(int cid, char p, char d, int pt, int dt, int amt) {

        this.customerId = cid;
        this.pickup = p;
        this.drop = d;
        this.pickupTime = pt;
        this.dropTime = dt;
        this.amount = amt;
    }
}
