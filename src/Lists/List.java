package Lists;

public class List {
    public Node head;
    public Node warden = new Node(null);

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
        Node currNode = this.head;
        while (currNode != this.warden) {
            System.out.println(currNode);
            currNode = currNode.next;
        }
    }

    public Node search(String nodeContent) {
        Node currNode = this.head;
        while (currNode != this.warden) {
            if (currNode.content.equals(nodeContent)) {
                return currNode;
            }
            currNode = currNode.next;
        }
        return null;
    }

    public void delete(String nodeContent) {
        Node currNode = head;
        while (currNode != warden) {
            if (currNode.content.equals(nodeContent)) {
                if (currNode == head && currNode.next == warden || currNode.prev == warden && currNode == head) {
                    head = currNode.next;
                }
                currNode.prev.next = currNode.next;
                currNode.next.prev = currNode.prev;
            }
            currNode = currNode.next;
        }
    }

    public void clear() {
        Node currNode = this.head;
        while (currNode != this.warden) {
            currNode = currNode.next;
           delete(currNode.prev.content);
        }
    }

    public List distinct() {
        List distinctList = new List();
        distinctList.insert(head.copy());
        Node currNode = this.head;
        while (currNode != this.warden) {
            if (distinctList.search(currNode.content) == null) {
                distinctList.insert(currNode.copy());
            }
            currNode = currNode.next;
        }
        return distinctList;
    }

    public List merge(List that) {

    }

    public static void main(String[] args) {
        Node node1 = new Node("node1");
        Node node12 = new Node("node1");
        Node node2 = new Node("node2");
        Node node3 = new Node("node3");
        Node node4 = new Node("node4");
        Node node5 = new Node("node4");
        List list = new List();
        list.insert(node1);
        list.insert(node2);
        list.insert(node3);
        list.insert(node4);
        list.insert(node5);
        list.insert(node12);
        list.distinct().print();
    }
}
