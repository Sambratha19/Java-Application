import java.util.LinkedList;
import java.util.*;

public class Main {
    static List<Items> itemsList=new LinkedList<>();
    static List<Items> addtocart=new LinkedList<>();

    static Authentication authentication=new Authentication();
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Register: ");
        authentication.register(scanner);
        System.out.println("Login: ");
        String email=authentication.login(scanner);
        char category=authentication.getCategory();
        if(email!="null" && email.length()!=0){
            if(category=='S'){
                listInventory();
                while(true){
                    System.out.println("1. addItem\n2. updateItem\n3. Exit");
                    int choose=scanner.nextInt();
                    switch (choose){
                        case 1:
                            addItem(scanner);
                            break;
                        case 2:
                            updateItem(scanner);
                            break;
                        case 3:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid choose....");
                            break;
                    }
                }

            }else {
                while (true){
                    listInventory();
                    System.out.println("1. Add to Cart\n2. deleteItem\n3. BuyItem\n4. Exit");
                    int choose= scanner.nextInt();
                    switch (choose){
                        case 1:
                            addToCart(scanner);
                            break;
                        case 2:
                            deleteFromCart(scanner);
                            break;
                        case 3:
                            buyItem(scanner);
                        case 4:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid..");
                            break;
                    }
                }
            }
        }else{
            System.out.println("User not found");
        }

    }
    public static void buyItem(Scanner scanner){
        System.out.println("Y/N");
        char choose=scanner.next().toUpperCase().charAt(0);
        makePayment(scanner);
    }


    public static void makePayment(Scanner scanner){
        System.out.println("Choose payment option\n1. UPI\n2. QR");
        int choose= scanner.nextInt();
        switch (choose){
            case 1:
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid");
                break;
        }
    }
    public static void addToCart(Scanner scanner){
        System.out.println("Enter id to cart: ");
        int id=scanner.nextInt();
        for(Items items:itemsList){
            if(items.getItemId()==id){
                System.out.println("Quantity needed: ");
                int quantity=scanner.nextInt();
                addtocart.add(new Items(items.getItemId(), items.getItemName(), quantity, items.getPrice()*quantity));
                int idItem=items.getItemId();
                itemsList.remove(items);
                itemsList.add(new Items(items.getItemId(), items.getItemName(), items.getStock()-quantity, items.getPrice()));
                break;
            }
        }
    }

    public static void deleteFromCart(Scanner scanner){
        System.out.println("Enter Id: ");
        int id=scanner.nextInt();
        for(Items items:itemsList){
            if(items.getItemId()==id){
                itemsList.remove(items);
            }
        }
    }

    public static void addItem(Scanner scanner){
        System.out.println("Enter item Name: ");String name=scanner.nextLine();
        System.out.println("No. of Stock: ");int stock=scanner.nextInt();
        System.out.println("Price: ");int price= scanner.nextInt();

        itemsList.add(new Items(name, stock, price));
    }

    public static void updateItem(Scanner scanner){
        System.out.println("Enter which Id to modify: ");
        int id= scanner.nextInt();
        for(Items items:itemsList){
            if(items.getItemId()==id){
                itemsList.remove(items);
            }
        }
        addItem(scanner);

    }


    public static void listInventory(){
        System.out.println("ID\tNAME\tSTOCK\tPRICE");
        for(Items items:itemsList){
            System.out.println(items.getItemId()+"\t"+items.getItemName()+"\t"+items.getStock()+"\t"+items.getPrice());
        }
    }
}