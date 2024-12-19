package com.obarra.pocjdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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


    // oldest
    Car car = list.stream().sorted((a, b) -> a.getPublishedAt().compareTo(b.getPublishedAt())).findFirst().orElse(null);
    Assertions.assertEquals(new Car(new Date(1000L - 10)), car);
  }


  @Test
  public void maxUsingCustomComparingxx() {
    List<Car> list = Arrays.asList(new Car(new Date(1000L)),
        new Car(new Date(1000L - 10)),
        new Car(new Date(1000L + 10)),
        new Car(new Date(1000L - 10)),
        new Car(new Date(1000L - 10)));

    List<Car> listOldest = getListOfSameOldest(list);

    System.out.println(listOldest);
    Assertions.assertEquals(new Car(new Date(1000L - 10)), listOldest.get(0));
    Assertions.assertEquals(3, listOldest.size());
  }

  @Test
  public void maxUsingCustomComparingx() {
    List<Car> list = Arrays.asList(new Car(new Date(1000L)),
        new Car(new Date(1000L - 10)),
        new Car(new Date(1000L + 10)),
        new Car(new Date(1000L - 10)),
        new Car(new Date(1000L - 11)));

    List<Car> listOldest = getListOfSameOldest(list);

    System.out.println(listOldest);
    Assertions.assertEquals(new Car(new Date(1000L - 11)), listOldest.get(0));
    Assertions.assertEquals(1, listOldest.size());
  }

  @Test
  public void maxUsingCustomComparingxff() {
    List<Car> list = Arrays.asList(new Car(new Date(1000L)),
        new Car(new Date(1000L - 10)),
        new Car(new Date(1000L + 10)),
        new Car(new Date(1000L - 10)),
        new Car(new Date(1000L - 12)),
        new Car(new Date(1000L - 12)),
        new Car(new Date(1000L - 20)),
        new Car(new Date(1000L - 13)),
        new Car(new Date(1000L - 20)),
        new Car(new Date(1000L - 13)));

    List<Car> listOldest = getListOfSameOldest(list);

    System.out.println(listOldest);
    Assertions.assertEquals(new Car(new Date(1000L - 20)), listOldest.get(0));
    Assertions.assertEquals(2, listOldest.size());
  }

  @Test
  public void maxUsingCustomComparingjjx() {
    List<Car> list = Arrays.asList(new Car(new Date(1000L)),
        new Car(new Date(1000L - 12)),
        new Car(new Date(1000L + 10)),
        new Car(new Date(1000L - 12)),
        new Car(new Date(1000L - 11)));

    List<Car> listOldest = getListOfSameOldest(list);

    System.out.println(listOldest);
    Assertions.assertEquals(new Car(new Date(1000L - 12)), listOldest.get(0));
    Assertions.assertEquals(2, listOldest.size());
  }

  @Test
  public void maxUsingCustomComparingjjxk() {
    List<Car> list = Arrays.asList(new Car(new Date(1000L - 12)),
        new Car(new Date(1000L - 12)),
        new Car(new Date(1000L - 12)),
        new Car(new Date(1000L - 12)),
        new Car(new Date(1000L - 12)));

    List<Car> listOldest = getListOfSameOldest(list);

    System.out.println(listOldest);
    Assertions.assertEquals(new Car(new Date(1000L - 12)), listOldest.get(0));
    Assertions.assertEquals(5, listOldest.size());
  }

  private List<Car> getListOfOldest1(final List<Car> list) {
    List<Car> listOldest = new ArrayList<>();
    Car oldest = null;
    for (Car car : list) {
      if (oldest == null || car.getPublishedAt().compareTo(oldest.getPublishedAt()) < 0) {
        oldest = car;
        listOldest.clear();
        listOldest.add(car);
      } else  if(car.getPublishedAt().compareTo(oldest.getPublishedAt()) == 0) {
        listOldest.add(car);
      }
    }

    return listOldest;
  }

  private List<Car> getListOfSameOldest(final List<Car> list) {
    List<Car> listOldest = new ArrayList<>();

    for (Car car : list) {
      if (listOldest.isEmpty()) {
        listOldest.add(car);
        continue;
      }

      int compared = car.getPublishedAt().compareTo(listOldest.get(0).getPublishedAt());
      if (compared > 0) {
        continue;
      }

      if (compared < 0) {
        listOldest.clear();
        listOldest.add(car);
      } else {
        listOldest.add(car);
      }
    }

    return listOldest;
  }
}