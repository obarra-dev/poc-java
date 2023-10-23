package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class InterfaceTest {

    // TODO make it works!!
    @Disabled
    @Test
    void indent() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object proxy = Proxy.   newProxyInstance(ClassLoader.getSystemClassLoader(), new Class<?>[] { VehicleInterface.class },
                (prox, method, args) -> {
                    if (method.isDefault()) {
                        return InvocationHandler.invokeDefault(prox, method, args);
                    }
                    return prox;
                }
        );
        Method method = proxy.getClass().getMethod("hello");
        Assertions.assertEquals(method.invoke(proxy), "dss");
    }

}
