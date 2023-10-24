package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// since java 14
// JEP359, JEP384, JEP395
class JEP359RecordsTest {

    @Test
    public void recordUsingGetter() {
        UserRecord userRecord = new UserRecord(10, "UserOne");

        // it does not compile, it is immutable
        // user.id = 12323;
        Assertions.assertEquals(10, userRecord.id());
        Assertions.assertEquals("UserOne", userRecord.password());
    }

    @Test
    public void recordUsingEqual() {
        UserRecord userRecord = new UserRecord(10, "UserOne");
        UserRecord userRecordTwo = new UserRecord(10, "UserOne");

        Assertions.assertEquals(userRecordTwo, userRecord);
    }

    @Test
    public void recordUsingToString() {
        UserRecord userRecord = new UserRecord(10, "UserOne");
        Assertions.assertEquals("UserRecord[id=10, password=UserOne]", userRecord.toString());
    }

    @Test
    void recordTest() {
        var person = new PersonRecord("Omar", 29);
        Assertions.assertEquals("Omar", person.firstName());
        Assertions.assertEquals(29, person.age());
        Assertions.assertEquals("OMAR", person.getNickName());
        Assertions.assertEquals("Person[firstName=Omar, age=29]", person.toString());
        Assertions.assertTrue(person.equals(new PersonRecord("Omar", 29)));
        Assertions.assertFalse(person.equals(new PersonRecord("Omarx", 29)));
        Assertions.assertNotNull(person.hashCode());
    }

    /**
     *  Compact Constructor is a point to validation and/or normalization code need to be given in the constructor body.
     */
    @Test
    void compactConstructor() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new PersonRecord("Omar", -1);
        });
    }

    @Test
    public void defineRecordsAsClassMembersOfInnerClasses() {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();

        Assertions.assertEquals(123, innerClass.userRecord.id());
        Assertions.assertEquals("userFromInnerClass", innerClass.userRecord.password());
    }
}
