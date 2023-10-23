package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandles;

class RandomGeneratorFactoryTest {
    @Test
    void indent() {
        var text = "Hello Team!\nThis is Java 12.";

        var result = text.indent(10);
        Assertions.assertEquals("          Hello Team!\n" +
                "          This is Java 12.\n", result);

        result = result.indent(-10);
        Assertions.assertEquals("Hello Team!\n" +
                "This is Java 12.\n", result);

        result = text.indent(-10);
        Assertions.assertEquals("Hello Team!\n" +
                "This is Java 12.\n", result);
    }

    @Test
    void transform() {
        String text = "Golang";
        String result = text.transform(value ->
                new StringBuilder(value).reverse().toString()
        );

        Assertions.assertEquals("gnaloG", result);
    }

    @Test
    void describeConstable() {
        String text = "Golang";
        var result = text.describeConstable();
        Assertions.assertEquals("Golang", result.get());
    }

    @Test
    void resolveConstantDesc() {
        String text = "Golang";
        // TODO why?
        String result = text.resolveConstantDesc(MethodHandles.lookup());
        Assertions.assertEquals("Golang", result);
    }
}
