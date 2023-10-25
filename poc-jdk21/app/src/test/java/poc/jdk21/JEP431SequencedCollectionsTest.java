package poc.jdk21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO ada more examples
//  Java 21 introduced the interface SequencedCollection
class JEP431SequencedCollectionsTest {
    @Test
    void getLastAndFirstElement() {
        var list = List.of(1, 2, 3);

        Assertions.assertEquals(3, list.getLast());
        Assertions.assertEquals(1, list.getFirst());
    }

    @Test
    void addLastAndFirstElement() {
        var list = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        list.addFirst(0);
        list.addLast(4);

        Assertions.assertEquals(4, list.getLast());
        Assertions.assertEquals(0, list.getFirst());
    }

    @Test
    void removeLastAndFirstElement() {
        var list = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        list.removeFirst();
        list.removeLast();

        Assertions.assertEquals(2, list.getLast());
        Assertions.assertEquals(2, list.getFirst());
    }

    @Test
    void reversed() {
        var list = List.of(1, 2, 3);
        Assertions.assertEquals(List.of(3, 2, 1), list.reversed());
    }

}
