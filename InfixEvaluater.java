package infixevaluatergui;


import java.util.StringTokenizer;
public class InfixEvaluater {
    
    StringBuilder p                 = new StringBuilder();
    private Stack<String> operaters = new Stack<>();
    private Stack<String> values    = new Stack<>();
    private String infixExp;

    public InfixEvaluater(Stack<String> ops, Stack<String> val, String input) {
        operaters = ops;
        values    = val;
        infixExp  = input;
    }

    public String getInfixExp() {
        return infixExp;
    }

    public void setInfixExp(String infixExp) {
        this.infixExp = infixExp;
    }

    public Stack<String> getOperaters() {
        return operaters;
    }

    public void setOperaters(Stack<String> operaters) {
        this.operaters = operaters;
    }

    public Stack<String> getValues() {
        return values;
    }

    public void setValues(Stack<String> values) {
        this.values = values;
    }

    public String getResult() {
        String pattern, result;
        StringTokenizer tokenizer;
        pattern   = "*+-";
        tokenizer = new StringTokenizer(infixExp, pattern, true);
        EvaluateExpression evaluater = new ExpressionEvaluater(tokenizer, pattern);
        result    = evaluater.result();
        return result;
    }
}
