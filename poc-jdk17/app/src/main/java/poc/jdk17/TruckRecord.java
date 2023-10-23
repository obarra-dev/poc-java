package poc.jdk17;

// Since records are implicitly final, the sealed hierarchy is even more concise
public record TruckRecord(int loadCapacity, String registrationNumber) implements VehicleInterface {

    @Override
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

}
