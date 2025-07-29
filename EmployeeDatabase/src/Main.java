import java.util.*;

public class Main {
    static List<Employee> emp=new ArrayList<>();
    public static void main(String[] args) {
        emp.add(new Employee(25, "Kavin", "IT", "Computer Science", "Shakthi"));
        emp.add(new Employee(23, "Vijay", "CSE", "Computer Science", "Prathima"));
        emp.add(new Employee(27, "Santhiya", "CSE", "Computer Science", "Prathima"));
        Scanner scan=new Scanner(System.in);
        while(true){
            System.out.println("1. All Employee details\n2. Search employee detail\n3. Employee under given manger name of the department\n4. reporting to tree of given employee name");
            int choose= scan.nextInt();;
            switch (choose){
                case 1:
                    for(Employee l:emp){
                       l.printDetails();
                    }
                    break;
                case 2:
                    System.out.println("Enter Your name: ");
                    String name1=scan.nextLine();
                    for(Employee l:emp){
                        if(l.name.equalsIgnoreCase(name1)){
                            l.printDetails();
                        }
                    }

                    break;
                case 3:
                    System.out.println("Enter Manager Name: ");
                    String manager=scan.nextLine();
                    System.out.print("Enter department: ");
                    String dept = scan.nextLine();
                    for(Employee l:emp){
                        if(l.manager.equalsIgnoreCase(manager) && l.department.equalsIgnoreCase(dept)){
                            l.printDetails();
                        }
                    }
                    break;
                case 4:
                    scan.nextLine();
                    System.out.print("Enter employee name: ");
                    String name = scan.nextLine();

                    Map<String, String> managerMap = new HashMap<>();
                    for (Employee e : emp) {
                        managerMap.put(e.name, e.manager);
                    }

                    String current = name;
                    while (managerMap.containsKey(current)) {
                        String mgr = managerMap.get(current);
                        if (mgr == null || mgr.equalsIgnoreCase("None")) break;
                        System.out.println(current + " reports to " + mgr);
                        current = mgr;
                    }
                    break;
                case 5:
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid...");
            }
        }
    }

}