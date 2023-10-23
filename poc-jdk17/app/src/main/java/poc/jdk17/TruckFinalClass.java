package poc.jdk17;

public final class TruckFinalClass extends VehicleSealedAbstractClass implements ServiceSealedInterface {

    public TruckFinalClass(String registrationNumber) {
        super(registrationNumber);
    }


    @Override
    public String getImplementedMethod() {
        return "TruckFinalClass";
    }

}