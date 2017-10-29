# Simple-Infix-Evaluater
A simple infix expression is one with no parentheses, e.g.  
5 + 6 * 3
Using 2 stacks you can evaluate a simple infix expression in one pass without converting to a postfix form first.  This requires use of two stacks, one with integers and one with operators.
The main program is again separate.  The main program must implement the algorithm given below:
1.	Create an empty operator stack
2.	Create an empty value stack
3.	Repeat
		    Get the next token.
	  	    If the token is:
	      A number: 
	            Push the number onto the value stack.
	     An operator (call it thisOp):
	            While the operator stack is not empty, and the top item on the operator stack	
		    has the same or greater precedence as thisOp,
	Pop the operator from the operator stack.
	Pop the value stack twice, getting two operands.
	Apply the operator to the operands, in the correct order.
	Push the result onto the value stack.
      	    Push thisOp onto the operator stack.     
until at the end of the string
4.	While operator stack is not empty     
	Pop the operator from the operator stack.
	        		Pop the value stack twice, getting two operands.
	        		Apply the operator to the operands, in the correct order.
Push the result onto the value stack.
5.	Pop result from value stack (at this point the operator stack should be empty).
