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
// JEP360,
class JEP360SealedClassesTest {

    @Test
    void patterMatchingWithSealedClasses() {
        var result = getImplementedMethod(new CarNonSealedClass("123"));
        Assertions.assertEquals("CarNonSealedClass", result);

        result = getImplementedMethod(new TruckFinalClass("123"));
        Assertions.assertEquals("TruckFinalClass", result);

        result = getImplementedMethod(new WagonSealedClass("123"));
        Assertions.assertEquals("WagonSealedClass", result);

        result = getImplementedMethod(new WagonBolivia("123"));
        Assertions.assertEquals("WagonSealedClass", result);
    }

    private static String getImplementedMethod(VehicleSealedAbstractClass vehicle) {
        String result;
        if (vehicle instanceof CarNonSealedClass car) {
            result = car.getImplementedMethod();
        } else if (vehicle instanceof TruckFinalClass truck) {
            result = truck.getImplementedMethod();
        } else if (vehicle instanceof WagonSealedClass wagon) {
            result = wagon.getImplementedMethod();
        } else {
            throw new RuntimeException("Unknown instance of Vehicle");
        }
        return result;
    }

    @Test
    void patterMatchingWithNoSealedClasses() {
        PersonAbstractClass person = new Employee();

        String result;
        if (person instanceof Employee employee) {
            result = employee.getEmployeeName();
        } else if (person instanceof Manager manager) {
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
