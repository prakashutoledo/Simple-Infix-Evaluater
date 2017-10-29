package infixevaluatergui;

public interface StackInterface<E> {

    E pop() throws EmptyStackException;

    int size() throws EmptyStackException;

    E peek() throws EmptyStackException;

    boolean isEmpty();

    boolean contains(E element) throws EmptyStackException;
}
