package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

// since java 9
class CompletableFutureTest {

    // TODO test rest of methods
    @Test
    void delayedExecutor() {
        Executor executor = CompletableFuture.delayedExecutor(2L, TimeUnit.SECONDS);
        executor.execute(() -> System.out.println("Hi"));
    }
}
