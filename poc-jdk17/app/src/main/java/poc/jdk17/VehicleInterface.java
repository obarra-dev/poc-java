package poc.jdk17;

public sealed interface VehicleInterface permits CarRecord, TruckRecord {

    String getRegistrationNumber();

}