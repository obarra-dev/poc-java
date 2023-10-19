package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotNull;
import java.util.stream.Stream;

//Is does not seem useful
//to keep things uniform
class JEP323LocalVariableSyntaxForLambdaParametersTest {

    @Test
    void varInLambdaParameter() {
        // this is new but what is the benefits?
        MyFunctionalOperation myFunctionalOperation = (var a, var b) -> a + b;
        var result = myFunctionalOperation.doSomething(7, 5);
        Assertions.assertEquals(12, result);

        MyFunctionalOperation myFunctionalOperation2 = (a, b) -> a + b;
        result = myFunctionalOperation2.doSomething(7, 5);
        Assertions.assertEquals(12, result);

        MyFunctionalOperation myFunctionalOperation3 = (Integer a, Integer b) -> a + b;
        result = myFunctionalOperation3.doSomething(7, 5);
        Assertions.assertEquals(12, result);
    }

    @Test
    void varInLambdaParametersWhenUseNotNull() {
        var der = Stream.of(1, 2, 3, 4, 5).filter((@NotNull var x) -> x < 3).count();
        Assertions.assertEquals(2, der);
    }
}
