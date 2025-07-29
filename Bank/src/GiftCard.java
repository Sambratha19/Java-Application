import java.util.*;

public class GiftCard {
    static int cardNo;
    static int pin;
    Customer cust;
    int giftBalance = 0;
    int rewardPoints=0;
    List<String> history = new ArrayList<>();
    GiftCard(Customer cust){
        this.cust=cust;
        this.pin=generatePin();this.cardNo=generateCardNo();
    }GiftCard(){

    }

    public int generateCardNo() {
        Random rand=new Random();
        cardNo=10000+rand.nextInt(90000);
        return cardNo;
    }

    public static int getPin() {
        return pin;
    }

    public static int getCardNo() {
        return cardNo;
    }

    public int generatePin() {
        Random rand=new Random();
        pin=1000+rand.nextInt(9000);
        return pin;
    }

    public boolean topUp(int amount){
        if(cust.Balance>=amount){
            cust.Balance-=amount;
            giftBalance+=amount;
            history.add("Top-up: ₹" + amount + " (Gift Balance: ₹" + giftBalance + ")");
            return true;
        }else{
            history.add("Top-up Failed: Insufficient main balance.");
            return false;
        }
    }

    public void printTransactionHistory() {
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction history for Card No: " + cardNo);
            for (String h : history) {
                System.out.println(" - " + h);
            }
        }
    }


    public boolean purchase(int amount){
        if(blocked){
            System.out.println("Gift card is blocked. Cannot make purchase.");
            return false;
        }
        if(giftBalance>=amount){
            giftBalance-=amount;
            rewardPoints+=amount/100;
            System.out.println("Purchase successful. Remaining balance: " + balance);
            return true;
        } else {
            System.out.println("Insufficient gift card balance.");
            return false;
        }
    }

    public void redeemPoints() {
        int redeemable = (rewardPoints / 10) * 10;  // for every 10 points, ₹10
        if (redeemable > 0) {
            cust.Balance += redeemable;
            rewardPoints -= (redeemable);  // reduce redeemed points
            System.out.println("Redeemed " + redeemable + " rupees to main account.");
        } else {
            System.out.println("Not enough reward points to redeem.");
        }
    }



    private boolean blocked = false;

    // getter for blocked
    public boolean isBlocked() {
        return blocked;
    }
    public void block() {
        if (!blocked) {
            blocked = true;
            // Transfer balance back to customer
            cust.Balance += this.giftBalance;
            this.giftBalance = 0;
            System.out.println("Gift card blocked. Balance transferred back to main account.");
        } else {
            System.out.println("Gift card is already blocked.");
        }
    }

    public String toString() {
        return "GiftCard [Card No=" + cardNo + ", Pin=" + pin + ", Gift Balance=₹" + giftBalance + "]";
    }
}
