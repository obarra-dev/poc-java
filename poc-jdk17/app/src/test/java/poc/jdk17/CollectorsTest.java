package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class CollectorsTest {

    @Test
    void toList() {
        List<String> integersAsString = List.of("1", "2", "3");
        List<Integer> intsOldWay = integersAsString.stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> intsNewWay = integersAsString.stream().map(Integer::parseInt).toList();

        // TODO is there a toSet??

        Assertions.assertEquals(intsOldWay, intsNewWay);
    }

}
