import java.util.*;

public class Main {
    static List<Customer> db=new ArrayList<>();
    static{
        db.add(new Customer("cust101", "Bcd234", 5000));
        db.add(new Customer("cust102", "Aa0", 7500));
        db.add(new Customer("cust103", "Ifmmp3135", 3000));
        db.add(new Customer("cust104", "bC2a", 10000));
        db.add(new Customer("cust105", "Tbgf098", 8500));
    }

    static List<GiftCard> gift=new ArrayList<>();
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        boolean log=false; String cust="", encrypt="";
        while(true){

            if(!log) {
                System.out.println("Enter CustomerID: ");
                String custId = scan.nextLine();
                System.out.println("Enter Password: ");
                String pass = scan.nextLine();
                cust=custId;
                encrypt = encrypt(pass);
                for(Customer c:db){
                    if(c.custId.equals(custId) && c.pass.equals(encrypt)){
                        log=true;
                        break;
                    }
                }
                if(!log){
                    System.out.println("Invalid");
                    System.exit(0);
                    scan.close();
                }
            }else{
                System.out.println("1. Create gift Card\n2. TopUp\n3. Transaction History\n4. Block Account\n5. Logout\n6. Redeem Reward\n7. EXIT");
                int input= scan.nextInt();
                switch (input){
                    case 1:
                        GiftCard giftCard=new GiftCard();
                        System.out.println("Create card...");
                        System.out.println("Card Number: " +GiftCard.getCardNo());
                        System.out.println("PIN: " + GiftCard.getPin());
                        gift.add(giftCard);
                        break;
                    case 2:
                        GiftCard giftCard1=new GiftCard();
                        System.out.println("TopUp");
                        System.out.println("Enter Top Up amount: ");
                        int amount= scan.nextInt();
                        if(!giftCard1.topUp(amount)){
                            System.out.println("Successfully topuped");
                        }else{
                            System.out.println("No sufficient balance");
                        }
                        break;
                    case 3:
                        GiftCard giftCard11=new GiftCard();
                        giftCard11.printTransactionHistory();
                        break;
                    case 4:

                        break;
                    case 5:
                        System.out.println("Logout");
                        log=false;
                        scan.nextLine();
                        break;
                    case 6:
                        scan.nextLine(); // consume newline
                        System.out.println("Enter Gift Card Number:");
                        int cardNo = scan.nextInt();

                        System.out.println("Enter PIN:");
                        int pin = scan.nextInt();

                        GiftCard selectedCard = null;

                        // find gift card
                        for (GiftCard g : gift) {
                            if (g.getCardNo()==(cardNo) && g.getPin()==(pin)) {
                                selectedCard = g;
                                break;
                            }
                        }

                        if (selectedCard == null) {
                            System.out.println("Invalid card number or PIN.");
                            break;
                        }

                        if (selectedCard.isBlocked()) {
                            System.out.println("This gift card is blocked.");
                            break;
                        }

                        System.out.println("Enter purchase amount:");
                        int purchaseAmount = scan.nextInt();

                        boolean purchaseSuccess = selectedCard.purchase(purchaseAmount);

                        if(purchaseSuccess) {
                            // Optionally redeem points automatically here or in a separate option
                            selectedCard.redeemPoints();
                        }

                        break;
                    case 7:
                        System.out.println("Thack You...");
                        System.exit(0);
                        scan.close();
                        break;
                    default:
                        System.out.println("Invalid...");break;
                }
            }

        }
    }


    public static String encrypt(String pass){
        String res="";
        for(int i=0;i<pass.length();i++){
            int num=(int) pass.charAt(i);

            if(num==90 || num==122){
                num-=25;
            }else if(num==57){
                num-=9;
            }else{
                num+=1;
            }
            res+=""+(char)num;

        }
        return res;
    }
}