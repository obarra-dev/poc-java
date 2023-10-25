package poc.jdk21;

public interface GreetingInterface {
    String getName();

    default String greet() {
        return "Hello, " + getName() + " from GreetingInterface";
    }
}
