package com.shpp.p2p.cs.aprianikova.collections;

/**
 * The interface contains the main collection methods.
 *
 * @param <E> - the type of elements in the collection
 */
public interface CollectionsI<E> {

    /**
     * Adds an element to the collection.
     *
     * @param element - element being added to collection.
     */
    void add(E element);

    /**
     * The method removes all elements from the collection.
     */
    void clear();

    /**
     * Determining whether a collection is empty.
     */
    boolean isEmpty();

    /**
     * number of collection elements
     */
    int size();

    /**
     * @return a string of collection elements.
     */
    String toString();
}
