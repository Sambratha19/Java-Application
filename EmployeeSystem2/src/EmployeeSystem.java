import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;



public class EmployeeSystem {

    static List<Employee> employees = new ArrayList<>();

    static {
        employees.add(new Employee(101, "Alice Johnson", 29, "Software Engineer", "IT", "David Thompson"));
        employees.add(new Employee(102, "Brian Lee", 34, "Senior Developer", "IT", "David Thompson"));
        employees.add(new Employee(103, "Clara Smith", 26, "UX Designer", "Design", "Megan Brooks"));
        employees.add(new Employee(104, "Daniel Kumar", 41, "Project Manager", "IT", "David Thompson"));
        employees.add(new Employee(105, "Eva Martinez", 30, "QA Engineer", "Quality Assurance", "Rachel Allen"));
        employees.add(new Employee(106, "Frank Wright", 38, "HR Manager", "Human Resources", "Jennifer White"));
        employees.add(new Employee(107, "Grace Liu", 27, "Content Strategist", "Marketing", "Tom Richards"));
        employees.add(new Employee(108, "Henry Patel", 33, "Business Analyst", "Product", "Sarah Green"));
        employees.add(new Employee(109, "Ivy Zhao", 25, "Data Scientist", "Analytics", "Kevin Moore"));
        employees.add(new Employee(110, "Jack Wilson", 45, "Finance Controller", "Finance", "Emma Carter"));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a command (SHOW_ALL, QUERY, HIERARCHY, REPORTING_TO, SUMMARY, EXIT):");
            String command = scanner.nextLine().trim().toUpperCase();

            switch (command) {
                case "SHOW_ALL":
                    employees.forEach(System.out::println);
                    break;
                case "QUERY":
                    handleQuery(scanner);
                    break;
                case "HIERARCHY":
                    System.out.println("Enter employee name:");
                    String emp = scanner.nextLine();
                    showHierarchy(emp);
                    break;
                case "REPORTING_TO":
                    System.out.println("Enter manager name:");
                    String mgr = scanner.nextLine();
                    showReportees(mgr);
                    break;
                case "SUMMARY":
                    showSummary();
                    break;
                case "EXIT":
                    return;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }

    static void handleQuery(Scanner scanner) {
        System.out.println("Enter query (e.g., age > 30 and department contains IT):");
        String query = scanner.nextLine();

        Predicate<Employee> predicate = e -> true;
        for (String condition : query.split("and")) {
            condition = condition.trim();

            if (condition.startsWith("age")) {
                String[] parts = condition.split(" ");
                int value = Integer.parseInt(parts[2]);
                switch (parts[1]) {
                    case ">": predicate = predicate.and(e -> e.age > value); break;
                    case "<": predicate = predicate.and(e -> e.age < value); break;
                    case "==": predicate = predicate.and(e -> e.age == value); break;
                    case "!=": predicate = predicate.and(e -> e.age != value); break;
                }
            } else {
                String[] parts = condition.split(" ", 3);
                String field = parts[0].toLowerCase();
                String operator = parts[1].toLowerCase();
                String value = parts[2].toLowerCase();

                predicate = predicate.and(e -> {
                    String fieldValue = switch (field) {
                        case "name" -> e.name.toLowerCase();
                        case "designation" -> e.designation.toLowerCase();
                        case "department" -> e.department.toLowerCase();
                        case "reporting" -> e.ReportTo.toLowerCase();
                        default -> "";
                    };
                    return switch (operator) {
                        case "startswith" -> fieldValue.startsWith(value);
                        case "endswith" -> fieldValue.endsWith(value);
                        case "contains" -> fieldValue.contains(value);
                        case "notcontains" -> !fieldValue.contains(value);
                        case "equals" -> fieldValue.equals(value);
                        case "notequals" -> !fieldValue.equals(value);
                        default -> false;
                    };
                });
            }
        }

        employees.stream().filter(predicate).forEach(System.out::println);
    }

    static void showHierarchy(String name) {
        Set<String> seen = new HashSet<>();
        String current = name;
        while (current != null && !seen.contains(current)) {
            seen.add(current);
            System.out.print(current);
            String finalCurrent = current;
            Optional<Employee> e = employees.stream().filter(emp -> emp.name.equalsIgnoreCase(finalCurrent)).findFirst();
            if (e.isPresent()) {
                current = e.get().ReportTo;
                if (current != null && !seen.contains(current)) System.out.print(" -> ");
            } else break;
        }
        System.out.println();
    }

    static void showReportees(String manager) {
        employees.stream()
                .filter(e -> e.ReportTo.equalsIgnoreCase(manager))
                .forEach(System.out::println);
    }

    static void showSummary() {
        System.out.println("Summary by Department:");
        employees.stream().collect(Collectors.groupingBy(e -> e.department, Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("\nSummary by Designation:");
        employees.stream().collect(Collectors.groupingBy(e -> e.designation, Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("\nSummary by Reporting To:");
        employees.stream().collect(Collectors.groupingBy(e -> e.ReportTo, Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
