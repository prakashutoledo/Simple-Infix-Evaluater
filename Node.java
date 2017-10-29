package infixevaluatergui;

public class Node<E> {

    Node link;
    E info;

    public Node(E obj) {
        link = null;
        info = obj;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }
}
