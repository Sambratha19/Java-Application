import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Function extends DatabseAccess{

    public List<Item> search(String item){
        List<Item> item_list=new LinkedList<>();
        try {
            PreparedStatement search=con.prepareStatement("SELECT * FROM product_item WHERE LOWER(product_name) LIKE ?");
            search.setString(1, "%"+item.toLowerCase()+"%");
            ResultSet resultSet=search.executeQuery();

            while (resultSet.next()){
                item_list.add(new Item(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item_list;
    }

    public void cart(String email, List<Item> cart){
        try {
            Statement statement=con.createStatement();
            ResultSet rs=statement.executeQuery("SELECT* FROM user_details");
            boolean found=false;
            while (rs.next()) {
                if(rs.getString("email").equals(email)){
                    found=true;
                }
            }
            PreparedStatement add_cart=con.prepareStatement("INSERT INTO cart(email, cart_list) values(?, ?)");
            String str="";
            for(Item i:cart){
                str+=""+i.getProduct_id()+" "+i.getProduct_name()+" "+i.getPrice()+"\n";
            }
            add_cart.setString(1, email);
            add_cart.setString(2, str);
            add_cart.executeUpdate();
            if(found){

            }else{
                System.out.println("Email not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update_cart(String email, Scanner scanner){
        try {
            Statement statement=con.createStatement();
            ResultSet rs=statement.executeQuery("select * from cart");
            String cart="";
            while (rs.next()){
                if(rs.getString("email").equals(email)){
                    cart=rs.getString("cart_list");
                    break;
                }
            }

            String[] list_cart=cart.split("\n");
            System.out.println(Arrays.toString(list_cart));
            System.out.println("Enter Id which one to modify");
            int id= scanner.nextInt();
            String sId=String.valueOf(id);
            int length=sId.length();
            int j=0;
            String result_cart="";
            for(int i=0;i<list_cart.length;i++){
                boolean b=false;
                while(j<list_cart[i].length()) {
                    String sub = list_cart[i].substring(0, j);
                    if(sub.length()>length) break;
                    else if(sub.length()==length && sub.equals(sId)){
                        b=true;
                        break;
                    }
                    j++;
                }

                if(!b){
                    result_cart+=list_cart[i]+"\n";
                }
            }

            PreparedStatement pre=con.prepareStatement("delete from cart where email link ?");
            pre.setString(1, email);
            pre.executeUpdate();
            PreparedStatement add=con.prepareStatement("insert into cart(email, cart_list) values (?, ?)");
            add.setString(1, email);
            add.setString(2, result_cart);
            add.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
