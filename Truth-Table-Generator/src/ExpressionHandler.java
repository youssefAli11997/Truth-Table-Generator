import java.util.HashMap;
import java.util.Map;

public class ExpressionHandler {

    private String expression;
    private Boolean[][] truthTable;
    private TruthTableGenerator truthTableGenerator;
    private Boolean tautology;
    private Boolean contradiction;
    private int numberOfVariables;
    private int rows;
    private int cols;
    private Character[] symbols;

    public ExpressionHandler(String expression) {
        this.expression = expression;
        symbols = initSymbols();
        truthTable = null;
        tautology = false;
        contradiction = false;
        numberOfVariables = initNumberOfVariables();
        cols = calcCols();
        rows = calcRows();
        truthTableGenerator = new TruthTableGenerator(rows, cols, symbols);
    }

    private Character[] initSymbols() {
        Map<Character, Integer> freq = getFreqMap();
        return freq.keySet().toArray(new Character[0]);
    }

    private int calcRows() {
        int pow2 = 1;
        for (int i = 0; i < numberOfVariables; i++)
            pow2 *= 2;
        return pow2;
    }

    private int calcCols() {
        return numberOfVariables + 1;
    }

    private Map<Character, Integer> getFreqMap() {
        Map<Character, Integer> freq = new HashMap<>();
        for (Character c : expression.toCharArray()) {
            if (Character.isAlphabetic(c) && !c.equals('v') && !c.equals('V')) {
                if (freq.containsKey(c)) {
                    freq.put(c, freq.get(c) + 1);
                } else {
                    freq.put(c, 1);
                }
            }
        }
        return freq;
    }

    private int initNumberOfVariables() {
        Map<Character, Integer> freq = getFreqMap();
        return freq.size();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void initTruthTable() {
        truthTable = truthTableGenerator.generateTruthTable(this.expression);
    }

    public Boolean[][] getTruthTable() {
        if (truthTable == null)
            initTruthTable();
        return truthTable;
    }

    public Character[] getSymbols() {
        return symbols;
    }

    public int getNumberOfVariables() {
        return numberOfVariables;
    }

    private void testTautology() {
        for (Boolean[] row : truthTable) {
            if (row[cols - 1].equals(false)) {
                tautology = false;
                return;
            }
        }
        tautology = true;
    }

    private void testContradiction() {
        for (Boolean[] row : truthTable) {
            if (row[cols - 1].equals(true)) {
                contradiction = false;
                return;
            }
        }
        contradiction = true;
    }

    public Boolean isTautology() {
        if (tautology == null)
            testTautology();
        return tautology;
    }

    public Boolean isContradiction() {
        if (contradiction == null)
            testContradiction();
        return contradiction;
    }

    public Boolean isEquivalence(Boolean[][] comparedTruthTable){
        return truthTableGenerator.testEquivalance(truthTable, comparedTruthTable);
    }
}
