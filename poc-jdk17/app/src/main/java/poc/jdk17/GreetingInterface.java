package poc.jdk17;

public interface GreetingInterface {
    String getName();

    default String greet() {
        return "Hello, " + getName() + " from GreetingInterface";
    }
}
