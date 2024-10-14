package org.example.store;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CalculatorTest {

    @Test
    void test1() {

        Calculator calculator = Mockito.mock(Calculator.class);

        Mockito.when(calculator.add(3, 4)).thenReturn(7);

        int result = calculator.add(3, 4);

        System.out.println(result);


    }

}
