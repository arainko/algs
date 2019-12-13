package Lists;

public class List {
    public Node head;
    public Node warden = new Node((String)null);

    public List() {
        this.warden.next = this.warden;
        this.warden.prev = this.warden;
        this.head = this.warden;
    }

    public void insert(Node node) {
        node.prev = this.warden;
        node.next = this.head;
        this.head.prev = node;
        this.warden.next = node;
        this.head = node;
    }

    public void print() {
        for(Node currNode = this.head; currNode != this.warden; currNode = currNode.next) {
            System.out.println(currNode);
        }

    }

    public Node search(String nodeContent) {
        for(Node currNode = this.head; currNode != this.warden; currNode = currNode.next) {
            if (currNode.content.equals(nodeContent)) {
                return currNode;
            }
        }

        return null;
    }

    public void delete(String nodeContent) {
        for(Node currNode = this.head; currNode != this.warden; currNode = currNode.next) {
            if (currNode.content.equals(nodeContent)) {
                if (currNode == this.head && currNode.next == this.warden) {
                    this.head = currNode.next;
                }

                currNode.prev.next = currNode.next;
                currNode.next.prev = currNode.prev;
            }
        }

    }

    public static void main(String[] args) {
        Node node1 = new Node("node1");
        Node node2 = new Node("node2");
        Node node3 = new Node("node3");
        Node node4 = new Node("node4");
        List list = new List();
        List list2 = new List();
        list.insert(node1);
        list.insert(node2);
        list.insert(node3);
        list2.insert(node4);
        list2.delete("node4");
        list2.print();
    }
}
