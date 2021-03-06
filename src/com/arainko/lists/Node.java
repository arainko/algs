package com.arainko.lists;

public class Node {
    public String content;
    public Node next;
    public Node prev;

    public Node(String content) {
        this.content = content;
        this.next = null;
        this.prev = null;
    }

    public Node copy() {
        return new Node(content);
    }

    public String toString() {
        if (content == null)
            return "";
        return this.content;

    }
}