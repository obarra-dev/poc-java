package poc.jdk21;

public final class TruckFinalClass extends VehicleSealedAbstractClass implements ServiceSealedInterface {

    public TruckFinalClass(String registrationNumber) {
        super(registrationNumber);
    }


    @Override
    public String getRegistrationNumberByOverrideMethod() {
        return registrationNumber + " by override method in TruckFinalClass";
    }

}