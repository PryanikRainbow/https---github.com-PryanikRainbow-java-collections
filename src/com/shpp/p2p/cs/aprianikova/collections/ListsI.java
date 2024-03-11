package com.shpp.p2p.cs.aprianikova.collections;

/**
 * The interface contains the main lists (MyArrayList; LinkedList) methods.
 *
 * @param <E> - the type of elements in the list.
 */
public interface ListsI<E> extends CollectionsI<E> {

    /**
     * Assign a value to an element of list, or assign a new value
     *
     * @param index    - index of the element in the list.
     * @param newValue - - new value of the list element.
     */
    void set(int index, E newValue);

    /**
     * Get element by index
     *
     * @param index - index of the element in the list
     * @return E value of element by index
     */
    E get(int index);

    /**
     * The method adds an element to the list by index
     *
     * @param index   - user-defined index to insert the new element into the list.
     * @param element - element being added to list.
     */
    void add(int index, E element);

    /**
     * The method removes an element from the list by index.
     *
     * @param index - user-defined index of list element to remove.
     */
    void remove(int index);

    /**
     * The method removes an all elements from the list by value if list contains it.
     *
     * @param element - value of element to removing
     */
    void removeByValue(E element);

    /**
     * The method checks whether list contains a determined element.
     *
     * @param element - element whose presence is checked in list.
     * @return element presence/element absence in the list.
     */
    boolean contains(E element);

    /**
     * Returns the first index of list element by value. If the element is missing return -1.
     *
     * @param element - element value
     * @return first index of the element by value
     */
    int indexOf(E element);

    /**
     * Returns the last index of list element by value. If the element is missing return -1.
     *
     * @param element - element value
     * @return last index of the element by value
     */
    int lastIndexOf(E element);

    /**
     * Method converts list to array.
     *
     * @return array
     */
    E[] toArray();

}
