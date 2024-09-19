package com.obarra.pocjdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CollectionsTest
{
  @Test
  public void maxUsingDates() {
    List<Date> list = new ArrayList<>();
    list.add(new Date(1000L));
    list.add(new Date(1000L + 10));

    Date actual = Collections.max(list);
    Assertions.assertEquals(1010L, actual.getTime());
  }

  @Test
  public void maxUsingEmptyList() {
    Assertions.assertThrows(NoSuchElementException.class, () -> Collections.max(new ArrayList<>()));
  }

  @Test
  public void maxUsingComparingInt() {
    List<String> list = Arrays.asList("Java", "Go", "Kotlin");

    String actual = Collections.max(list, Comparator.comparingInt(String::length));
    Assertions.assertEquals("Kotlin", actual);
  }

  @Test
  public void maxUsingCustomComparing() {
    List<Car> list = Arrays.asList(new Car(new Date(1000L)), new Car(new Date(1000L + 10)), new Car(new Date(1000L - 10)));

    Car actual = Collections.max(list, Comparator.comparing(Car::getPublishedAt));

    Assertions.assertEquals(new Car(new Date(1000L + 10)), actual);
  }
}