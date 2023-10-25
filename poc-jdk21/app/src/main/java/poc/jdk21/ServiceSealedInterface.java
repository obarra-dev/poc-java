package poc.jdk21;

public sealed interface ServiceSealedInterface permits CarNonSealedClass, TruckFinalClass, WagonSealedClass {

    String getRegistrationNumberByOverrideMethod();

}