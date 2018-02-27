
package infixevaluatergui;


public class Stack<E> implements UnboundedStackInterface<E> {
    int count;
    Node<E> peek;
    public Stack() {
        count = 0;
        peek  = null;
    }

    @Override
    public void push(E element) {
        Node<E> newNode;
        newNode = new Node<>(element);
        newNode.setLink(peek);
        peek = newNode;
        count++;
    }

    @Override
    public E pop() {
        if (!isEmpty()) {
            E tempElement;
            tempElement = peek.getInfo();
            peek        = (Node<E>) peek.getLink();
            count--;
            return tempElement;
        } else {
            throw new EmptyStackException("Stack is empty.");
        }
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public E peek() {
        if (!isEmpty()) {
            return peek.getInfo();
        } else {
            throw new EmptyStackException("Stack is empty."); 
        }
    }

    @Override
    public boolean isEmpty() {
        return (peek == null);
    }

    @Override
    public boolean contains(E element) throws EmptyStackException {
        if (isEmpty()) 
            throw new EmptyStackException("Stack is empty.");
        else 
            return (element == peek.getInfo());
    }

}
