package poc.jdk21;

public sealed interface VehicleSealedInterface permits CarRecord, TruckRecord {

    String getRegistrationNumberByOverrideMethod();

}