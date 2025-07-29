import java.util.HashMap;
import java.util.*;

public class SpreedSheet {
    static Map<String, Cell> cells=new HashMap<>();

    public void setCell(String cellName, String expression){
        cells.put(cellName, new Cell(expression));
    }

    public void printCell(){
        for(String key:cells.keySet()){
            System.out.println(key+": "+cells.get(key).expression+" => "+evaluate(key, true));
        }
    }

    public Double evaluate(String cellName, boolean usePrecedence){
        Cell cell=cells.get(cellName);
        if(cell==null) return null;

        if(cell.isEvaluating) throw new RuntimeException("Circular dependency detected at "+cellName);

        cell.isEvaluating=true;
        try{
            String expr=cell.expression;
            if(usePrecedence)
                cell.value = evaluateWithPrecedence(expr);
            else
                cell.value = evaluateWithoutPrecedence(expr);
            return cell.value;
        }catch(Exception e){
            System.out.println("Error in "+cellName+": "+e.getMessage());
            return null;
        }finally {
            cell.isEvaluating=false;
        }
    }
    private double evaluateWithPrecedence(String expr) {
        List<String> postfix = infixToPostfix(expr.split(" "));
        Stack<Double> stack = new Stack<>();
        for (String token : postfix) {
            if (isOperator(token)) {
                double b = stack.pop(), a = stack.pop();
                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/":
                        if (b == 0) throw new ArithmeticException("Division by zero");
                        stack.push(a / b); break;
                }
            } else {
                stack.push(getValue(token));
            }
        }
        return stack.pop();
    }

    private List<String> infixToPostfix(String[] tokens) {
        List<String> output = new ArrayList<>();
        Stack<String> ops = new Stack<>();
        Map<String, Integer> precedence = Map.of("+", 1, "-", 1, "*", 2, "/", 2);

        for (String token : tokens) {
            if (isOperator(token)) {
                while (!ops.isEmpty() && precedence.getOrDefault(ops.peek(), 0) >= precedence.get(token))
                    output.add(ops.pop());
                ops.push(token);
            } else {
                output.add(token);
            }
        }
        while (!ops.isEmpty()) output.add(ops.pop());
        return output;
    }

    private boolean isOperator(String token) {
        return "+-*/".contains(token);
    }


    private  double evaluateWithoutPrecedence(String expression){
        String token[]=expression.split(" ");
        double result=getValue(token[0]);

        for(int i=0;i<token.length;i++){
            String op=token[i];
            double val=getValue(token[i+1]);
            switch (op) {
                case "+": result += val; break;
                case "-": result -= val; break;
                case "*": result *= val; break;
                case "/":
                    if (val == 0) throw new ArithmeticException("Division by zero");
                    result /= val; break;
                default: throw new IllegalArgumentException("Invalid operator: " + op);
            }


        }
        return result;
    }
    private double getValue(String token) {
        if (cells.containsKey(token)) {
            Double val = evaluate(token, true);
            if (val == null) throw new RuntimeException("Cannot evaluate cell: " + token);
            return val;
        }
        return Double.parseDouble(token);
    }

    public void changeRangeIfCondition(List<String> cellRange, String conditionOp, double conditionVal, String newExpr) {
        for (String cellName : cellRange) {
            Double currentVal = evaluate(cellName, true);
            if (currentVal == null) continue;
            boolean shouldChange = switch (conditionOp) {
                case "<" -> currentVal < conditionVal;
                case ">" -> currentVal > conditionVal;
                case "==" -> currentVal == conditionVal;
                default -> false;
            };
            if (shouldChange) {
                setCell(cellName, newExpr);
            }
        }
    }
}
