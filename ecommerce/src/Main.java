import java.util.*;

public class Main {
    public static void main(String[] args) {
        //register
        //login
        List<Item> cart=new LinkedList<>();
        Function function=new Function();
        Auth auth=new Auth();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Register");
        auth.register(scanner);
        System.out.println("Login");
        String email=auth.login(scanner);
        if(email.length()==0 || email.equals("null")) {
            System.exit(0);
        }
        while(true) {
            System.out.println("1. search \n2. view cart\n3. update cart\n4. exit");
            int ch= scanner.nextInt();
            switch (ch) {
                case 1:
                    scanner.nextLine();
                    System.out.println("Search Item: ");
                    String search = scanner.nextLine().trim();
                    List<Item> items = function.search(search);
                    System.out.println("Search Result");
                    for (Item i : items) {
                        System.out.println(i.getProduct_id() + " " + i.getProduct_name());
                    }
                    System.out.println("-- To add Item in cart enter its ID no.. (y/n)");
                    char choose = scanner.next().toLowerCase().charAt(0);
                    while (choose == 'y') {
                        System.out.print("ID: ");
                        int item_id = scanner.nextInt();
                        System.out.print("Quality No..: ");
                        int quality = scanner.nextInt();
                        for (Item i : items) {
                            if (i.getProduct_id() == item_id) {
                                cart.add(new Item(i.getProduct_id(), i.getProduct_name(), i.getPrice() * quality));
                            }
                        }
                        System.out.print("Y to add item N to stop: ");
                        choose = scanner.next().toLowerCase().charAt(0);
                    }
                    if (choose == 'n') {
                        System.out.println("THANK YOU");
                    } else
                        System.out.println("Invalid");
                    if(cart.size()>0) function.cart(email, cart);
                    break;
                case 2:
                    System.out.println("View Cart");
                    for(Item carts:cart){
                        System.out.println(carts.getProduct_id()+" "+carts.getProduct_name()+" "+carts.getPrice());
                    }
                    break;
                case 3:
                    function.update_cart(email, scanner);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choose");
                    break;
            }
        }
    }
}