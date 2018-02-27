package infixevaluatergui;


public class InfixEvaluater {
    private String infixExp;
    public StringBuilder process = null;
    public InfixEvaluater(String input) {
        infixExp  = input;
    }

    public String getInfixExp() {
        return infixExp;
    }

    public void setInfixExp(String infixExp) {
        this.infixExp = infixExp;
    }

    public String getResult() throws Exception{
        String pattern, result;
        StringTokenizer tokenizer;
        pattern   = "*+-";
        tokenizer = new StringTokenizer(infixExp, pattern, true);
        EvaluateExpression evaluater = new EvaluateExpression(tokenizer, pattern);
        process = evaluater.line; 
        result    = evaluater.result();
        return result;
    }
    
    public StringBuilder getProcess() {
    	return process;
    }
}
