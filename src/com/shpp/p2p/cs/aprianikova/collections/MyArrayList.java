package com.shpp.p2p.cs.aprianikova.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class - simple implementation of the collection ArrayList
 */
public class MyArrayList<E> implements Iterable<E>, ListsI<E> {

    /* constant that regulates the initial capacity of the array */
    private static final int DEFAULT_CAPACITY = 10;

    /* main array of the instance */
    private Object[] arrayList;

    /* size of the ArrayList (the number of filled cells in the static array) */
    private int size = 0;

    /**
     * An array with a capacity of 10 elements is created.
     */
    public MyArrayList() {
        this.arrayList = new Object[DEFAULT_CAPACITY];
    }

    /**
     * An array of the specified capacity is created, if input parameter is correct (>0).
     * If the index is not correct - print the text about error, throw the appropriate exception.
     *
     * @param capacity - user-defined array capacity
     */
    public MyArrayList(int capacity) {
        if (capacity >= 0)
            this.arrayList = new Object[capacity];
        else {
            System.err.println("Capacity must be >= 0");
            throw new NegativeArraySizeException();
        }
    }

    /**
     * Assign a value to an element of MyArrayList, or assign a new value, if index is correct.
     * If the index is not correct - print the text about error, throw the appropriate exception.
     *
     * @param index    - index of the element in the array
     * @param newValue - new value of the arrayList element
     */
    @Override
    public void set(int index, E newValue) {
        if (index >= 0 && index < size)
            arrayList[index] = newValue;
        else {
            printError(index);
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Get element by index, if index is correct.
     * If the index is not correct - print the text about error, throw the appropriate exception.
     *
     * @param index - index of the element in the array
     * @return E value of element by index
     */
    @SuppressWarnings("unchecked") /* remove warning for type safety */
    @Override
    public E get(int index) {
        if (index >= 0 && index < size) {
            return (E) arrayList[index];
        } else {
            printError(index);
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * The method adds an element to the end of list. MyArrayList size +1.
     * If the entire array is full,
     * rewrite the array, increasing its length:
     * -get a copy of the current array,
     * -create a new array by increasing its length,
     * -copy the old array to the new array.
     * -Add element.
     *
     * @param e - element being added to MyArrayList.
     */
    @Override
    public void add(E e) {
        if (size == arrayList.length) {
            Object[] copy = getCopy();
            this.arrayList = new Object[increasedLength()];
            System.arraycopy(copy, 0, arrayList, 0, copy.length);
        }
        this.arrayList[size] = e;
        size++;
    }

    /**
     * The method adds an element to the arrayList by index. ArrayList size +1.
     * If index is correct, rewrite the array:
     * -get a copy of the current array
     * -clear the old array, else If the entire array is full, then increasing its length
     * -rewrite part of the list before the index
     * -add a new element at the desired index
     * -rewrite part of the list after the index
     * If index is not correct - print the text about error, throw the appropriate exception.
     *
     * @param index - user-defined index to insert the new element into the arrayList
     * @param e     - element being added to arrayList
     */
    @Override
    public void add(int index, E e) {
        if (index >= 0 && index <= size) {
            Object[] copy = getCopy();

            if (index < arrayList.length) this.arrayList = new Object[arrayList.length];
            else if (index == arrayList.length) this.arrayList = new Object[increasedLength()];

            for (int i = 0; i < index; i++) {
                arrayList[i] = copy[i];
            }
            arrayList[index] = e;
            for (int i = index + 1; i < arrayList.length; i++) {
                arrayList[i] = copy[i - 1];
            }
            size++;
        } else {
            printError(index);
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * The method removes an element from the arrayList by index. ArrayList size -1.
     * If index is correct, rewrite the array:
     * -get a copy of the current array
     * -clear the old array, reduce its length by 1
     * -rewrite part of the list before the index
     * -rewrite part of the list after the index.
     * If index is not correct - print the text about error, throw the appropriate exception.
     *
     * @param index - user-defined index of arrayList element to remove
     */
    @Override
    public void remove(int index) {
        if (index >= 0 && index < size) {
            Object[] copy = getCopy();
            this.arrayList = new Object[arrayList.length - 1];
            for (int i = 0; i < index; i++) {
                arrayList[i] = copy[i];
            }
            for (int i = index; i < arrayList.length; i++) {
                arrayList[i] = copy[i + 1];
            }
            size--;
        } else {
            printError(index);
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * The method removes an all elements from the list by value if the arrayList contains it.
     *
     * @param e - value of element to removing
     */
    @Override
    public void removeByValue(E e) {
        Iterator<E> iterator = iterator();

        while (iterator.hasNext()) {
            E current = iterator.next();
            if (current.equals(e)) {
                iterator.remove();
            }
        }
    }

    /**
     * The method removes all elements from the arrayList.
     */
    @Override
    public void clear() {
        this.arrayList = new Object[arrayList.length];
        size = 0;
    }

    /**
     * Determining whether a arrayList is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * The method checks whether MyArrayList contains a determined element.
     *
     * @param e - element whose presence is checked in the MyArrayList
     * @return element presence/element absence in the MyArrayList
     */
    @Override
    public boolean contains(E e) {
        for (Object o : arrayList) {
            if (e.equals(o)) return true;
        }
        return false;
    }

    /**
     * Returns the first index of MyArrayList element by value. If the element is missing return -1.
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
     * Returns the last index of MyArrayList element by value. If the element is missing return -1.
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
     * Method converts MyArrayList to array.
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
     * Method converts array to MyArrayList.
     *
     * @param array - input array
     * @return MyArrayList
     */
    public MyArrayList<E> arrayToList(E[] array) {
        MyArrayList<E> arrayList = new MyArrayList<>();
        for (E e : array) arrayList.add(e);

        return arrayList;
    }

    /**
     * @return size of MyArrayList. The number of filled array cells in static array (arrayList)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return Iterator<E> for MyArrayList
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /* index Next */
            int index;

            /** check if the next element exists */
            @Override
            public boolean hasNext() {
                return index < size();
            }

            /**
             * Returns the next element of collection if it exists. Go to the next index (for hasNext() checking).
             * If index is not correct - print the text about error, throw the appropriate exception.
             */
            @Override
            @SuppressWarnings("unchecked") /* remove warning for type safety */
            public E next() {
                if (hasNext()) {
                    return (E) arrayList[index++];
                } else {
                    printError(index);
                    throw new NoSuchElementException();
                }
            }

            /** returns the previous index, removes the current element */
            @Override
            public void remove() {
                index--;
                MyArrayList.this.remove(index);
            }
        };
    }

    /**
     * @return string of MyArrayList
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size(); i++) {
            sb.append(arrayList[i]);
            if (i < size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * Print an error message when attempting to access a value with an invalid index.
     */
    private void printError(int index) {
        System.err.println(index + " incorrectly index. Size of ArrayList " + size + ".");
    }

    /**
     * @return the new increased length of the main array
     */
    private int increasedLength() {
        return arrayList.length * 2;
    }

    /**
     * @return copy of array
     */
    private Object[] getCopy() {
        return arrayList.clone();
    }
}



