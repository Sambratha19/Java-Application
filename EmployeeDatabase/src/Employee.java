public class Employee {
    int age;
    String name;
    String designation;
    String department;
    String manager;
    Employee(int age, String name, String designation, String department, String manager){
        this.age=age;
        this.name=name;
        this.department=department;
        this.manager=manager;
        this.designation=designation;
    }

    public void printDetails() {
        System.out.println("Name: " + name + ", Age: " + age + ", Designation: " + designation +
                ", Department: " + department + ", Manager: " + manager);
    }

    public static void EmployeeDetails(String name){

    }

}
