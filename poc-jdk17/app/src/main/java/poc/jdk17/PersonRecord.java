package poc.jdk17;

public record PersonRecord(
        String firstName,
        int age
) {
    public PersonRecord {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be greater than 0!");
        }
    }

    public String getNickName() {
        return firstName.toUpperCase();
    }
}