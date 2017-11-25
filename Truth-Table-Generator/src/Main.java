import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        ExpressionHandler expressionHandler = new ExpressionHandler(expression);

        System.out.println(expressionHandler.getNumberOfVariables());

        /*Boolean[][] truthTable = expressionHandler.getTruthTable();

        for(Boolean[] row : truthTable){
            for(Boolean cell : row){
                System.out.print(cell + " ");
            }
            System.out.println();
        }*/

    }
}
