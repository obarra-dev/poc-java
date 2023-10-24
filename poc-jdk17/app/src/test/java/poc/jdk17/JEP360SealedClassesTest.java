package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.constant.ClassDesc;
import java.util.Arrays;
import java.util.Optional;

// Sealed Classes and Interfaces in Java
// The main motivation behind sealed classes is to have the possibility for a superclass to be widely accessible but not widely extensible
// Constrains
//All permitted subclasses must belong to the same module as the sealed class.
//Every permitted subclass must explicitly extend the sealed class.
//Every permitted subclass must define a modifier: final, sealed, or non-sealed.
// since java 15
// JEP360, JEP397
class JEP360SealedClassesTest {

    @Test
    void patterMatchingWithSealedInterface() {
        var result = getRegistrationNumberByOverrideMethod(new CarNonSealedClass("123"));
        Assertions.assertEquals("123 by override method in CarNonSealedClass", result);

        result = getRegistrationNumberByOverrideMethod(new TruckFinalClass("123"));
        Assertions.assertEquals("123 by override method in TruckFinalClass", result);

        result = getRegistrationNumberByOverrideMethod(new WagonSealedClass("123"));
        Assertions.assertEquals("123 by override method in WagonSealedClass", result);

        result = getRegistrationNumberByOverrideMethod(new WagonBolivia("123"));
        Assertions.assertEquals("123 by override method in WagonSealedClass", result);
    }

    private static String getRegistrationNumberByOverrideMethod(ServiceSealedInterface vehicle) {
        String result;
        if (vehicle instanceof CarNonSealedClass car) {
            result = car.getRegistrationNumberByOverrideMethod();
        } else if (vehicle instanceof TruckFinalClass truck) {
            result = truck.getRegistrationNumberByOverrideMethod();
        } else if (vehicle instanceof WagonSealedClass wagon) {
            result = wagon.getRegistrationNumberByOverrideMethod();
        } else {
            throw new RuntimeException("Unknown instance of Vehicle");
        }
        return result;
    }


    //
    @Test
    void patterMatchingWithSealedClass() {
        var result = getRegistrationNumberByOverrideMethodUsingVehicleSealedAbstractClass(new CarNonSealedClass("123"));
        Assertions.assertEquals("123 by override method in CarNonSealedClass 123 by VehicleSealedAbstractClass", result);

        result = getRegistrationNumberByOverrideMethodUsingVehicleSealedAbstractClass(new TruckFinalClass("123"));
        Assertions.assertEquals("123 by override method in TruckFinalClass 123 by VehicleSealedAbstractClass", result);

        result = getRegistrationNumberByOverrideMethodUsingVehicleSealedAbstractClass(new WagonSealedClass("123"));
        Assertions.assertEquals("123 by override method in WagonSealedClass 123 by VehicleSealedAbstractClass", result);

        result = getRegistrationNumberByOverrideMethodUsingVehicleSealedAbstractClass(new WagonBolivia("123"));
        Assertions.assertEquals("123 by override method in WagonSealedClass 123 by VehicleSealedAbstractClass", result);
    }

    private static String getRegistrationNumberByOverrideMethodUsingVehicleSealedAbstractClass(VehicleSealedAbstractClass vehicle) {
        String result;
        if (vehicle instanceof CarNonSealedClass car) {
            result = car.getRegistrationNumberByOverrideMethod();
        } else if (vehicle instanceof TruckFinalClass truck) {
            result = truck.getRegistrationNumberByOverrideMethod();
        } else if (vehicle instanceof WagonSealedClass wagon) {
            result = wagon.getRegistrationNumberByOverrideMethod();
        } else {
            throw new RuntimeException("Unknown instance of Vehicle");
        }
        return result + vehicle.getRegistrationNumberByMethodFromAbstractClass();
    }

    // Since records are implicitly final, the sealed hierarchy is even more concise
    @Test
    void patterMatchingWithSealedClassAndRecord() {
        var result = getRegistrationNumberByOverrideMethod(new CarRecord("123"));
        Assertions.assertEquals("123 by override method in CarRecord", result);

        result = getRegistrationNumberByOverrideMethod(new TruckRecord("123"));
        Assertions.assertEquals("123 by override method in TruckRecord", result);
    }

    private static String getRegistrationNumberByOverrideMethod(VehicleSealedInterface vehicle) {
        String result;
        if (vehicle instanceof CarRecord car) {
            result = car.getRegistrationNumberByOverrideMethod();
        } else if (vehicle instanceof TruckRecord truck) {
            result = truck.getRegistrationNumberByOverrideMethod();
        } else {
            throw new RuntimeException("Unknown instance of Vehicle");
        }

        return result;
    }

    // TODO difference sealed and final??
    @Test
    void patterMatchingWithNoSealedClasses() {
        PersonAbstractClass person = new EmployeeFinal();

        String result;
        if (person instanceof EmployeeFinal employee) {
            result = employee.getEmployeeName();
        } else if (person instanceof ManagerNormalClass manager) {
            result = manager.getManagerName();
        } else {
            throw new RuntimeException("Unknown instance of Vehicle");
        }
        Assertions.assertEquals("I am a Employee", result);
    }


    // compatibility of sealed classes with the reflection API
    @Test
    void isSealed() {
        TruckFinalClass truck = new TruckFinalClass("124");

        Class<?>[] permittedSubclasses = truck.getClass().getSuperclass().getPermittedSubclasses();
        Optional<Class<?>> first = Arrays
                .stream(permittedSubclasses)
                .filter(d -> truck.getClass().getCanonicalName().equals(d.getCanonicalName()))
                .findFirst();

        ClassDesc classDescTruck = ClassDesc.of(truck.getClass().getCanonicalName());
        ClassDesc classDescTruckFromPermitted = ClassDesc.of(first.get().getCanonicalName());

        Assertions.assertEquals(classDescTruckFromPermitted, classDescTruck);
    }

    // compatibility of sealed classes with the reflection API
    @Test
    void getPermittedSubclasses() {
        TruckFinalClass truck = new TruckFinalClass("124");


        Class<?>[] permittedSubclasses = truck.getClass().getSuperclass().getPermittedSubclasses();
        Optional<Class<?>> first = Arrays
                .stream(permittedSubclasses)
                .filter(d -> truck.getClass().getCanonicalName().equals(d.getCanonicalName()))
                .findFirst();

        ClassDesc classDescTruck = ClassDesc.of(truck.getClass().getCanonicalName());
        ClassDesc classDescTruckFromPermitted = ClassDesc.of(first.get().getCanonicalName());

        Assertions.assertEquals(classDescTruckFromPermitted, classDescTruck);
    }
}
