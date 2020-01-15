package com.arainko.lists;

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
        this.warden.prev.next = that.head;
        that.warden.prev.next = this.warden;
        that.head = that.warden;

        return this;
    }

    public static void main(String[] args) {
        Node[] nodes = {new Node("node1"), new Node("node2"), new Node("node3"),
                new Node("node11"), new Node("node12"), new Node("node13")};
        List list = new List();

        for (Node node : nodes)
            list.insert(node);
        System.out.println("Lista po dodanie elementow: ");
        list.print();

        list.insert(new Node("inserted-node"));
        System.out.println("\nLista po dodaniu jednego elementu: ");
        list.print();

        System.out.println("\nSearch obiektu: ");
        System.out.println(list.search("node2"));

        System.out.println("\nLista z duplikatami: ");
        list.insert(new Node("node12"));
        list.insert(new Node("node13"));
        list.print();
        System.out.println("\nLista bez duplikatow: ");
        List duplicateLess = list.distinct();
        duplicateLess.print();
//        List mergedList = list.merge(list2);

        System.out.println("\nLista z usunietym elementem: ");
        list.delete("node1");
        list.print();

        System.out.println("\nUsuniecie calej listy: ");
        List newList = list.distinct();
        newList.clear();
        newList.print();
//        mergedList.print();

        System.out.println("\nPolaczona lista z duplikatami i ta bez: ");
        List merged = list.merge(duplicateLess);
        merged.print();
    }
}
