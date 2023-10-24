package poc.jdk17;

public sealed interface ServiceSealedInterface permits CarNonSealedClass, TruckFinalClass, WagonSealedClass {

    String getRegistrationNumberByOverrideMethod();

}