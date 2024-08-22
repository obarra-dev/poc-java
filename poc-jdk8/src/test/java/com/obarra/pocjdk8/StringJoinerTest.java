package com.obarra.pocjdk8;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringJoinerTest {
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";

    @Test
    void add() {
        StringJoiner joiner = new StringJoiner(",", PREFIX, SUFFIX);
        joiner.add("Red")
            .add("Green")
            .add("Blue");
        Assertions.assertEquals(joiner.toString(), "[Red,Green,Blue]");

        // Unfortunately, there’s no easy way to  join all elements of a list using StringJoiner
        List<String> rgbList = Arrays.asList("Red", "Green", "Blue");
        joiner = new StringJoiner(",", PREFIX, SUFFIX);
        rgbList.forEach(joiner::add);
        Assertions.assertEquals(joiner.toString(), "[Red,Green,Blue]");
    }

    @Test
    public void setEmptyValue() {
        StringJoiner joiner = new StringJoiner(",", PREFIX, SUFFIX);
        Assertions.assertEquals(joiner.toString(), PREFIX + SUFFIX);

        joiner = new StringJoiner(",", PREFIX, SUFFIX);
        joiner.setEmptyValue("default");
        Assertions.assertEquals(joiner.toString(), "default");

        joiner = new StringJoiner(",");
        joiner.setEmptyValue("default");
        Assertions.assertEquals(joiner.toString(), "default");

        joiner = new StringJoiner(",");
        Assertions.assertEquals(joiner.toString(), "");
    }

    @Test
    public void merge() {
        StringJoiner rgbJoiner = new StringJoiner(
            ",", PREFIX, SUFFIX);
        StringJoiner cmybJoiner = new StringJoiner(
            "-", PREFIX, SUFFIX);

        rgbJoiner.add("Red")
            .add("Green")
            .add("Blue");
        cmybJoiner.add("Cyan")
            .add("Magenta")
            .add("Yellow")
            .add("Black");

        rgbJoiner.merge(cmybJoiner);

        Assertions.assertEquals(
            rgbJoiner.toString(),
            "[Red,Green,Blue,Cyan-Magenta-Yellow-Black]");
    }
}