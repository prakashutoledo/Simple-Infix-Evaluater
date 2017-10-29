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
        result    = evaluate(tokenizer, pattern);
        return result;
    }

    private String evaluate(StringTokenizer tokenizer, String pattern) {
        String token, thisOp, second, first, peekOp, result;

        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken().trim();
            if (token.contains(" ") || token.equals(" ")) {
                throw new PrefixException("Extra operands: operands left over");
            }
            if (!pattern.contains(token)) {
                values.push(token);
            } else if (pattern.contains(token)) {
                thisOp = token;
                while (!operaters.isEmpty() && (precedence(operaters.peek()) <= precedence(thisOp))) {
                    if (values.isEmpty()) {
                        throw new PrefixException("Stack under flow: Operaters left over");
                    }
                    second = values.pop();
                    if (values.isEmpty()) {
                        throw new PrefixException("Stack under flow: Operaters left over: ");
                    }
                    first = values.pop();
                    if (operaters.isEmpty()) {
                        throw new PrefixException("Operands left over: Stack under flow");
                    }
                    peekOp = operaters.pop();
                    result = getValue(second, peekOp, first);
                    values.push(result);
                }
                operaters.push(thisOp);
            }
        }
        while (!operaters.isEmpty()) {
            if (values.isEmpty()) {
                throw new PrefixException("Not enough operands: stack underflow: operaters left over.");
            }
            second = values.pop();
            if (values.isEmpty()) {
                throw new PrefixException("Not enough operands: stack underflow: operaters left over.");
            }
            first = values.pop();
            if (operaters.isEmpty()) {
                throw new PrefixException("Not enough operands: stack underflow: operateres left over.");
            }
            peekOp = operaters.pop();
            result = getValue(second, peekOp, first);
            values.push(result);
        }
        if (values.isEmpty()) {
            throw new PrefixException("Extra Operaters: operaters left over");
        }
        return values.pop();
    }

    public int precedence(String token) {
        switch (token) {
            case "*":
                return 1;
            case "-":
            case "+":
                return 2;
            default:
                System.out.println(token);
                throw new PrefixException("Not a valid operater ");
        }
    }

    public  String getValue(String second, String op, String first) {
        int result = 0;
        switch (op) {
            case "*":
                result = Integer.parseInt(first) * Integer.parseInt(second);
                p.append("Evaluating : ").append(first).append(" * ").append(second).append(" = ").append(result).append("\n");
                break;
            case "+":
                result = Integer.parseInt(first) + Integer.parseInt(second);
                p.append("Evaluating : ").append(first).append(" + ").append(second).append(" = ").append(result).append("\n");
                break;
            case "-":
                result = Integer.parseInt(first) - Integer.parseInt(second);
                p.append("Evaluating : ").append(first).append(" - ").append(second).append(" = ").append(result).append("\n");
                break;
            default:
                result = 0;
                throw new PrefixException("Illegal symbol: For the input string: " + op);
        }
        return Integer.toString(result);
    }
}
