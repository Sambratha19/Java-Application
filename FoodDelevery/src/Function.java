import java.util.ArrayList;
import java.util.*;

public class Function {
    static int N=5;
    static int riderAmount[]=new int[N];
    static String rider[]=new String[N];
    static int riderCount[]=new int[N];
    static Map<String, Character> riderAppointment=new HashMap<>();
    static
    {
        for(int i=0;i<N;i++){
            rider[i]="DE"+(i+1);
        }
    }

    static List<Booking> bookingList=new ArrayList<>();
    public static void assignOrder(Scanner scanner){
        System.out.print("Enter Restaurant: ");
        char restaurant=scanner.next().charAt(0);
        System.out.print("Enter Destination: ");
        char destination=scanner.next().charAt(0);
        scanner.nextLine();
        System.out.print("Time: ");
        String time=scanner.nextLine();
        Customer customer=new Customer(restaurant, destination, time);

        String pickupTime=pickup(time);
        String dropTime=drop(time);
        int selectRider=selectRider(restaurant);

        int commission=50, allowance=10;
        riderAmount[selectRider]+=commission;
        riderCount[selectRider]++;
        riderAppointment.put(rider[selectRider], destination);

        int customerId=customer.getId();
        bookingList.add(new Booking(rider[selectRider],pickupTime, dropTime, riderAmount[selectRider], customerId ));
    }


    public static void print(){
        for (Booking b:bookingList){
            System.out.println(b.getCustomerId()+" "+b.riderId+" "+b.pickupTime+" "+b.getDropTime()+" "+b.getAmount());
        }
    }

    private static int selectRider(char restaurant){
        int index=-1;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            char location=riderAppointment.getOrDefault(rider[i], '-');
            if(location==restaurant) {
                if (riderAmount[i] < min) {
                    min = riderAmount[i];
                    index = i;
                }
            }
        }


        if(index==-1){
            for (int i = 0; i < N; i++) {
                if (riderAmount[i] < min) {
                    min = riderAmount[i];
                    index = i;
                }
            }
        }
        return index;
    }


    private static String pickup(String time){
        String[] split=time.split(":");
        int hour=Integer.parseInt(split[0]);
        int min=Integer.parseInt(split[1]);

        min+=15;
        if(min>=60){
            min-=60;
            hour=(hour+1)%24;
        }
        String fHour="", fMin="";
        if(hour<=9){
            fHour=""+0+""+hour;
        }else{
            fHour=""+hour;
        }

        if(min<=9){
            fMin=""+0+""+min;
        }else{
            fMin=""+min;
        }

        String res=""+fHour+":"+fMin;
        return res;
    }


    private static String drop(String time){
        String[] split=time.split(":");
        int hour=Integer.parseInt(split[0]);
        int min=Integer.parseInt(split[1]);

        min+=30;
        if(min>=60){
            min-=60;
            hour=(hour+1)%24;
        }
        String fHour="", fMin="";
        if(hour<=9){
            fHour=""+0+""+hour;
        }else{
            fHour=""+hour;
        }

        if(min<=9){
            fMin=""+0+""+min;
        }else{
            fMin=""+min;
        }

        String res=""+fHour+":"+fMin;
        return res;
    }
}
