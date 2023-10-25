package poc.jdk21;

public abstract sealed class VehicleSealedAbstractClass permits CarNonSealedClass, TruckFinalClass, WagonSealedClass {

    protected final String registrationNumber;

    public VehicleSealedAbstractClass(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumberByMethodFromAbstractClass() {
        return " " + registrationNumber + " by VehicleSealedAbstractClass";
    }

}