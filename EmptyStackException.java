
package infixevaluatergui;

public class EmptyStackException extends RuntimeException {

    public EmptyStackException() {
        super();
    }

    public EmptyStackException(String messege) {
        super(messege);
    }
}
