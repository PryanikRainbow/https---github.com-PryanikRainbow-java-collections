package com.shpp.p2p.cs.aprianikova.collections;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;

/**
 * This class tests all the public methods of the MyLinkedList class.
 * The tests used different types of data.
 * Each test has at least one condition under which it will be considered passed,
 * testMethod(boolean condition) checks whether the condition is met and prints the test result.
 */
public class TestsLinkedList {
    /* source: https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/*/
    /* color for the text of the passed test */
    public static final String ANSI_GREEN = "\u001B[32m";

    /*  color for the text of the no passed test */
    public static final String ANSI_RED = "\u001B[31m";

    /* a constant that resets the color */
    public static final String ANSI_RESET = "\u001B[0m";


    public static void main(String[] args) {
        System.out.println("---> Tests for MyLinkedList:");
        setTest();
        getTest();
        getFirstTest();
        getLastTest();
        addTest();
        addFirstTest();
        addLastTest();
        addByIndexTest();
        removeByIndexTest();
        removeByValueTest();
        removeFirstTest();
        removeLastTest();
        clearTest();
        isEmptyTest();
        containsTest();
        indexOfTest();
        lastIndexOfTest();
        toArrayTest();
        arrayToListTest();
        sizeTest();
        iteratorTest();
        iteratorRemoveTest();
        toStringTest();
    }


    /**
     * Checking the set() method by the first index, the index inside the list, the last index.
     */
    private static void setTest() {
        System.out.println("- Test set(int index, E e) method");

        Boolean[] arrayBoolean = {false, false, false, false};
        MyLinkedList<Boolean> linkedList = new MyLinkedList<Boolean>().arrayToList(arrayBoolean);
        for (int i = 0; i < linkedList.size(); i++) linkedList.set(i, true);

        Boolean[] arrayBoolean2 = {true};
        MyLinkedList<Boolean> linkedList2 = new MyLinkedList<Boolean>().arrayToList(arrayBoolean2);
        for (int i = 0; i < linkedList2.size(); i++) linkedList2.set(i, false);

        boolean passed = linkedList.toString().equals("[true, true, true, true]") && linkedList2.toString().equals("[false]");
        System.out.print("Test set: Boolean - ");
        testMethod(passed);
    }

    /**
     * Checking the get() method by the first index,
     * the index inside the list (first and second half of the list), the last index.
     */
    private static void getTest() {
        System.out.println("- Test get(int index) method");

        Object[] arrayObject = {false, 'a', 1, "Wow!", 0.1};
        MyLinkedList<Object> linkedList = new MyLinkedList<>().arrayToList(arrayObject);

        Object elementO = linkedList.get(0);
        Object element1 = linkedList.get(1);
        Object element2 = linkedList.get(3);
        Object element3 = linkedList.get(linkedList.size() - 1);

        Object[] arrayObject2 = {'1'};
        MyLinkedList<Object> linkedList2 = new MyLinkedList<>().arrayToList(arrayObject2);

        boolean passed = elementO.equals(false) && element1.equals('a') && element2.equals("Wow!")
                && element3.equals(0.1) && linkedList2.get(0).equals('1');
        System.out.print("get (E e, Object) - ");
        testMethod(passed);
    }

    /**
     * Checking the getFirst() method. Cases: a list with 1 element, and checking a list with 5 elements.
     */
    private static void getFirstTest() {
        System.out.println("- Test getFirst() method");

        Integer[] arrayInteger = {0};
        MyLinkedList<Object> linkedList = new MyLinkedList<>().arrayToList(arrayInteger);

        boolean getFirst1element = linkedList.getFirst().equals(0) && linkedList.getFirst().equals(linkedList.getLast());

        linkedList.clear();
        for (int i = 1; i < 6; i++) linkedList.add(i);
        boolean getFirst5elements = linkedList.getFirst().equals(1) && !linkedList.getFirst().equals(linkedList.getLast());

        boolean passed = getFirst1element && getFirst5elements;
        System.out.print("getFirst() Integer - ");
        testMethod(passed);
    }

    /**
     * Checking the getFirst() method. Cases: a list with 1 element, and checking a list with 5 elements.
     */
    private static void getLastTest() {
        System.out.println("- Test getLast() method");

        Integer[] arrayInteger = {0};
        MyLinkedList<Object> linkedList = new MyLinkedList<>().arrayToList(arrayInteger);

        boolean getFirst1element = linkedList.getLast().equals(0) && linkedList.getFirst().equals(linkedList.getLast());

        linkedList.clear();
        for (int i = 1; i < 6; i++) linkedList.add(i);
        boolean getFirst5elements = linkedList.getLast().equals(5) && !linkedList.getFirst().equals(linkedList.getLast());

        boolean passed = getFirst1element && getFirst5elements;
        System.out.print("getLast() Integer - ");
        testMethod(passed);
    }

    /**
     * Checking the add() method (inserting an element at the end of the MyLinedList).
     */
    private static void addTest() {
        System.out.println("- Test add(E e) method:");

        MyLinkedList<Byte> linkedList = new MyLinkedList<>();
        linkedList.add((byte) 0);
        boolean add1element = linkedList.toString().equals("[0]");
        for (byte i = 0; i <= 10; i++) linkedList.add(i);
        linkedList.add((byte) 11);

        boolean condition = add1element && linkedList.toString().equals("[0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]");
        System.out.print("add(E e) Byte - ");
        testMethod(condition);
    }

    /**
     * Checking the addFirst() method. Insertion into an empty list; insertion into a not empty list
     */
    private static void addFirstTest() {
        System.out.println("- Test addFirst() method:");

        MyLinkedList<Country> linkedList = new MyLinkedList<>();
        linkedList.addFirst(new Country("South Korea", 100363));
        boolean add1element = linkedList.toString().equals("[South Korea: 100363 km^2]") && linkedList.getFirst().equals(linkedList.getLast());

        Country[] arrayCountries = {new Country("Austria", 83871), new Country("Georgia", 69700)};
        linkedList = new MyLinkedList<Country>().arrayToList(arrayCountries);
        linkedList.addFirst(new Country("Greece", 131957));
        boolean addInNotEmptyList = linkedList.toString().equals("[Greece: 131957 km^2, Austria: 83871 km^2, Georgia: 69700 km^2]")
                && !linkedList.getFirst().equals(linkedList.getLast());

        System.out.print("addFirst(E e) my class (Country) - ");
        testMethod(addInNotEmptyList && add1element);
    }

    /**
     * Checking the addLast() method. Insertion into an empty list; insertion into a not empty list
     */
    private static void addLastTest() {
        System.out.println("- Test addLast() method:");

        MyLinkedList<Boolean> linkedList = new MyLinkedList<>();
        linkedList.addLast(true);
        boolean add1element = linkedList.toString().equals("[true]") && linkedList.getFirst().equals(linkedList.getLast());

        Boolean[] arrayBoolean = {false, false, false};
        linkedList = new MyLinkedList<Boolean>().arrayToList(arrayBoolean);
        linkedList.addLast(true);
        boolean addInNotEmptyList = linkedList.toString().equals("[false, false, false, true]") && !linkedList.getFirst().equals(linkedList.getLast());

        System.out.print("addLast(E e) Boolean - ");
        testMethod(addInNotEmptyList && add1element);
    }

    /**
     * Checking add(int index) method by the last index, first index,
     * index inside the list (first and second halves).
     */
    private static void addByIndexTest() {
        System.out.println("- Test add(int index, E e) method:");

        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        for (int i = 0; i <= 4; i++) myLinkedList.add(i);
        myLinkedList.add(5, myLinkedList.size());
        boolean indexLast = myLinkedList.toString().equals("[0, 1, 2, 3, 4, 5]");
        System.out.print("add(at last position, Integer) - ");
        testMethod(indexLast);

        String[] arrayString = {"dog", "cat", "bird"};
        MyLinkedList<String> myLinkedList2 = new MyLinkedList<String>().arrayToList(arrayString);
        myLinkedList2.add(0, "unicorn");
        boolean index0 = myLinkedList2.toString().equals("[unicorn, dog, cat, bird]");
        System.out.print("add(at first position, String) - ");
        testMethod(index0);

        Country[] arrayCountries = {new Country("Spain", 505990),
                new Country("Czech Republic", 78867), new Country("Norway", 385207)};
        MyLinkedList<Country> myLinkedList3 = new MyLinkedList<Country>().arrayToList(arrayCountries);
        myLinkedList3.add(1, new Country("Great Britain", 243610));

        boolean addToFirstHalf = myLinkedList3.toString().equals("[Spain: 505990 km^2, Great Britain: 243610 km^2," +
                " Czech Republic: 78867 km^2, Norway: 385207 km^2]");
        System.out.print("add(add to first half, my Class(Country)) - ");
        testMethod(addToFirstHalf);

        myLinkedList3.add(3, new Country("Cyprus", 9251));
        boolean addToSecondHalf = myLinkedList3.toString().equals("[Spain: 505990 km^2, Great Britain: 243610 km^2," +
                " Czech Republic: 78867 km^2, Cyprus: 9251 km^2, Norway: 385207 km^2]");
        System.out.print("add(add to second half, my Class(Country)) - ");
        testMethod(addToSecondHalf);
    }


    /**
     * Checking the remove(int index) method by the first index, index inside the list, last index.
     */
    private static void removeByIndexTest() {
        System.out.println("- Test remove(int index) method:");

        Double[] arrayDouble = {0.25, 9.5, -7.0, 25.0, -0.21, 3.0, 0.216};
        MyLinkedList<Double> myLinkedList = new MyLinkedList<Double>().arrayToList(arrayDouble);

        myLinkedList.remove(0);
        boolean index0 = myLinkedList.toString().equals("[9.5, -7.0, 25.0, -0.21, 3.0, 0.216]");
        System.out.print("remove(first index) Double - ");
        testMethod(index0);

        myLinkedList.remove(1);
        boolean firstHalf = myLinkedList.toString().equals("[9.5, 25.0, -0.21, 3.0, 0.216]");
        System.out.print("remove(index in first half) Double - ");
        testMethod(firstHalf);

        myLinkedList.remove(3);
        boolean secondHalf = myLinkedList.toString().equals("[9.5, 25.0, -0.21, 0.216]");
        System.out.print("remove(index in second half) Double - ");
        testMethod(secondHalf);

        myLinkedList.remove(myLinkedList.size() - 1);
        boolean condition4 = myLinkedList.toString().equals("[9.5, 25.0, -0.21]");
        System.out.print("remove(last index) Double - ");
        testMethod(condition4);
    }

    /**
     * Checking the removeFirst() method. Cases: when size 1; when the list is not empty
     */
    private static void removeFirstTest() {
        System.out.println("- Test removeFist() method:");

        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("Python");
        linkedList.removeFirst();
        boolean remove1element = linkedList.toString().equals("[]") && linkedList.size() == 0;

        String[] arrayString = {"Scala", "Java", "JS", "PHP"};
        linkedList = new MyLinkedList<String>().arrayToList(arrayString);
        linkedList.removeFirst();
        boolean removeIfNotEmptyList = linkedList.toString().equals("[Java, JS, PHP]") && linkedList.size() == 3;

        System.out.print("removeFirst(E e) String - ");
        testMethod(remove1element && removeIfNotEmptyList);
    }

    /**
     * Checking the removeLast() method. Cases: when size 1; when the list is not empty
     */
    private static void removeLastTest() {
        System.out.println("- Test removeLast() method:");

        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.add(0);
        linkedList.removeLast();
        boolean remove1element = linkedList.toString().equals("[]") && linkedList.size() == 0;

        Integer[] arrayInteger = {0, 1, 2, 3, 4};
        linkedList = new MyLinkedList<Integer>().arrayToList(arrayInteger);
        linkedList.removeLast();
        boolean removeIfNotEmptyList = linkedList.toString().equals("[0, 1, 2, 3]") && linkedList.size() == 4;

        System.out.print("removeLast(E e) Integer - ");
        testMethod(remove1element && removeIfNotEmptyList);

    }

    /**
     * Checking the removeByValue(E e). Case: element to be removed with first index, middle index, last index.
     * + testing iterator.remove() (inside removeByValue(E e).
     */
    private static void removeByValueTest() {
        System.out.println("- Test removeByValue(E e) method:");

        Boolean[] arrayBoolean = {true, true, false, true};
        MyLinkedList<Boolean> linkedList = new MyLinkedList<Boolean>().arrayToList(arrayBoolean);

        linkedList.removeByValue(true);
        boolean condition = linkedList.toString().equals("[false]");
        System.out.print("removeByValue(E e) Boolean - ");
        testMethod(condition);
    }

    /**
     * Checking the clear().
     */
    private static void clearTest() {
        System.out.println("- Test clear() method:");

        Character[] arrayCharacter = {'+', '*', '/', '='};
        MyLinkedList<Character> linkedList = new MyLinkedList<Character>().arrayToList(arrayCharacter);

        linkedList.clear();
        boolean condition = linkedList.toString().equals("[]");
        System.out.print("clear() Character - ");
        testMethod(condition);
    }

    /**
     * Checking the isEmpty().
     */
    private static void isEmptyTest() {
        System.out.println("- Test isEmpty() method:");

        MyLinkedList<Boolean> myLinkedList = new MyLinkedList<>();
        Boolean[] arrayBoolean = {true, true, false, true};
        MyLinkedList<Boolean> myLinkedList2 = new MyLinkedList<Boolean>().arrayToList(arrayBoolean);

        boolean condition = myLinkedList.isEmpty() && !myLinkedList2.isEmpty();
        System.out.print("isEmpty() Boolean - ");
        testMethod(condition);
    }

    /**
     * Checking the contains(E e).
     */
    public static void containsTest() {
        System.out.println("- Test contains(E e) method:");

        String[] arrayString = {"sun", "sea", "sky", "love"};
        MyLinkedList<String> linkedList = new MyLinkedList<String>().arrayToList(arrayString);

        MyLinkedList<Byte> linkedList2 = new MyLinkedList<>();
        boolean condition = linkedList.contains("sun") && linkedList.contains("sky")
                && !linkedList.contains("snow") && !linkedList2.contains("sky");
        System.out.print("contains(E e) String - ");
        testMethod(condition);
    }

    /**
     * Checking the indexOf().
     */
    private static void indexOfTest() {
        System.out.println("- Test indexOf() method:");

        String[] arrayString = {"blue", "green", "yellow"};
        MyLinkedList<String> myLinkedList = new MyLinkedList<String>().arrayToList(arrayString);

        boolean condition = myLinkedList.indexOf("green") == 1 && myLinkedList.indexOf("dark") == -1;
        System.out.print("indexOf() String - ");
        testMethod(condition);
    }

    /**
     * Checking the lastIndexOf().
     */
    public static void lastIndexOfTest() {
        System.out.println("- Test lastIndexOf() method:");

        Double[] arrayDouble = {0.1, 7.0, -10.01, 7.0, 7.0};
        MyLinkedList<Double> myLinkedList = new MyLinkedList<Double>().arrayToList(arrayDouble);

        boolean condition = myLinkedList.lastIndexOf(7.0) == myLinkedList.size() - 1 && myLinkedList.indexOf(8.0) == -1;
        System.out.print("lastIndexOf() Double - ");
        testMethod(condition);
    }

    /**
     * Checking the toArray().
     */
    public static void toArrayTest() {
        System.out.println("- Test toArray() method:");

        Byte[] arrayByte = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22};
        MyLinkedList<Byte> linkedList = new MyLinkedList<>();
        for (byte i = 0; i <= 22; i += 2) linkedList.add(i);

        Byte[] arrayByte2 = {};
        MyLinkedList<Byte> myLinkedList2 = new MyLinkedList<Byte>().arrayToList(arrayByte2);

        boolean condition = Arrays.equals(linkedList.toArray(), arrayByte)
                && Arrays.equals(myLinkedList2.toArray(), arrayByte2);
        System.out.print("toArrayTest() Byte - ");
        testMethod(condition);
    }

    /**
     * Checking the arrayToList().
     */
    public static void arrayToListTest() {
        System.out.println("- Test arrayToList method:");
        Country[] arrayCountries = {new Country("Latvia", 64589),
                new Country("USA", 9833520), new Country("Estonia", 45226)};
        MyLinkedList<Country> linkedList = new MyLinkedList<Country>().arrayToList(arrayCountries);

        boolean condition = arrayCountries.length == linkedList.size()
                && arrayCountries[0].equals(linkedList.get(0)) && arrayCountries[1].equals(linkedList.get(1));
        System.out.print("arrayToList() my class(Country) - ");
        testMethod(condition);
    }

    /**
     * Checking the size() with the help of basic methods that change it, also empty list.
     */
    public static void sizeTest() {
        System.out.println("- Test size() method:");
        Boolean[] arrayBoolean = {true, true, false, false, true};
        MyLinkedList<Boolean> linkedList = new MyLinkedList<Boolean>().arrayToList(arrayBoolean);

        linkedList.add(true);
        boolean afterAdd = linkedList.size() == 6;
        linkedList.add(3, true);
        boolean afterAddByIndex = linkedList.size() == 7;
        linkedList.remove(1);
        boolean afterRemove = linkedList.size() == 6;
        linkedList.removeByValue(false);
        boolean afterRemoveByValue = linkedList.size() == 4;
        linkedList.clear();
        boolean afterClear = linkedList.size() == 0;
        linkedList.addFirst(false);
        boolean afterAddFirst = linkedList.size() == 1;
        linkedList.removeLast();
        boolean afterRemoveLast = linkedList.size() == 0;
        linkedList.addLast(false);
        boolean afterAddLast = linkedList.size() == 1;
        linkedList.removeFirst();
        boolean afterRemoveFirst = linkedList.size() == 0;


        linkedList = new MyLinkedList<>();

        boolean condition = afterAdd && afterAddByIndex && afterRemove && afterRemoveByValue && afterClear
                && afterAddFirst && afterRemoveLast && afterAddLast && afterRemoveFirst && linkedList.size() == 0;
        System.out.print("size() Boolean - ");
        testMethod(condition);
    }

    /**
     * Checking the iterator(). Cases: hesNext in empty list, traversal of the filled list through an iterator.
     */
    public static void iteratorTest() {
        System.out.println("- Test iterator() method:");
        Integer[] arrayInteger = {3, 3, 2, 3};
        MyLinkedList<Integer> linkedList = new MyLinkedList<Integer>().arrayToList(arrayInteger);

        MyLinkedList<Integer> linkedList2 = new MyLinkedList<>();
        boolean hasNextEmptyTest = !linkedList2.iterator().hasNext();

        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            linkedList2.add(i);
        }
        boolean iterationTest = linkedList.toString().equals(linkedList2.toString());

        boolean condition = hasNextEmptyTest && iterationTest;
        System.out.print("iterator() Integer - ");
        testMethod(condition);
    }

    /**
     * Checking iterator().remove() method.
     * Test case: removing all list elements; removing element with list size 1; removing different elements.
     */
    private static void iteratorRemoveTest() {
        System.out.println("- Test iterator().remove() method:");
        // 1)
        Country[] countriesArray = {new Country("Ukraine", 603700),
                new Country("Poland", 312685), new Country("Lithuania", 65300)};
        MyLinkedList<Country> linkedList = new MyLinkedList<Country>().arrayToList(countriesArray);

        Iterator<Country> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Country country = iterator.next();
            iterator.remove();
        }
        boolean removeAll = linkedList.toString().equals("[]");

        //2)
        Color[] colorArray = {Color.white};
        MyLinkedList<Color> myLinkedList2 = new MyLinkedList<Color>().arrayToList(colorArray);

        Iterator<Color> iterator2 = myLinkedList2.iterator();
        while (iterator2.hasNext()) {
            Color color = iterator2.next();
            iterator2.remove();
        }
        boolean ifSize1 = myLinkedList2.toString().equals("[]");

        //3)
        Character[] charArray = {'a', 'b', 'c', 'd', 'e', 'f'};
        MyLinkedList<Character> myLinkedList3 = new MyLinkedList<Character>().arrayToList(charArray);

        Iterator<Character> iterator3 = myLinkedList3.iterator();
        while (iterator3.hasNext()) {
            Character c = iterator3.next();
            if (c.equals('a') || c.equals('c') || c.equals('e') || c.equals('f')) {
                iterator3.remove();
            }
        }
        boolean differentPositions = myLinkedList3.toString().equals("[b, d]");

        boolean condition = removeAll && ifSize1 && differentPositions
                && myLinkedList3.size() == 2 && linkedList.size() == 0;
        System.out.print("iterator().remove() my Class(Country); Color; Character - ");
        testMethod(condition);
    }

    /**
     * Checking the toString().
     */
    private static void toStringTest() {
        System.out.println("- Test toString() method:");

        MyLinkedList<Byte> linkedList = new MyLinkedList<>();
        for (byte i = 0; i <= 4; i++) linkedList.add(i);
        MyLinkedList<Byte> linkedList2 = new MyLinkedList<>();

        boolean condition = linkedList.toString().equals("[0, 1, 2, 3, 4]")
                && linkedList2.toString().equals("[]");
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
     * Just a class to track area of country/
     */
    private static class Country {
        String country;
        int area;

        public Country(String country, int area) {
            this.country = country;
            this.area = area;
        }

        public String getCountry() {
            return country;
        }

        public int getArea() {
            return area;
        }

        public String toString() {
            return getCountry() + ": " + getArea() + " km^2";
        }
    }
}
