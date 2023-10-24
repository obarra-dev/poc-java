package poc.jdk17;

// Since records are implicitly final, the sealed hierarchy is even more concise
public record CarRecord(String registrationNumber) implements VehicleSealedInterface {

    @Override
    public String getRegistrationNumberByOverrideMethod() {
        return registrationNumber + " by override method in CarRecord";
    }

}