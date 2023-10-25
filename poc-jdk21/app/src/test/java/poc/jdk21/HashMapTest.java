package poc.jdk21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

class HashMapTest {

    // TODO do the same test for LinkedHashMap and WeakHashMap
    @Test
    void newHashMap() {
        // new way to define the initial capacity without care about the load factor 0.75
        Map<String, Integer> mapNewWay = HashMap.newHashMap(120);

        // for 120 mappings: 120 / 0.75 = 160
        Map<String, Integer> mapOldWay = new HashMap<>(160);

        Assertions.assertEquals(mapOldWay, mapNewWay);
    }
}
