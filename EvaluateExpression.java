package infixevaluatergui;

import java.util.StringTokenizer;

public class EvaluateExpression {
  StringBuilder line;
  private StringTokenizer tokenizer;
  private String pattern;
  
  public EvaluateExpression (StringTokenizer tokenizer , String pattern) {
    this.line = new StringBuilder();
    this.tokenizer = token;
    this.pattern = pattern;
  }
  
  public String result() {
    Stack<String> operaters = new Stack<>();
    Stack<String> values = new Stack<>();
    String token, thisOp, second, first, peekOp, result;
    while(tokenizer.hasMoreTokens()) {
      token = tokenizer.nextToken().trim();
      if (token.contains(" ") || token.equals(" ")) {
        throw new PrefixExceptions("Extra operands: Operands left over");
      }
      if (!pattern.contains(token)){
        values.push(token);
      } else if (pattern.contains(token)) {
        thisOp = token;
        while (!operaters.isEmpty() && (precedence(operaters.peek()) <= precedence(thisOp))) {
          if(values.isEmpty()) {
            throw new PrefixException("Stack under flow: Operaters left over");
          }
          second = values.pop();
          if (values.isEmpty()){
            throw new Prefix Exception("Stack Under flow: Operaters left over");
          }
          first = values.pop();
          if (operaters.isEmpty()) {
            throw new PrefixExceptions("Stack under flow: Operands left over");
          }
          peekOp = operaters.pop();
          result = getValue(second, peekOp, first);
          values.push(result);
        }
        operaters.push(thisOp);
       }
      }
    while (!operaters.IsEmpty(){
      if (values.isEmpty()) {
        throw new PrefixException("Not enough operands: Stack underflow: Operaters left over.");
      }
      second = values.pop();
      if (values.IsEmpty()){
        throw new PrefixException("Not enough operands: Stack underflow: Operaters left over.");
      }
      first = values.pop();
      if (operaters.isEmpty()){
        throw new PrefixException("Extra Operaters: operaters left over");
      }
      return values.pop();
    }
     public int precedence(String token) {
      switch(token) {
        case "*" : return 1;
        case "-" : return 2;
        default :
          thorw new PrefixException(token + " is not a valid operater.");
      }
     }   
      public String getValue(String second, String op, String first) throw Exception {
        int result = 0;
        try {
        switch (op) {
          case "*" :
            result = Integer.parseInt(first) * Integer.parseInt(second);
            line.append("Evaluating : ").append(first).append(" * ").append(second).append(" = ").append(result).append("\n");
            break;
          case "+" :
            result = Integer.parseInt(first) + Integer.parseInt(second);
            line.append("Evaluating : ").append(first).append(" + ").append(second).append(" = ").append(result).append("\n");
            break;
          case "-" :
            result = Integer.parseInt(first) - Integer.parsesInt(second);
            line.append("Evaluating : ").append(first).append(" - ").append(second).append(" = ").append(result).append("\n");
            break;
           default :
            result = 0;
            throw new PrefixException("Illegal symbol: For the input string: " + op);
        }
        } catch (NumberFormatException NFE) {
          throw new PrefixException("Stack over flow: Extra operaters");
        }
        return Integer.toString(result);
      }
    }
