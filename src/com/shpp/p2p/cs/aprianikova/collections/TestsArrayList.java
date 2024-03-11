package com.shpp.p2p.cs.aprianikova.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * This class tests all the public methods of the MyArrayList class.
 * The tests used different types of data.
 * Each test has at least one condition under which it will be considered passed,
 * testMethod(boolean condition) checks whether the condition is met and prints the test result.
 */
public class TestsArrayList{
    /* source: https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/*/
    /* color for the text of the passed test */
    public static final String ANSI_GREEN = "\u001B[32m";

    /*  color for the text of the no passed test */
    public static final String ANSI_RED = "\u001B[31m";

    /* a constant that resets the color */
    public static final String ANSI_RESET = "\u001B[0m";


    public static void main(String[] args) {
        System.out.println("---> Tests for MyArrayList:");
        setTest();
        getTest();
        addTest();
        addByIndexTest();
        removeByIndexTest();
        removeByValueTest();
        clearTest();
        isEmptyTest();
        containsTest();
        indexOfTest();
        lastIndexOfTest();
        toArrayTest();
        arrayToListTest();
        sizeTest();
        iteratorTest();
        toStringTest();
    }

    /**
     * Checking the set() method by the first index, the index inside the list, the last index.
     */
    private static void setTest() {
        System.out.println("- Test set(int index, E e) method");

        Boolean[] arrayBoolean = {false, true, false, false};
        MyArrayList<Boolean> myArrayList = new MyArrayList<Boolean>().arrayToList(arrayBoolean);

        myArrayList.set(0, true);
        myArrayList.set(2, true);
        myArrayList.set(myArrayList.size() - 1, true);

        boolean passed = myArrayList.toString().equals("[true, true, true, true]");
        System.out.print("set (int index, Boolean) - ");
        testMethod(passed);
    }

    /**
     * Checking the get() method by the first index, the index inside the list, the last index.
     */
    private static void getTest() {
        System.out.println("- Test get(int index) method");

        Object[] arrayObject = {false, 'a', 1};
        MyArrayList<Object> myArrayList = new MyArrayList<>().arrayToList(arrayObject);

        Object elementO = myArrayList.get(0);
        Object element1 = myArrayList.get(1);
        Object element2 = myArrayList.get(myArrayList.size() - 1);

        boolean passed = elementO.equals(false) && element1.equals('a') && element2.equals(1);
        System.out.print("get (E e, Object) - ");
        testMethod(passed);
    }

    /**
     * Checking the add() method (inserting an element at the end of the MyArrayList)
     */
    private static void addTest() {
        System.out.println("- Test add(E e) method:");

        MyArrayList<Byte> myArrayList = new MyArrayList<>();
        for (byte i = 0; i <= 10; i++) myArrayList.add(i);
        myArrayList.add((byte) 11);
        boolean condition = myArrayList.toString().equals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]");
        System.out.print("add(E e) Byte - ");
        testMethod(condition);

        MyArrayList<Character> myArrayList2 = new MyArrayList<>();
        for (char c = 'a'; c <= 'd'; ++c) myArrayList2.add(c);
        myArrayList2.add('e');
        boolean condition2 = myArrayList2.toString().equals("[a, b, c, d, e]");
        System.out.print("add(E e) Character - ");
        testMethod(condition2);
    }

    /**
     * Checking add(int index) method by the last index, first index, index inside the list.
     */
    private static void addByIndexTest() {
        System.out.println("- Test add(int index, E e) method:");

        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        for (int i = 0; i <= 4; i++) myArrayList.add(i);
        myArrayList.add(5, myArrayList.size());
        boolean indexLast = myArrayList.toString().equals("[0, 1, 2, 3, 4, 5]");
        System.out.print("add(at last position, Integer) - ");
        testMethod(indexLast);

        String[] arrayString = {"dog", "cat", "bird"};
        MyArrayList<String> myArrayList2 = new MyArrayList<String>().arrayToList(arrayString);
        myArrayList2.add(0, "unicorn");
        boolean index0 = myArrayList2.toString().equals("[unicorn, dog, cat, bird]");
        System.out.print("add(at first position, String) - ");
        testMethod(index0);

        Singer[] arraySingers = {new Singer("Katy Perry", 106),
                new Singer("Lady Gaga", 363), new Singer("Eminem", 150)};
        MyArrayList<Singer> myArrayList3 = new MyArrayList<Singer>().arrayToList(arraySingers);
        myArrayList3.add(2, new Singer("Pitbull", 46));
        boolean indexInTheMiddle = myArrayList3.toString().equals("[Katy Perry: 106 wins, Lady Gaga: 363 wins, " +
                "Pitbull: 46 wins, Eminem: 150 wins]");
        System.out.print("add(2 index, my Class(Singer)) - ");
        testMethod(indexInTheMiddle );
    }

    /**
     * Checking the remove(int index) method by the first index, index inside the list, last index.
     */
    private static void removeByIndexTest() {
        System.out.println("- Test remove(int index) method:");

        Double[] arrayDouble = {0.25, 9.5, -7.0, 25.0, 0.216};
        MyArrayList<Double> myArrayList = new MyArrayList<Double>().arrayToList(arrayDouble);

        myArrayList.remove(0);
        boolean index0 = myArrayList.toString().equals("[9.5, -7.0, 25.0, 0.216]");
        System.out.print("remove(first index) Double - ");
        testMethod(index0);

        myArrayList.remove(1);
        boolean indexInMiddle = myArrayList.toString().equals("[9.5, 25.0, 0.216]");
        System.out.print("remove(index 1) Double - ");
        testMethod(indexInMiddle);

        myArrayList.remove(myArrayList.size() - 1);
        boolean indexLast = myArrayList.toString().equals("[9.5, 25.0]");
        System.out.print("remove(last index) Double - ");
        testMethod(indexLast);
    }

    /**
     * Checking the removeByValue(E e). Case: element to be removed with first index, middle index, last index.
     */
    private static void removeByValueTest() {
        System.out.println("- Test removeByValue(E e) method:");

        Boolean[] arrayBoolean = {true, true, false, true};
        MyArrayList<Boolean> myArrayList = new MyArrayList<Boolean>().arrayToList(arrayBoolean);

        myArrayList.removeByValue(true);
        boolean condition = myArrayList.toString().equals("[false]");

        Boolean[] arrayBoolean2 = {false, false, false, false};
        MyArrayList<Boolean> myArrayList2 = new MyArrayList<Boolean>().arrayToList(arrayBoolean2);

        myArrayList2.removeByValue(false);
        boolean condition2 = myArrayList2.toString().equals("[]");

        System.out.print("removeByValue(E e) Boolean - ");
        testMethod(condition && condition2);
    }

    /**
     * Checking the clear().
     */
    private static void clearTest() {
        System.out.println("- Test clear() method:");

        Character[] arrayCharacter = {'+', '*', '/', '='};
        MyArrayList<Character> myArrayList = new MyArrayList<Character>().arrayToList(arrayCharacter);

        myArrayList.clear();
        boolean condition = myArrayList.toString().equals("[]");
        System.out.print("clear() Character - ");
        testMethod(condition);
    }

    /**
     * Checking the isEmpty().
     */
    private static void isEmptyTest() {
        System.out.println("- Test isEmpty() method:");

        MyArrayList<Boolean> myArrayList = new MyArrayList<>();
        Boolean[] arrayBoolean = {true, true, false, true};
        MyArrayList<Boolean> myArrayList2 = new MyArrayList<Boolean>().arrayToList(arrayBoolean);

        boolean condition = myArrayList.isEmpty() && !myArrayList2.isEmpty();
        System.out.print("isEmpty() Boolean - ");
        testMethod(condition);
    }

    /**
     * Checking the contains(E e).
     */
    public static void containsTest() {
        System.out.println("- Test contains(E e) method:");

        String[] arrayString = {"sun", "sea", "sky", "love"};
        MyArrayList<String> myArrayList = new MyArrayList<String>().arrayToList(arrayString);
        boolean condition = myArrayList.contains("sun") && myArrayList.contains("sky") && !myArrayList.contains("snow");
        System.out.print("contains(E e) String - ");
        testMethod(condition);
    }

    /**
     * Checking the indexOf().
     */
    public static void indexOfTest() {
        System.out.println("- Test indexOf() method:");

        Byte[] arrayByte = {0, 7, 10, 7, -2};
        MyArrayList<Byte> myArrayList = new MyArrayList<Byte>().arrayToList(arrayByte);

        boolean condition = myArrayList.indexOf((byte) 7) == 1 && myArrayList.indexOf((byte) 8) == -1;
        System.out.print("indexOf() Byte - ");
        testMethod(condition);
    }

    /**
     * Checking the lastIndexOf().
     */
    public static void lastIndexOfTest() {
        System.out.println("- Test lastIndexOf() method:");

        Double[] arrayDouble = {0.1, 7.0, -10.01, 7.0, 7.0};
        MyArrayList<Double> myArrayList = new MyArrayList<Double>().arrayToList(arrayDouble);

        boolean condition = myArrayList.lastIndexOf(7.0) == myArrayList.size() - 1 && myArrayList.indexOf(8.0) == -1;
        System.out.print("lastIndexOf() Double - ");
        testMethod(condition);
    }

    /**
     * Checking the toArray().
     */
    public static void toArrayTest() {
        System.out.println("- Test toArray() method:");
        Byte[] arrayByte = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22};
        MyArrayList<Byte> myArrayList = new MyArrayList<>();
        for (byte i = 0; i <= 22; i += 2) myArrayList.add(i);

        boolean condition = Arrays.equals(myArrayList.toArray(), arrayByte);
        System.out.print("toArrayTest() Byte - ");
        testMethod(condition);
    }

    /**
     * Checking the arrayToList().
     */
    public static void arrayToListTest() {
        System.out.println("- Test arrayToList method:");
        Singer[] arraySinger = {new Singer("Katy Perry", 106),
                new Singer("Eminem", 150)};
        MyArrayList<Singer> myArrayList = new MyArrayList<Singer>().arrayToList(arraySinger);

        boolean condition = arraySinger.length == myArrayList.size()
                && arraySinger[0].equals(myArrayList.get(0)) && arraySinger[1].equals(myArrayList.get(1));
        System.out.print("arrayToListTest() my Class(Singer) - ");
        testMethod(condition);
    }

    /**
     * Checking the size() with the help of basic methods that change it, also empty list.
     */
    public static void sizeTest() {
        System.out.println("- Test size() method:");
        Boolean[] arrayBoolean = {true, true, false, false, true};
        MyArrayList<Boolean> myArrayList = new MyArrayList<Boolean>().arrayToList(arrayBoolean);

        myArrayList.add(true);
        boolean afterAdd = myArrayList.size() == 6;
        myArrayList.add(3, true);
        boolean afterAddByIndex = myArrayList.size() == 7;
        myArrayList.remove(1);
        boolean afterRemove = myArrayList.size() == 6;
        myArrayList.removeByValue(false);
        boolean afterRemoveByValue = myArrayList.size() == 4;
        myArrayList.clear();
        boolean afterClear = myArrayList.size() == 0;

        myArrayList = new MyArrayList<>();
        boolean condition = afterAdd && afterAddByIndex && afterRemove && afterRemoveByValue
                && afterClear && myArrayList.size() == 0;
        System.out.print("size() Boolean - ");
        testMethod(condition);
    }

    /**
     * Checking the iterator(). Cases: hesNext in empty list, traversal of the filled list through an iterator.
     */
    public static void iteratorTest() {
        System.out.println("- Test iterator() method:");
        Integer[] arrayInteger = {3, 3, 2, 3};
        MyArrayList<Integer> myArrayList = new MyArrayList<Integer>().arrayToList(arrayInteger);
        MyArrayList<Integer> myArrayList2 = new MyArrayList<>();

        boolean hasNextEmptyTest = !myArrayList2.iterator().hasNext();

        Iterator<Integer> iterator = myArrayList.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            myArrayList2.add(i);
        }
        boolean iterationTest = myArrayList.toString().equals(myArrayList2.toString());

        iterator = myArrayList.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) iterator.remove();
        }
        boolean removeIteratorTest = Objects.equals(myArrayList.toString(), "[2]");

        boolean condition = hasNextEmptyTest && iterationTest && removeIteratorTest && myArrayList.size() == 1;
        System.out.print("iterator() Integer - ");
        testMethod(condition);
    }

    /**
     * Checking the toString().
     */
    private static void toStringTest() {
        System.out.println("- Test toString() method:");

        MyArrayList<Byte> myArrayList = new MyArrayList<>();
        for (byte i = 0; i <= 4; i++) myArrayList.add(i);
        MyArrayList<Byte> myArrayList2 = new MyArrayList<>();

        boolean condition = myArrayList.toString().equals("[0, 1, 2, 3, 4]") && myArrayList2.toString().equals("[]");
        System.out.print("toString() Byte - ");
        testMethod(condition);
    }

    /**
     * Prints the test result to the console (the most responsible method :) )
     *
     * @param condition - the result of condition
     */
    private static void testMethod(boolean condition) {
        if (condition) System.out.println(ANSI_GREEN + "true" + ANSI_RESET);
        else System.out.println(ANSI_RED + "false" + ANSI_RESET);
    }

    /**
     * Let it be a test class of its own.
     * Just a class to track singers' wins/
     */
    private static class Singer {
        /* Singer's name/nickname */
        String singerName;
        /* the number of awards and wins */
        int numberAwards;

        public Singer(String singerName, int numberAwards) {
            this.singerName = singerName;
            this.numberAwards = numberAwards;
        }
        public String getSingerName() {
            return singerName;
        }

        public int getNumberAwards() {
            return numberAwards;
        }

        public String toString() {
            return getSingerName() + ": " + getNumberAwards() + " wins";
        }
    }

}

