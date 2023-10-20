package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

// since java 9
class ProcessAPITest {

    @Test
    void processHandle() {
        ProcessHandle current = ProcessHandle.current();
        Assertions.assertNotNull( current.pid());
        Assertions.assertTrue(current.isAlive());

        Optional<String> user = current.info().user();
        Assertions.assertFalse(user.isEmpty());
    }
}
