package Model;

import Interfaces.ExpressionEvaluator;

public class TruthTableHandler {

    private Boolean[][] truthTable;
    private int rows;
    private int cols;
    private int vars;
    private Character[] symbols;
    private ExpressionEvaluator expressionEvaluator;

    public TruthTableHandler(int rows, int cols, Character[] symbols){
        this.rows = rows;
        this.cols = cols;
        this.vars = cols-1;
        this.symbols = symbols;
        this.truthTable = new Boolean[this.rows][this.cols];
        this.expressionEvaluator = new MyExpressionEvaluator();
    }

    public Boolean[][] generateTruthTable(String expression){
        // 0 --> (2^vars)-1
        for(int subset = 0; subset<rows; subset++){
            String subsetBits = Integer.toBinaryString(subset);
            String substitutedExpression = expression;
            while(subsetBits.length() < vars){
                subsetBits = "0" + subsetBits;
            }
            for(int i=0; i<subsetBits.length(); i++){
                truthTable[subset][i] = (subsetBits.charAt(i) == '1');
            }
            int i=0;
            for(Character symbol : symbols){
                substitutedExpression = substitutedExpression.replaceAll(symbol + "", subsetBits.charAt(i++)+ "");
            }

            Boolean truthValue = expressionEvaluator.getTruthValue(substitutedExpression);
            truthTable[subset][cols-1] = truthValue;
        }
        return truthTable;
    }

    public Boolean testEquivalence(Boolean[][] truthTable, Boolean[][] comparedTruthTable) {
        if(truthTable.length != comparedTruthTable.length)
            return false;
        for(int i=0; i<truthTable.length; i++){
            if(!truthTable[i][truthTable[0].length-1].equals(comparedTruthTable[i][comparedTruthTable[0].length-1]))
                return false;
        }
        return true;
    }
}