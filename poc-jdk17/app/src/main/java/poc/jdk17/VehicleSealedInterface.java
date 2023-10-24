package poc.jdk17;

public sealed interface VehicleSealedInterface permits CarRecord, TruckRecord {

    String getRegistrationNumberByOverrideMethod();

}