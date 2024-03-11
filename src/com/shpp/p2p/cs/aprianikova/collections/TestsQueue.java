package com.shpp.p2p.cs.aprianikova.collections;

import java.util.Iterator;

/**
 * This class tests all the public methods of the QueueSimple class.
 * The tests used different types of data.
 * Each test has at least one condition under which it will be considered passed,
 * testMethod(boolean condition) checks whether the condition is met and prints the test result.
 */
public class TestsQueue  {
    /* source: https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/*/
    /* color for the text of the passed test */
    public static final String ANSI_GREEN = "\u001B[32m";

    /*  color for the text of the no passed test */
    public static final String ANSI_RED = "\u001B[31m";

    /* a constant that resets the color */
    public static final String ANSI_RESET = "\u001B[0m";


    public static void main(String[] args) {
        System.out.println("---> Tests for QueueSimple:");
        addTest();
        peekTest();
        removeTest();
        isEmptyTest();
        clearTest();
        iteratorTest();
        sizeTest();
        arrayToQueueTest();
        toStringTest();
    }

    /**
     * Checking the add() method (inserting an element at the end of the QueueSimple)
     */
    private static void addTest() {
        System.out.println("- Test add(E e) method:");

        QueueSimple<City> queue = new QueueSimple<>();
        queue.add(new City("Kyiv", 839));
        boolean addToEmpty = queue.toString().equals("Queue: [Kyiv: 839 km^2]");

        City[] arrayCity = {new City("Kropyvnytskyi", 103), new City("Lviv", 149),
                new City("Lutsk", 42)};
        queue = new QueueSimple<City>().arrayToQueue(arrayCity);
        queue.add(new City("Kharkiv", 350));
        boolean condition = addToEmpty && queue.toString().equals("Queue: [Kropyvnytskyi: 103 km^2, Lviv: 149 km^2, Lutsk: 42 km^2, Kharkiv: 350 km^2]");
        System.out.print("add(E e) my class (City) - ");
        testMethod(condition);
    }

    /**
     * Checking the peek() method. Cases: on a queue with 1 element, and checking a queue with 5 elements.
     */
    private static void peekTest() {
        System.out.println("- Test peek() method");

        Integer[] arrayInteger = {0};
        QueueSimple<Integer> queue = new QueueSimple<Integer>().arrayToQueue(arrayInteger);

        boolean peekIfSize1 = queue.peek().equals(0) && queue.size() == 1;

        queue.clear();
        for (int i = 1; i < 6; i++) queue.add(i);
        boolean peek5elements = queue.peek().equals(1) && queue.size() == 5;

        boolean passed = peekIfSize1 && peek5elements;
        System.out.print("peek() Integer - ");
        testMethod(passed);
    }

    /**
     * Checking the remove() method. Cases: when size is 1; when size is 4
     */
    private static void removeTest() {
        System.out.println("- Test remove() method:");

        QueueSimple<String> queue = new QueueSimple<>();
        queue.add("jump");
        queue.remove();
        boolean remove1element = queue.toString().equals("Queue: []") && queue.size() == 0;

        String[] arrayString = {"swim", "dance", "sing", "win"};
        queue = new QueueSimple<String>().arrayToQueue(arrayString);
        queue.remove();
        boolean removeIfSize4 = queue.toString().equals("Queue: [dance, sing, win]") && queue.size() == 3;

        System.out.print("remove() String - ");
        testMethod(remove1element && removeIfSize4);
    }


    /**
     * Checking the clear().
     */
    private static void clearTest() {
        System.out.println("- Test clear() method:");

        Character[] arrayCharacter = {'+', '*', '/', '='};
        QueueSimple<Character> queue = new QueueSimple<Character>().arrayToQueue(arrayCharacter);

        queue.clear();
        boolean condition = queue.toString().equals("Queue: []");
        System.out.print("clear() Character - ");
        testMethod(condition);
    }

    /**
     * Checking the isEmpty().
     */
    private static void isEmptyTest() {
        System.out.println("- Test isEmpty() method:");

        QueueSimple<Boolean> queue = new QueueSimple<>();
        Boolean[] arrayBoolean = {true, true, false, true};
        QueueSimple<Boolean> queue2 = new QueueSimple<Boolean>().arrayToQueue(arrayBoolean);

        boolean condition = queue.isEmpty() && !queue2.isEmpty();
        System.out.print("isEmpty() Boolean - ");
        testMethod(condition);
    }


    /**
     * Checking the arrayToQueue(). Cases: if queue is not empty, if queue is empty, .
     */
    public static void arrayToQueueTest() {
        System.out.println("- Test arrayToQueue method:");
        Integer[] arrayInteger = {100, 200, 300, 400};
        QueueSimple<Integer> queue = new QueueSimple<Integer>().arrayToQueue(arrayInteger);

        boolean ifNotEmpty = arrayInteger.length == queue.size()
                && arrayInteger[0].equals(queue.peek());

        Integer[] arrayInteger2 = {};
        QueueSimple<Integer> queue2 = new QueueSimple<Integer>().arrayToQueue(arrayInteger2);
        boolean ifEmpty = arrayInteger.length == queue.size()
                && queue2.toString().equals("Queue: []");

        System.out.print("arrayToQueue() Integer - ");
        testMethod(ifNotEmpty && ifEmpty);
    }

    /**
     * Checking the size() with the help of basic methods that change it, also empty queue.
     */
    public static void sizeTest() {
        System.out.println("- Test size() method:");
        Double[] arrayDouble = {0.1, 22.30, -0.4, -16.0, 13.30};
        QueueSimple<Double> queue = new QueueSimple<Double>().arrayToQueue(arrayDouble);
        boolean afterAdd = false;
        boolean afterRemove = false;
        boolean afterClear = false;

        queue.add(13.8);
        if (queue.size() == 6) afterAdd = true;

        queue.remove();
        if (queue.size() == 5) afterRemove = true;

        queue.clear();
        if (queue.size() == 0) afterClear = true;

        queue = new QueueSimple<>();

        boolean condition = afterAdd && afterRemove && afterClear && queue.size() == 0;
        System.out.print("size() Double - ");
        testMethod(condition);
    }

    /**
     * Checking the iterator(). Cases: hesNext in empty list, traversal of the filled list through an iterator.
     */
    public static void iteratorTest() {
        System.out.println("- Test iterator() method:");
        Integer[] arrayInteger = {3, 3, 2, 3};
        QueueSimple<Integer> queue = new QueueSimple<Integer>().arrayToQueue(arrayInteger);
        QueueSimple<Integer> queue2 = new QueueSimple<>();

        boolean hasNextEmptyTest = false;
        boolean iterationTest = false;

        if (!queue2.iterator().hasNext()) hasNextEmptyTest = true;

        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            queue2.add(i);
        }

        if (queue.toString().equals(queue2.toString())) iterationTest = true;

        boolean condition = hasNextEmptyTest && iterationTest;
        System.out.print("iterator() Integer - ");
        testMethod(condition);
    }

    /**
     * Checking the toString().
     */
    private static void toStringTest() {
        System.out.println("- Test toString() method:");

        QueueSimple<Byte> queue = new QueueSimple<>();
        for (byte i = 0; i <= 4; i++) queue.add(i);
        QueueSimple<Byte> queue2 = new QueueSimple<>();

        boolean condition = queue.toString().equals("Queue: [0, 1, 2, 3, 4]")
                && queue2.toString().equals("Queue: []");
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
     * Just a class to track area of city.
     */
    private static class City {
        String city;
        int area;

        public City(String city, int area) {
            this.city = city;
            this.area = area;
        }

        public String getCity() {
            return city;
        }

        public int getArea() {
            return area;
        }

        public String toString() {
            return getCity() + ": " + getArea() + " km^2";
        }
    }
}

