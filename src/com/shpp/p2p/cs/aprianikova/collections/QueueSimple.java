package com.shpp.p2p.cs.aprianikova.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple queue based on a linked list with the methods needed to perform a given task.
 */
public class QueueSimple<E> implements Iterable<E>, CollectionsI<E>{
    /* first node of QueueSimple */
    private Node<E> firstNode;

    /* last node of QueueSimple */
    private Node<E> lastNode;

    /* the number of elements in QueueSimple */
    private int size;

    /**
     * The method adds an element to the end of QueueSimple.QueueSimple size +1.
     * If list is empty, give the value of the first node.
     * Else the new node will follow the last node.
     * The new node now becomes the last node.
     *
     * @param element - element being added to QueueSimple.
     */
    @Override
    public void add(E element) {
        /* create a node from element and next(last) link - null) */
        Node<E> newLast = new Node<>(element, null, null);
        if (isEmpty()) {
            firstNode = newLast;
        } else {
            lastNode.setNext(newLast);
        }
        lastNode = newLast;
        size++;
    }

    /**
     * The method returns the value first node of the QueueSimple and removes it node.
     * The first node will be considered the next node. Size -1.
     * If the QueueSimple is empty after removing, set a value of null for the last node.
     * Return the value of removed node.
     * Else if QueueSimple is empty, print the text with error, throw the corresponding exception.
     *
     * @return value of removed Node.
     */
    public E remove() {
        if (!isEmpty()) {
            E newFirst = firstNode.getValue();
            firstNode = firstNode.getNext();
            size--;
            if (isEmpty()) lastNode = null;
            return newFirst;
        } else {
            printError();
            throw new NoSuchElementException();
        }
    }

    /**
     * get first element from QueueSimple
     */
    public E peek() {
        if (!isEmpty()) {
            return firstNode.getValue();
        } else {
            printError();
            throw new NoSuchElementException();
        }
    }

    /**
     * Method converts array to QueueSimple.
     *
     * @param array - input array
     * @return QueueSimple
     */
    public QueueSimple<E> arrayToQueue(E[] array) {
        QueueSimple<E> queueSimple = new QueueSimple<>();
        for (E e : array) queueSimple.add(e);

        return queueSimple;
    }

    /**
     * Determining whether a QueueSimple is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * The method removes all elements from the QueueSimple. "Resets" the firstNode and lastNode
     */
    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    /**
     * @return QueueSimple iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /* next node */
            Node<E> node = firstNode;

            /** check if the next element exists  */
            @Override
            public boolean hasNext() {
                return node != null;
            }

            /**
             * Returns the next element of collection if it exists. Go to the next node (for hasNext() checking).
             * Else print the text about error, throw the appropriate exception.
             */
            @Override
            public E next() {
                if (hasNext()) {
                    E element = node.getValue();
                    node = node.getNext();
                    return element;
                } else {
                    throw new NoSuchElementException();
                }
            }

        };
    }

    /**
     * number of elements in the QueueSimple
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Get a string QueueSimple of elements
     */
    @Override
    public String toString() {
        Node<E> node = firstNode;

        StringBuilder sb = new StringBuilder();
        sb.append("Queue: [");
        for (int i = 0; i < size; i++) {
            sb.append(node.getValue());
            if (i < size() - 1) sb.append(", ");

            node = node.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    /** Print an error message when attempting to access elements of a Queue that is empty. */
    private void printError() {
        System.err.println("Queue is empty.");
    }

}

