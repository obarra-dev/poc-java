package poc.jdk17;

// if we declare it non-sealed, then it is open for extension
public non-sealed class CarNonSealedClass extends VehicleSealedAbstractClass implements ServiceSealedInterface {

    public CarNonSealedClass(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public String getRegistrationNumberByOverrideMethod() {
        return registrationNumber + " by override method in CarNonSealedClass";
    }

}