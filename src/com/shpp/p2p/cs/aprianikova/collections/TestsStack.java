package com.shpp.p2p.cs.aprianikova.collections;

import java.util.Iterator;

/**
 * This class tests all the public methods of the StackSimple class.
 * The tests used different types of data.
 * Each test has at least one condition under which it will be considered passed,
 * testMethod(boolean condition) checks whether the condition is met and prints the test result.
 */
public class TestsStack {
    public static final String ANSI_GREEN = "\u001B[32m";

    /*  color for the text of the no passed test */
    public static final String ANSI_RED = "\u001B[31m";

    /* a constant that resets the color */
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        System.out.println("---> Tests for StackSimple:");
        addTest();
        popTest();
        peekTest();
        isEmptyTest();
        clearTest();
        iteratorTest();
        sizeTest();
        arrayToStackTest();
        toStringTest();
    }

    /**
     * Checking the add() (push()) method (inserting an element at the end of the StackSimple)
     */
    private static void addTest() {
        System.out.println("- Test add(E e) method:");

        StackSimple<YouTubeChannel> stack = new StackSimple<>();
        stack.add(new YouTubeChannel("Geek Journal", 337));
        boolean addToEmpty = stack.toString().equals("Stack: [Geek Journal: 337 K subscribers]") && stack.size == 1;

        YouTubeChannel[] array = {new YouTubeChannel("Bezodnya Music", 117),
                new YouTubeChannel("Eileen", 195), new YouTubeChannel("Nina - Ukraine ", 230)};
        stack = new StackSimple<YouTubeChannel>().arrayToStack(array);
        stack.add(new YouTubeChannel("Zagin Kinomaniv", 576));
        boolean addToNotEmptyList = stack.toString().equals("Stack: [Zagin Kinomaniv: 576 K subscribers, Nina - Ukraine : 230 K subscribers," +
                " Eileen: 195 K subscribers, Bezodnya Music: 117 K subscribers]") && stack.size == 4;

        System.out.print("add(E e) my class YouTubeChannel - ");
        testMethod(addToEmpty && addToNotEmptyList);
    }

    /**
     * Checking the pop() method. Cases: when size is 1; when size is 4
     */
    private static void popTest() {
        System.out.println("- Test pop() method:");
        StackSimple<Object> stack = new StackSimple<>();
        stack.add(1);
        Object i = stack.pop();
        boolean pop1element = stack.toString().equals("Stack: []") && stack.size() == 0 && i.equals(1);

        Object[] arrayObject = {0, 0.1, 'g', "Hi!"};
        stack = new StackSimple<>().arrayToStack(arrayObject);
        Object string = stack.pop();
        Object character = stack.pop();

        boolean popIfNotEmptyList = stack.toString().equals("Stack: [0.1, 0]") && stack.size() == 2
                && string.equals("Hi!") && character.equals('g');

        System.out.print("pop() Object - ");
        testMethod(pop1element && popIfNotEmptyList);
    }

    /**
     * Checking the peek() method. Cases: on a queue with 1 element, and checking a queue with 5 elements.
     */
    private static void peekTest() {
        System.out.println("- Test peek() method");

        Byte[] arrayByte = {0};
        StackSimple<Byte> stack = new StackSimple<Byte>().arrayToStack(arrayByte);

        boolean peekIfSize1 = stack.peek().equals((byte) 0) && stack.size() == 1;

        stack.clear();
        for (byte i = 1; i < 6; i++) stack.add(i);
        byte peek = 5;
        boolean peek5elements = stack.peek() == peek && stack.size == 5;

        boolean passed = peekIfSize1 && peek5elements;
        System.out.print("peek() Byte - ");
        testMethod(passed);
    }

    /**
     * Checking the isEmpty().
     */
    private static void isEmptyTest() {
        System.out.println("- Test isEmpty() method:");

        StackSimple<Boolean> stack = new StackSimple<>();
        Boolean[] arrayBoolean = {true, true, false, true};
        StackSimple<Boolean> stack2 = new StackSimple<Boolean>().arrayToStack(arrayBoolean);

        boolean condition = stack.isEmpty() && !stack2.isEmpty();
        System.out.print("isEmpty() Boolean - ");
        testMethod(condition);
    }

    /**
     * Checking the clear().
     */
    private static void clearTest() {
        System.out.println("- Test clear() method:");

        Character[] arrayCharacter = {'!', '#', '^', ')'};
        StackSimple<Character> stack = new StackSimple<Character>().arrayToStack(arrayCharacter);

        stack.clear();
        boolean condition = stack.toString().equals("Stack: []");
        System.out.print("clear() Character - ");
        testMethod(condition);
    }

    /**
     * Checking the arrayToStack(). Cases: if stack is not empty; if stack is empty.
     */
    public static void arrayToStackTest() {
        System.out.println("- Test arrayToStack method:");
        Double[] arrayDouble = {26.0, 10.0, 20.0, 25.01};
        /* since the original array will change: */
        Double[] copy = new Double[arrayDouble.length];
        System.arraycopy(arrayDouble, 0, copy, 0, arrayDouble.length);
        StackSimple<Double> stack = new StackSimple<Double>().arrayToStack(arrayDouble);

        boolean notEmpty = arrayDouble.length == stack.size() && copy[arrayDouble.length - 1].equals(stack.peek())
                && stack.toString().equals("Stack: [25.01, 20.0, 10.0, 26.0]");

        Double[] arrayDouble2 = {};
        StackSimple<Double> stackEmpty = new StackSimple<Double>().arrayToStack(arrayDouble2);

        boolean empty = arrayDouble2.length == stackEmpty.size() && stackEmpty.toString().equals("Stack: []");
        System.out.print("arrayToStack() Double - ");
        testMethod(notEmpty && empty);
    }

    /**
     * Checking the iterator(). Cases: hesNext in empty list, traversal of the filled list through an iterator.
     */
    private static void iteratorTest() {
        System.out.println("- Test iterator() method:");

        Byte[] byteArray = {0, 1, 2, 3, 4};
        StackSimple<Byte> stack = new StackSimple<Byte>().arrayToStack(byteArray);
        StackSimple<Byte> stack2 = new StackSimple<>();

        boolean hasNextEmptyStack = false;
        boolean iterationTest = false;
        if (!stack2.iterator().hasNext()) hasNextEmptyStack = true;

        Iterator<Byte> iterator = stack.iterator();

        while (iterator.hasNext()) {
            Byte b = iterator.next();
            stack2.add(b);
        }
        /* A stack iterator has reverse iteration. Because of this toString() also return the reverse the stack. */
        /* By reversing the addition of elements, stack2 is reversed. */
        /* Therefore, we compare the opposite indices of the stack elements  */
        if (stack.toString().equals("Stack: [4, 3, 2, 1, 0]")
                && stack2.toString().equals("Stack: [0, 1, 2, 3, 4]") && stack.size == stack2.size()) {
            iterationTest = true;
        }

        boolean condition = hasNextEmptyStack && iterationTest;
        System.out.print("iterator() Byte - ");
        testMethod(condition);
    }

    /**
     * Checking the size() with the help of basic methods that change it, also empty stack.
     */
    private static void sizeTest() {
        System.out.println("- Test size() method:");
        Boolean[] arrayBoolean = {true, true, false, false, true};
        StackSimple<Boolean> stack = new StackSimple<Boolean>().arrayToStack(arrayBoolean);
        boolean afterPush = false;
        boolean afterPop = false;
        boolean afterClear = false;

        stack.add(true);
        if (stack.size() == 6) afterPush = true;

        stack.pop();
        if (stack.size() == 5) afterPop = true;

        stack.clear();
        if (stack.size() == 0) afterClear = true;

        stack = new StackSimple<>();

        boolean condition = afterPush && afterPop && afterClear && stack.size() == 0;
        System.out.print("size() Boolean - ");
        testMethod(condition);

    }
    /**
     * Checking the toString().
     */
    private static void toStringTest() {
        System.out.println("- Test toString() method:");

       StackSimple<Integer> stack = new StackSimple<>();
        for (int b = 0; b <= 4; b++) stack.add(b);
       StackSimple<Integer> stack2 = new StackSimple<>();

        boolean condition = stack.toString().equals("Stack: [4, 3, 2, 1, 0]")
                && stack2.toString().equals("Stack: []");
        System.out.print("toString() Integer - ");
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
     * Just a class to track subscribers of YouTube Channel/
     */
    private static class YouTubeChannel {
        String channel;
        int subscribers;

        public YouTubeChannel(String channel, int subscribers) {
            this.channel = channel;
            this.subscribers = subscribers;
        }

        public String getChannel() {
            return channel;
        }

        public int getFollowers() {
            return subscribers;
        }

        public String toString() {
            return getChannel() + ": " + getFollowers() + " K subscribers";
        }
    }

}
