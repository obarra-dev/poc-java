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

    @Test
    void nowCanInvokeDefaultMethodsUsingReflection() {
        GreetingInterface greetingProxy = (GreetingInterface) Proxy.newProxyInstance(
                InterfaceTest.class.getClassLoader(),
                new Class[]{GreetingInterface.class},
                (proxy, method, args) -> {
                    if (method.getName().equals("getName")) {
                        return "Omar";
                    } else if (method.isDefault()) {
                        return InvocationHandler.invokeDefault(proxy, method, args);
                    } else {
                        throw new IllegalStateException(
                                "Method not implemented: " + method);
                    }
                });

        Assertions.assertEquals("Omar", greetingProxy.getName());
        Assertions.assertEquals("Hello, Omar from GreetingInterface", greetingProxy.greet());
    }

}
