package Lists;

class Node {
    public String content;
    public Node next;
    public Node prev;

    public Node(String content) {
        this.content = content;
        this.next = null;
        this.prev = null;
    }

    public String toString() {
        return this.content;
    }
}