public class Employee {
    int id;
    String name;
    int age;
    String designation;
    String department;
    String ReportTo;

    public Employee(int id, String name, int age, String designation, String department, String reportTo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.designation = designation;
        this.department = department;
        ReportTo = reportTo;
    }

    public String toString() {
        return String.format("%d\t%s\t%d\t%s\t%s\t%s", id, name, age, designation, department, ReportTo);
    }
}
