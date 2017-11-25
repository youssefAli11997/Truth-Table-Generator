import java.util.ArrayList;

public class TruthTableGenerator {

    private Boolean[][] truthTable;
    private int rows;
    private int cols;
    private int vars;
    private Character[] symbols;


    public TruthTableGenerator(int rows, int cols, Character[] symbols){
        this.rows = rows;
        this.cols = cols;
        this.vars = cols-1;
        this.symbols = symbols;
        this.truthTable = new Boolean[this.rows][this.cols];
    }

    public Boolean[][] generateTruthTable(String expression){
        // 0 --> (2^vars)-1
        for(int subset = 0; subset<rows; subset++){
            String subsetBits = Integer.toBinaryString(subset);
            while(subsetBits.length() < vars){
                subsetBits = "0" + subsetBits;
            }
            System.out.println(subsetBits);
            for(int i=0; i<subsetBits.length(); i++){
                truthTable[subset][i] = (subsetBits.charAt(i) == '1');
            }
            int i=0;
            for(Character symbol : symbols){
                expression.replaceAll(symbol + "", subsetBits.charAt(i)+ "");
            }
            // Labib's Job (commented til being done).
            // Boolean truthValue = SomeClass.getTruthValue(expression);
            // truthTable[subset][cols-1] = truthValue;

            // For now
            truthTable[subset][cols-1] = new Boolean(true);
        }
        return truthTable;
    }
}