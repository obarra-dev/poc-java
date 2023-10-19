package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class OptionalTest {

    @Test
    void isEmpty() {
        var option = Optional.ofNullable(null);
        Assertions.assertTrue(option.isEmpty());

        option = Optional.ofNullable("omar");
        Assertions.assertFalse(option.isEmpty());

        option = Optional.ofNullable("");
        Assertions.assertFalse(option.isEmpty());

        option = Optional.ofNullable("   ");
        Assertions.assertFalse(option.isEmpty());

        // is preset is older, but empty is more easy to read
        option = Optional.ofNullable("   ");
        Assertions.assertTrue(option.isPresent());
    }

}
