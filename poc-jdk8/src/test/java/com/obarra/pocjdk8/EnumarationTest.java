package com.obarra.pocjdk8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

class EnumarationTest {

    @Test
    void enumerationList() {
        // String type here- shoppingList
        List<String> shoppingList = new ArrayList<>();

        // Adding element to ArrayList
        // Custom inputs
        shoppingList.add("Perfume");
        shoppingList.add("Clothes");
        shoppingList.add("Sandal");
        shoppingList.add("Jewellery");
        shoppingList.add("Watch");

        // Creating object of type Enumeration<String>
        Enumeration<String> enumeration = Collections.enumeration(shoppingList);
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
    }


    @Test
    void enumerationVector() {
        Enumeration<String> days;
        Vector<String> dayNames = new Vector<>();

        dayNames.add("Sunday");
        dayNames.add("Monday");
        dayNames.add("Tuesday");
        dayNames.add("Wednesday");
        dayNames.add("Thursday");
        dayNames.add("Friday");
        dayNames.add("Saturday");
        days = dayNames.elements();

        while (days.hasMoreElements()) {
            System.out.println(days.nextElement());
        }
    }

    @Test
    void enumerationProperties() {
        Enumeration<Object> days;
        Properties dayNames = new Properties();

        dayNames.put(1, "Sunday");
        dayNames.put(2, "Monday");
        dayNames.put(3, "Tuesday");
        dayNames.put(4, "Wednesday");
        dayNames.put(5, "Thursday");
        dayNames.put(6, "Friday");
        dayNames.put(7, "Saturday");
        days = dayNames.elements();

        while (days.hasMoreElements()) {
            System.out.println(days.nextElement());
        }
    }
}