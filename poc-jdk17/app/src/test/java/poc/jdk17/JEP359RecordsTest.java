package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

// since java 14
// JEP359
class JEP359RecordsTest {

    @Test
    public void recordUsingGetter() {
        User user = new User(10, "UserOne");

        // it does not compile, it is immutable
        // user.id = 12323;
        Assertions.assertEquals(10, user.id());
        Assertions.assertEquals("UserOne", user.password());
    }

    @Test
    public void recordUsingEqual() {
        User user = new User(10, "UserOne");
        User userTwo = new User(10, "UserOne");

        Assertions.assertEquals(userTwo, user);
    }

    @Test
    public void recordUsingToString() {
        User user = new User(10, "UserOne");
        Assertions.assertEquals("User[id=10, password=UserOne]", user.toString());
    }

    @Test
    void recordTest() {
        var person = new Person("Omar", 29);
        Assertions.assertEquals("Omar", person.firstName());
        Assertions.assertEquals(29, person.age());
        Assertions.assertEquals("OMAR", person.getNickName());
        Assertions.assertEquals("Person[firstName=Omar, age=29]", person.toString());
        Assertions.assertTrue(person.equals(new Person("Omar", 29)));
        Assertions.assertFalse(person.equals(new Person("Omarx", 29)));
        Assertions.assertNotNull(person.hashCode());
    }

    /**
     *  Compact Constructor is a point to validation and/or normalization code need to be given in the constructor body.
     */
    @Test
    void compactConstructor() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Person("Omar", -1);
        });
    }
}
