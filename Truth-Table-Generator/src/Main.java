import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        ExpressionHandler expressionHandler = new ExpressionHandler(expression);

        System.out.println(expressionHandler.getNumberOfVariables());

        Boolean[][] truthTable = expressionHandler.getTruthTable();

        Character[] symbols = expressionHandler.getSymbols();

        for(Character symbol : symbols){
            System.out.print(symbol + "  ");
        }
        System.out.println("#");

        for(Boolean[] row : truthTable){
            for(Boolean cell : row){
                if(cell.equals(false)){
                    System.out.print("F |");
                }
                else{
                    System.out.print("T |");
                }
            }
            System.out.println();
        }

    }
}
