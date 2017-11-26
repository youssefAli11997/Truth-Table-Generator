package Interfaces;

public interface ExpressionEvaluator {
    /*

        This is the method that will be called by the ExpressionHandler (rest methods could be private).
        This method should takes the expression with substituted values and get the truth value for it.
        This method should perform a call to infixToPostfix method to get the postfix version of the expression.
        The truth value of the expression should be calculated be performing a call to evaluate method.

     */
    Boolean getTruthValue(String expression);

    String infixToPostfix(String infixExpression);
    Boolean evaluate(String postfixExpression);
}
