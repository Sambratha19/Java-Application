public class Cell {
    String expression; //"A1 5 +"
    Double value;
    boolean isEvaluating;

    public Cell(String expression) {
        this.expression = expression;
        this.value=null;
        this.isEvaluating=false;
    }
}
