import java.util.*;
public class Main {

    public static void main(String[] args) {
        SpreedSheet sheet = new SpreedSheet();

        sheet.setCell("A1", "10");
        sheet.setCell("A2", "A1 5 +");
        sheet.setCell("B1", "A2 2 *");
        sheet.setCell("C1", "B1 A1 /");

        System.out.println("\n--- Without precedence ---");
        sheet.printCell();

        System.out.println("\n--- Change if value > 20 ---");
        sheet.changeRangeIfCondition(List.of("B1", "C1"), ">", 20, "5");
        sheet.printCell();
    }

}