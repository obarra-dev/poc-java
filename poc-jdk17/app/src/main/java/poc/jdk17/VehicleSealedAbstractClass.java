package poc.jdk17;

public abstract sealed class VehicleSealedAbstractClass permits CarNonSealedClass, TruckFinalClass, WagonSealedClass {

    protected final String registrationNumber;

    public VehicleSealedAbstractClass(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

}