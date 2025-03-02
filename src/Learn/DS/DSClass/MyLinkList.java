package Learn.DS.DSClass;

import Learn.DS.interfacePackage.Queue;

import org.jetbrains.annotations.NotNull;

/**
 * @author WYL
 */
public class MyLinkList<T> implements Queue<T> {
    private class Node {
        T val;
        Node next;
        Node pre;

        Node(T val, Node next, Node pre) {
            this.val = val;
            this.next = next;
            this.pre = pre;
        }

        Node(T val) {
            this(val, null, null);
        }
    }

    private Node first;
    private Node last;
    private int size;

    public MyLinkList() {
        this.first = new Node(null, null, null);
        this.last = this.first;
        this.size = 0;
    }

    public void add(T val, int index) {
        if (index < 0 || index > size) {
            System.out.println("Error index");
        } else {
            Node p = first;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            Node insertNode = new Node(val, null, null);
            if (p.next != null) {
                Node temp = p.next;
                temp.pre = insertNode;
                insertNode.next = temp;
                p.next = insertNode;
                insertNode.pre = p;
            } else {
                p.next = insertNode;
                insertNode.pre = p;
                last = insertNode;
            }
            size++;
        }
    }

    public void addFirst(T val) {
        add(val, 0);
    }

    public void addLast(T val) {
        add(val, size);
    }

    public void print() {
        Node p = first.next;
        System.out.println("Size: " + size);
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public void addQueue(T val) {
        addFirst(val);
    }

    @Override
    public T removeQueue() {
        return removeLast();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Node p = first.next;
        while (p != null) {
            Node temp = p.next;
            p.val = null;
            p.pre = null;
            p.next = null;
            p = temp;
        }
        this.first = null;
        this.last = this.first;
        this.size = 0;
    }

    @Override
    public void printQueue() {
        Node p = last;
        System.out.println("Size: " + size);
        while (p != null && p != first) {
            System.out.print(p.val + " ");
            p = p.pre;
        }
        System.out.println();
    }

    private void clearNode(@NotNull Node node) {
        node.pre = null;
        node.next = null;
        node.val = null;
    }

    public T remove(int index) {
        if (size == 0) {
            throw new RuntimeException("Empty List");
        }
        if (index >= size || index < 0) {
            throw new RuntimeException("OutOfBound");
        }
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node deleteNode = first.next;
            T data = deleteNode.val;
            for (int i = 0; i < index; i++) {
                deleteNode = deleteNode.next;
            }
            deleteNode.pre.next = deleteNode.next;
            deleteNode.next.pre = deleteNode.pre;
            clearNode(deleteNode);
            size--;
            return data;
        }
    }

    private T removeLast() {
        Node p = last;
        T data = p.val;
        last = p.pre;
        last.next = null;
        size--;
        clearNode(p);
        return data;
    }

    private T removeFirst() {
        Node p = first.next;
        T data = p.val;
        first.next = p.next;
        if (p.next != null) {
            p.next.pre = first;
        }
        size--;
        clearNode(p);
        return data;
    }

}
