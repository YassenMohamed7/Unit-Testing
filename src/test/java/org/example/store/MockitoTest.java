package org.example.store;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

public class MockitoTest {


    @Test void test1() {
        List list = Mockito.mock(List.class);

        list.add("red");        // add "red"
        list.add("green");      // add "green"
        list.add("blue");       // add "blue"
        list.clear();           // clear

        Mockito.when(list.get(Mockito.anyInt())).thenReturn("Yellow");

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        Mockito.verify(list).add("red");
        Mockito.verify(list).clear();
        Mockito.verify(list, Mockito.times(3)).add(Mockito.anyString());
    }

}
