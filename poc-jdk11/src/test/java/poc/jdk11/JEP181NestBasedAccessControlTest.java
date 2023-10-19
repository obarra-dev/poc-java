package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * it is a Java compiler’s optimization to remove the bridge method access.
 * Before Java 11, it creates a new access$000 bridge method for the nested private access automatically.
 */
class JEP181NestBasedAccessControlTest {

    @Test
    void getNestHost() {
        OuterClass outerClass = new OuterClass();
        Assertions.assertEquals("poc.jdk11.OuterClass", outerClass.getClass().getNestHost().getName());

        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        Assertions.assertEquals("poc.jdk11.OuterClass", innerClass.getClass().getNestHost().getName());
    }

    @Test
    void isNestmateOf() {
        OuterClass outerClass = new OuterClass();
        Assertions.assertTrue(outerClass.getClass().isNestmateOf(OuterClass.InnerClass.class));

        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        Assertions.assertTrue(innerClass.getClass().isNestmateOf(OuterClass.class));

        Assertions.assertFalse(innerClass.getClass().isNestmateOf(Double.class));
    }

    @Test
    void getNestMembers() {
        var nestMembers = Arrays.stream(OuterClass.InnerClass.class.getNestMembers())
                .map(Class::getName)
                .collect(Collectors.toList());

        Assertions.assertEquals(List.of("poc.jdk11.OuterClass", "poc.jdk11.OuterClass$InnerClass"), nestMembers);
    }

}
