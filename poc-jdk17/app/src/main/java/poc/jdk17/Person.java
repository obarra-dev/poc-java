package poc.jdk17;

public record Person(
        String firstName,
        int age
) {
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be greater than 0!");
        }
    }

    public String getNickName() {
        return firstName.toUpperCase();
    }
}