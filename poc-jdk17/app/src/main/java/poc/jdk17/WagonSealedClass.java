package poc.jdk17;

public sealed class WagonSealedClass extends VehicleSealedAbstractClass implements ServiceSealedInterface permits WagonBolivia {

    public WagonSealedClass(String registrationNumber) {
        super(registrationNumber);
    }


    @Override
    public String getImplementedMethod() {
        return "WagonSealedClass";
    }

}