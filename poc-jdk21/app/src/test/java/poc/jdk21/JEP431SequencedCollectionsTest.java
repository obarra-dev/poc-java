package poc.jdk21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;
import java.util.Set;

// TODO add more examples
//  Java 21 introduced the interfaces SequencedCollection, SequencedSet, SequencedMap
class JEP431SequencedCollectionsTest {

    @Test
    void sequencedCollection() {
        SequencedCollection<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

        Assertions.assertEquals(1, list.getFirst());
        Assertions.assertEquals(3, list.getLast());

        Assertions.assertEquals(1, list.removeFirst());
        Assertions.assertEquals(3, list.removeLast());
        Assertions.assertEquals(2, list.getLast());
        Assertions.assertEquals(2, list.getFirst());

        list.addFirst(1);
        list.addLast(3);
        Assertions.assertEquals(1, list.getFirst());
        Assertions.assertEquals(3, list.getLast());

        Assertions.assertEquals(List.of(3, 2, 1), list.reversed());
    }

    @Test
    void sequencedSet() {
        SequencedSet<Integer> set = new LinkedHashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        Assertions.assertEquals(1, set.getFirst());
        Assertions.assertEquals(3, set.getLast());

        Assertions.assertEquals(1, set.removeFirst());
        Assertions.assertEquals(3, set.removeLast());
        Assertions.assertEquals(2, set.getLast());
        Assertions.assertEquals(2, set.getFirst());

        set.addFirst(1);
        set.addLast(3);
        Assertions.assertEquals(1, set.getFirst());
        Assertions.assertEquals(3, set.getLast());

        Assertions.assertEquals(Set.of(3, 2, 1), set.reversed());
    }

    @Test
    void sequencedMap() {
        SequencedMap<String, Integer> map = new LinkedHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        Assertions.assertEquals(Map.entry("one", 1), map.firstEntry());
        Assertions.assertEquals(Map.entry("three", 3), map.lastEntry());


        Assertions.assertEquals(Map.entry("one", 1), map.pollFirstEntry());
        Assertions.assertEquals(Map.entry("three", 3), map.pollLastEntry());

        Assertions.assertEquals(Map.entry("two", 2), map.firstEntry());
        Assertions.assertEquals(Map.entry("two", 2), map.lastEntry());


        map.putFirst("one", 1);
        map.putLast("three", 3);
        Assertions.assertEquals(Map.entry("one", 1), map.firstEntry());
        Assertions.assertEquals(Map.entry("three", 3), map.lastEntry());

        SequencedMap<String, Integer> mapReversed = new LinkedHashMap<>();
        mapReversed.put("three", 3);
        mapReversed.put("two", 2);
        mapReversed.put("one", 1);
        Assertions.assertEquals(mapReversed, map.reversed());


        // TODO
        //    SequencedSet<K> sequencedKeySet();
        //    SequencedCollection<V> sequencedValues();
        //    SequencedSet<Entry<K, V>> sequencedEntrySet();
    }
}
