package infixevaluatergui;

import java.util.StringTokenizer;
public class EvaluateExpression {
	StringBuilder line;
	public StringTokenizer tokenizer;
	public String pattern;

	public EvaluateExpression(StringTokenizer t, String p) {
		line = new StringBuilder();
		tokenizer = t;
		pattern = p;
	}

	public String result() throws Exception {
		Stack<String> operaters = new Stack<String>();
		Stack<String> values = new Stack<String>();
		String token, thisOp, second, first, peekOp, result;
		while (tokenizer.hasMoreTokens()) {
			token = tokenizer.nextToken().trim();
			if (token.contains(" ") || token.equals(" ")) {
				throw new PrefixException("Extra operands: Operands left over");
			}
			if (!pattern.contains(token)) {
				values.push(token);
			} else if (pattern.contains(token)) {
				thisOp = token;
				while (!operaters.isEmpty() && (precedence(operaters.peek()) <= precedence(thisOp))) {
					if (values.isEmpty()) {
						throw new PrefixException("Stack under flow: Not enough operands");
					} else {
						second = values.pop();
					}
					if (values.isEmpty()) {
						throw new PrefixException("Stack under flow: Not enough operands");
					} else {
						first = values.pop();
					}
					if (operaters.isEmpty()) {
						throw new PrefixException("Stack under flow: Operands left over");
					} else {
						peekOp = operaters.pop();
					}
					result = getValue(second, peekOp, first);
					values.push(result);
				}
				operaters.push(thisOp);
			}
		}

		while (!operaters.isEmpty()) {

			if (operaters.isEmpty()) {
				throw new PrefixException("Stack under flow: Not enough operaters");
			} else {
				peekOp = operaters.pop();
			}
			if (values.isEmpty()) {
				throw new PrefixException("Stack under flow: Not enough operands");
			} else {
				second = values.pop();
			}
			if (values.isEmpty()) {
				throw new PrefixException("Stack under flow: Not enough operands");
			} else {
				first = values.pop();
			}
			result = getValue(second, peekOp, first);
			values.push(result);
		}
		return values.pop();
	}

	public int precedence(String token) {
		switch (token) {
		case "*":
			return 1;
		case "-":
			return 2;
		case "+":
			return 2;
		default:
			throw new PrefixException(token + " is not a valid operater.");
		}
	}

	public String getValue(String second, String op, String first) throws Exception {
		long result = 0;
		try {
			switch (op) {
			case "*":
				result = Long.parseLong(first) * Long.parseLong(second);
				line.append("Evaluating : ").append(first).append(" * ").append(second).append(" = ").append(result)
						.append("\n");
				break;
			case "+":
				result = Long.parseLong(first) + Long.parseLong(second);
				line.append("Evaluating : ").append(first).append(" + ").append(second).append(" = ").append(result)
						.append("\n");
				break;
			case "-":
				result = Long.parseLong(first) - Long.parseLong(second);
				line.append("Evaluating : ").append(first).append(" - ").append(second).append(" = ").append(result);
				break;
			default:
				result = 0;
				throw new PrefixException("Illegal symbol: For the input string: " + op);
			}
		} catch (NumberFormatException NFE) {
			throw new PrefixException("Stack over flow: Extra operaters");
		}
		return Long.toString(result);
	}
	
	public StringBuilder getLine() {
		return line;
	}
}
