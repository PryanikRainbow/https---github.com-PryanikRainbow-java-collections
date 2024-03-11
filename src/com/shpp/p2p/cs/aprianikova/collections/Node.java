package com.shpp.p2p.cs.aprianikova.collections;

/**
 * A class is an object on which linked lists are built. Each instance stores the current element, a link to the previous and next object
 */
public class Node<E> {
    /* value of current node */
    private E value;

    /* link to the previous node*/
    private Node<E> previous;

    /* link to the next node*/
    private Node<E> next;

    /* create a node (contain the value of this node, as well as a link to the next node) */
    public Node(E value, Node<E> previous, Node<E> next) {
        this.value = value;
        this.previous = previous;
        this.next = next;
    }

    /**
     * @return value of current node.
     */
    public E getValue() {
        return value;
    }

    /**
     * Set the value of current node.
     */
    public void setValue(E newValue) {
        this.value = newValue;
    }

    /**
     * @return link to next node.
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * Set the current node.
     */
    public void setNext(Node<E> next) {
        this.next = next;
    }

    /**
     * get a previous Node.
     */
    public Node<E> getPrevious() {
        return previous;
    }

    /**
     * Set the previous Node
     */
    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }

}
