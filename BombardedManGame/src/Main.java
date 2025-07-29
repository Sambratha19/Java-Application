
import java.util.*;

public class Main {
    static HashMap<String, Integer> setGet=new HashMap<String, Integer>();
    static boolean begin=false;
    static HashMap<String, Integer> permanent=new HashMap<>();
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        while(true){
            System.out.println("1. SET\n2. GET\n3. UNSET\n4. COUNT NUMBER\n5. BEGIN\n6. ROLLBACK\n7. COMMIT\n8. EXIT");
            int input= scan.nextInt();

            switch(input){
                case 1:
                    System.out.println("Set value to a variable: ");
                    System.out.print("Variable: ");String variable=scan.next();
                    System.out.print("Value: ");Integer value=scan.nextInt();
                    setGet.put(variable,value);
                    if(begin){

                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Get a value from variable: ");String variableGet= scan.next();
                    if(setGet.containsKey(variableGet)){
                        System.out.println(setGet.get(variableGet));
                    }else{
                        System.out.println("Not found");
                    }
                    break;
                case 3:
                    System.out.println("Unset a variable: ");
                    String variableUnset= scan.next();
                    if(setGet.containsKey(variableUnset)){
                        setGet.remove(variableUnset);
                    }else{
                        System.out.println("Not found");
                    }
                    break;
                case 4:
                    System.out.println("Get Count of Value: ");
                    int valueGet= scan.nextInt();
                    int count=0;
                    for(Map.Entry<String, Integer> get1: setGet.entrySet()){
                        if(get1.getValue()==valueGet){
                            count++;
                        }
                    }
                    System.out.println("Count= "+count);
                    break;
                case 5:
                    System.out.println("Begin: ");
                    setGet.clear();
                    begin=true;

                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.exit(0);
                    scan.close();
                    break;

            }
        }
    }
}