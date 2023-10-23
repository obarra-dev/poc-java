package poc.jdk17;

class OuterClass {
    class InnerClass {
        UserRecord userRecord = new UserRecord(123, "userFromInnerClass");
    }
}