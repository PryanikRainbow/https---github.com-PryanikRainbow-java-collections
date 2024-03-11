package com.shpp.p2p.cs.aprianikova.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  A simple stack based on a linked list with the methods needed to perform a given task.
 */
public class StackSimple<E> implements Iterable<E>, CollectionsI<E> {
    /* A node on the "top" of the stack (lifo). */
    private Node<E> peekNode;

    /* the number of elements in StackSimple */
    int size = 0;

    /**
     * The method adds an element to the end of StackSimple. StackSimple size +1.
     * If list is empty, give the value of the peekNode.
     * Else the new node will follow the last node.
     *
     * @param element - element being added to StackSimple.
     */
    @Override
    public void add(E element) {
        if (isEmpty()) {
            peekNode = new Node<>(element, null, null);
        } else {
            peekNode = new Node<>(element, peekNode, null);
        }
        size++;
    }

    /**
     * The method returns the value of last node(peekNode) node of the QueueSimple and removes it node.
     * Get a copy of the last node(peekNode). The last node(peekNode) becomes the previous node before it.
     * Size -1, return the value of removed Node.
     * Else if StackSimple is empty, print the text with error, throw the corresponding exception.
     *
     * @return value of removed Node.
     */
    public E pop() {
        if (!isEmpty()) {
            E peek = peek();
            peekNode = peekNode.getPrevious();
            size--;
            return peek;
        } else {
            printError();
            throw new NoSuchElementException();
        }
    }

    /**
     * get value (element) of last node(peekNode)
     */
    public E peek() {
        if (!isEmpty()) {
            return peekNode.getValue();
        } else {
            printError();
            throw new NoSuchElementException();
        }
    }

    /**
     * Determining whether a StackSimple is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * The method removes all elements from the Stack. "Resets" the lastNode (peekNode)
     */
    @Override
    public void clear() {
        peekNode = null;
        size = 0;
    }

    /**
     * number of elements in the StackSimple
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Method converts array to Stack.
     * Given the reverse iteration, which toString() of StackSimple also depends on, the array needs to be reversed.
     * (Any changes in the array within the method will affect the actual array values).
     *
     * @param array - input array
     * @return SimpleQueue
     */
    public StackSimple<E> arrayToStack(E[] array) {
        /* array reverse. Iterate to the middle of the array. */
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            E leftValue = array[left]; //get copy of left element
            array[left] = array[right];   //the element on the left gets the value of the opposite element on the right.
            array[right] = leftValue;   //The element on the right gets leftValue.
            left++;      //"approximate" both indexes to the middle(i++; j--)
            right--;
        }

        StackSimple<E> stack = new StackSimple<>();
        for (int i = array.length - 1; i >= 0; i--) {
            stack.add(array[i]);
        }
        return stack;
    }

    /**
     * @return StackSimple Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /* next node */
            Node<E> node = peekNode;

            /* check if the next element exists  */
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
                    node = node.getPrevious();
                    return element;
                } else {
                    printError();
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * Get a string StackSimple of elements.
     * (Also, due to the presence of links only to the previous node, the list of elements is reversed.)
     */
    @Override
    public String toString() {
        Node<E> node = peekNode;

        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        for (int i = 0; i < size; i++) {
            sb.append(node.getValue());
            if (i < size() - 1) sb.append(", ");

            node = node.getPrevious();
        }
        sb.append("]");

        return sb.toString();
    }


    /**
     * Print an error message when attempting to access elements of a StackSimple that is empty.
     */
    private void printError() {
        System.err.println("Stack is empty.");
    }
}
