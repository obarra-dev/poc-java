package poc.jdk21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;

class CollectionsTest {

    @Test
    void unmodifiableSequencedCollection() {
        SequencedCollection<Integer> listModifiable = new ArrayList<>(Arrays.asList(1, 2, 3));
        SequencedCollection<Integer> list = Collections.unmodifiableSequencedCollection(listModifiable);

        Assertions.assertEquals(1, list.getFirst());
        Assertions.assertEquals(3, list.getLast());

        Assertions.assertThrows(UnsupportedOperationException.class, list::removeFirst);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            list.addLast(3);
        });
    }

    @Test
    void unmodifiableSequencedSet() {
        SequencedSet<Integer> setModifiable = new LinkedHashSet<>();
        setModifiable.add(1);
        setModifiable.add(2);
        setModifiable.add(3);

        SequencedCollection<Integer> set = Collections.unmodifiableSequencedSet(setModifiable);

        Assertions.assertEquals(1, set.getFirst());
        Assertions.assertEquals(3, set.getLast());

        Assertions.assertThrows(UnsupportedOperationException.class, set::removeFirst);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            set.addLast(3);
        });
    }


    @Test
    void unmodifiableSequencedMap() {
        SequencedMap<String, Integer> mapModifiable = new LinkedHashMap<>();
        mapModifiable.put("one", 1);
        mapModifiable.put("two", 2);
        mapModifiable.put("three", 3);

        SequencedMap<String, Integer> map = Collections.unmodifiableSequencedMap(mapModifiable);

        Assertions.assertEquals(Map.entry("one", 1), map.firstEntry());
        Assertions.assertEquals(Map.entry("three", 3), map.lastEntry());

        Assertions.assertThrows(UnsupportedOperationException.class, map::pollFirstEntry);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            map.putLast("three", 3);
        });
    }

}
