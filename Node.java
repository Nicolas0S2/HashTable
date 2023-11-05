public class Node {
    private int key;
    private Node next;
    private Node previous;

    public Node() {
    }

    public Node(Node n) {
    this.key = n.getKey();
    this.next = n.getNext();
    this.previous = n.getPrevious();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}
