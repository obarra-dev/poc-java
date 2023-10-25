package poc.jdk21;

// Since records are implicitly final, the sealed hierarchy is even more concise
public record TruckRecord(String registrationNumber) implements VehicleSealedInterface {

    @Override
    public String getRegistrationNumberByOverrideMethod() {
        return registrationNumber + " by override method in TruckRecord";
    }
}
