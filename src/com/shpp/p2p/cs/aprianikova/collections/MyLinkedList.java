package com.shpp.p2p.cs.aprianikova.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class - simple implementation of the collection doubly LinkedList.
 */
public class MyLinkedList<E> implements Iterable<E>, ListsI<E> {
    /* first node of linkedList */
    private Node<E> firstNode = null;

    /* last node of linkedList */
    private Node<E> lastNode = null;

    /* size of linkedList (the number of nodes) */
    private int size = 0;

    /* create an empty linked list */
    public MyLinkedList() {
    }

    /**
     * Assign a value to an element of LinkedList, or assign a new value, if index is correct.
     * If the index is not correct - print the text about error, throw the appropriate exception.
     *
     * @param index    - index of the element (node) in the linkedList
     * @param newValue - new value of the arrayList element
     */
    @Override
    public void set(int index, E newValue) {
        if (index >= 0 && index < size) {
            Node<E> node = firstPartOfList(index) ? searchFromBegin(index) : searchFromEnd(index);
            node.setValue(newValue);
        } else {
            printError(index);
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Get element by index, if index is correct.
     * If the index is not correct - print the text about error, throw the appropriate exception.
     *
     * @param index - index of the element (node) in the linkedList
     * @return E value of element by index
     */
    @Override
    public E get(int index) {
        if (index >= 0 && index < size) {
            return firstPartOfList(index) ? searchFromBegin(index).getValue() : searchFromEnd(index).getValue();
        } else {
            printError(index);
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * get value (element) of first node(head)
     */
    public E getFirst() {
        if (!isEmpty()) {
            return firstNode.getValue();
        } else {
            printError(0);
            throw new NoSuchElementException();
        }
    }

    /**
     * get value (element) of last node(tail)
     */
    public E getLast() {
        if (!isEmpty()) {
            return lastNode.getValue();
        } else {
            printError(0);
            throw new NoSuchElementException();
        }
    }

    /**
     * The method adds an element to the end of linkedList.LinkedList size +1.
     * If list is empty, give the value of the first node. Give last node the value of first node.
     * Else create a new node with new value, the lastNode is set as the previous one of new node.
     * The new node is set as next to current last node. The last node is considered a new node.
     *
     * @param element - element being added to MyLinkedList.
     */
    @Override
    public void add(E element) {
        if (isEmpty()) {
            firstNode = new Node<>(element, null, null);
            lastNode = firstNode;
        } else {
            Node<E> newNode = new Node<>(element, lastNode, null);
            lastNode.setNext(newNode);
            lastNode = newNode;
        }
        size++;
    }

    /**
     * Method adds an element to the list at the specified index, if index is correct.
     * Different ways for insert, depending on the index:
     * -insertion at the beginning of list,
     * -insertion at end of list,
     * -insertion at the first half of list,
     * -insertion at the second half of the list.
     * If index is not correct - print the text about error, throw the appropriate exception.
     *
     * @param index   - node position (element) number in LinkedList.
     * @param element -  new value of the linkedList element
     */
    @Override
    public void add(int index, E element) {
        if (index >= 0 && index <= size) {
            if (index == 0) {
                addFirst(element);
            } else if (index == size) {
                add(element);
            } else if (firstPartOfList(index)) {
                addToFirstHalf(index, element);
            } else {
                addToSecondHalf(index, element);
            }
        } else {
            printError(index);
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Insertion at the beginning of list.
     * If Linked List is empty, the firstNode with the value of argument is added, neighbors "missing".
     * This same node will be equal to the last node.
     * Else new first node will have the new value specified by the argument,
     * the next node will be the current first node.
     * Attach a "previous" firstNode with the new node.
     *
     * @param element - new value of the linkedList element
     */
    public void addFirst(E element) {
        if (isEmpty()) {
            firstNode = new Node<>(element, null, null);
            lastNode = firstNode;
        } else {
            firstNode = new Node<>(element, null, firstNode);
            firstNode.getNext().setPrevious(firstNode);
        }
        size++;

    }

    /**
     * Insertion at the first half of list.
     * Find the node at current index.
     * Create a new node that will be between the previous and current node.
     * The node before the specified index is "attached" to the new node.
     *
     * @param index   - node position (element) number in LinkedList.
     * @param element - new value of the linkedList element
     */
    private void addToFirstHalf(int index, E element) {
        Node<E> node = searchFromBegin(index);

        Node<E> newNode = new Node<>(element, node.getPrevious(), node);
        node.getPrevious().setNext(newNode);

        size++;
    }

    /**
     * Insertion at the second half of list.
     * Find the node at current index.
     * Create a new node that will be between the previous and current node.
     * The node before the specified index is "attached" to the new node.
     *
     * @param index   - node position (element) number in LinkedList.
     * @param element - new value of the linkedList element
     */
    private void addToSecondHalf(int index, E element) {
        Node<E> node = searchFromEnd(index);

        Node<E> newNode = new Node<>(element, node.getPrevious(), node);
        node.getPrevious().setNext(newNode);

        size++;
    }

    /**
     * Search from the beginning, since index <= size / 2.
     *
     * @param index - node position (element) number in LinkedList.
     * @return node by index
     */
    private Node<E> searchFromBegin(int index) {
        Node<E> node = firstNode;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    /**
     * Search from the end, since index > size / 2.
     *
     * @param index - node position (element) number in LinkedList.
     * @return node by index
     */
    private Node<E> searchFromEnd(int index) {
        Node<E> node = lastNode;
        for (int i = size - 1; i > index; i--) {
            node = node.getPrevious();
        }
        return node;
    }

    /**
     * Insertion at the beginning of list.
     *
     * @param element - new value of the linkedList element
     */
    public void addLast(E element) {
        add(element);
    }

    /**
     * Removing element of LinkedList by index, if index is correct. Size -1.
     * Different ways for removing, depending on the index:
     * -removing from the beginning of list,
     * -removing from end of list,
     * -removing a node from the middle of the list.
     * If index is not correct - print the text about error, throw the appropriate exception.
     *
     * @param index - node position (element) number in LinkedList.
     */
    @Override
    public void remove(int index) {
        if (index >= 0 && index < size) {
            if (index == 0) {
                removeFirst();
            } else if (index == size - 1) {
                removeLast();
            } else {
                /* Find the node at current index */
                Node<E> node = firstPartOfList(index) ? searchFromBegin(index) : searchFromEnd(index);

                /* The previous node is "attached" to the node that follows the node at determined index */
                node.getPrevious().setNext(node.getNext());
                /* The next node is "attached" the node that precedes the node by the specified index */
                node.getNext().setPrevious(node.getPrevious());
                size--;
            }
        } else {
            printError(index);
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Removing from the beginning of list. If the list is not empty, size -1.
     * If the list is empty - print the text about error, throw the appropriate exception.
     * If size is not 1, the firstNode will be considered the next node after it.
     * "Reset" the reference to the previous node.
     * Else "reset" the first and last nodes (if size 1)
     */
    public void removeFirst() {
        if (isEmpty()) {
            printError(0);
            throw new NoSuchElementException();
        } else if (firstNode != lastNode) {
            firstNode = firstNode.getNext();
            firstNode.setPrevious(null);
            size--;
        } else {
            lastNode = null;
            firstNode = null;
            size--;
        }
    }

    /**
     * Removing from the end of list. If the list is not empty, size -1.
     * If the list is empty - print the text about error, throw the appropriate exception.
     * If size is not 1, the lastNode will be considered the previous node before it.
     * "Reset" the reference to the next node.
     * Else "reset" the first and last nodes (if size 1)
     */
    public void removeLast() {
        if (isEmpty()) {
            printError(0);
            throw new NoSuchElementException();
        } else if (lastNode != firstNode) {
            lastNode = lastNode.getPrevious();
            lastNode.setNext(null);
            size--;
        } else {
            lastNode = null;
            firstNode = null;
            size--;
        }
    }

    /**
     * The method removes an all elements from the list by value if the linkedList contains it.
     *
     * @param value - value of element to removing
     */
    @Override
    public void removeByValue(E value) {
        Iterator<E> iterator = iterator();

        while (iterator.hasNext()) {

            E current = iterator.next();
            if (current.equals(value)) {
                iterator.remove();
            }
        }
    }

    /**
     * The method removes all elements from the linkedList. "Resets" the firstNode and lastNode
     */
    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    /**
     * Determining whether a linkedList is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * The method checks whether linkedList contains a determined element.
     *
     * @param e - element whose presence is checked in the MyLinkedList
     * @return element presence/element absence in the MyLinkedList
     */
    @Override
    public boolean contains(Object e) {
        for (E current : this) {
            if (current.equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the first index of linkedList element by value. If the element is missing return -1.
     *
     * @param e - element value
     * @return first index of the element by value
     */
    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) if (e.equals(get(i))) return i;
        return -1;
    }

    /**
     * Returns the last index of linkedList element by value. If the element is missing return -1.
     *
     * @param e - element value
     * @return last index of the element by value
     */
    @Override
    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--) if (e.equals(get(i))) return i;
        return -1;
    }

    /**
     * Method converts MyLinkedList to array.
     *
     * @return array
     */
    @Override
    @SuppressWarnings("unchecked") /* remove warning for type safety */
    public E[] toArray() {
        E[] eArray = (E[]) new Object[size];
        for (int i = 0; i < size; i++) eArray[i] = get(i);

        return eArray;
    }

    /**
     * Method converts array to MyLinkedList.
     *
     * @param array - input array
     * @return MyLinkedList
     */
    public MyLinkedList<E> arrayToList(E[] array) {
        MyLinkedList<E> linkedList = new MyLinkedList<>();
        for (E e : array) linkedList.add(e);

        return linkedList;
    }

    /**
     * Return the number of linked list elements. Size of linked list (MyLinkedList).
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return Iterator<E> for MyLinkedList
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            /* next node */
            Node<E> node = firstNode;

            /**
             * check if the next element exists
             */
            @Override
            public boolean hasNext() {
                return node != null;
            }

            /**
             * Returns the next element of collection if it exists. Go to the next node (for hasNext() checking).
             * If index is not correct - print the text about error, throw the appropriate exception.
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

            /**
             * returns the previous node, removes the current element
             * after the next call, the next node is returned. So, you need to delete the previous node
             */
            @Override
            public void remove() {
                if (node == firstNode.getNext()) {
                    removeFirst();
                    node = firstNode;
                } else if (node == lastNode.getNext()) {
                    removeLast();
                    node = null;
                } else {
                    Node<E> removed = node.getPrevious();
                    Node<E> preRemoved = removed.getPrevious();
                    Node<E> afterRemoved = node;

                    /* The next node after removed one is "attached" to the node before removed one */
                    afterRemoved.setPrevious(preRemoved);
                    /* The previous node is "attached" to the node that follows the removed node  */
                    preRemoved.setNext(afterRemoved);

                    /* by removing a node, return to the previous node to check the next element (hasNext) */
                    node = preRemoved.getNext();
                    size--;
                }
            }
        };
    }


    /**
     * @return string of MyLinkedList
     */
    @Override
    public String toString() {
        Node<E> node = firstNode;

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(node.getValue());
            if (i < size() - 1) {
                sb.append(", ");
            }
            node = node.getNext();
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * Checking if the index is in the first half of list
     */
    private boolean firstPartOfList(int index) {
        return index <= size / 2;
    }

    /**
     * Print an error message when attempting to access a value with an invalid index.
     */
    private void printError(int index) {
        System.err.println(index + " incorrectly index. Size of LinkedList " + size + ".");
    }

}

