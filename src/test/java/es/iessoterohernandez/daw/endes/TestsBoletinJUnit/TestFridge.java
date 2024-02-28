package es.iessoterohernandez.daw.endes.TestsBoletinJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;


public class TestFridge {
	static Stream<Arguments> food() {
        return Stream.of(
                Arguments.of("Huevo"),
                Arguments.of("Plátano"),
                Arguments.of("Zumo"),
                Arguments.of("Chorizo"),
                Arguments.of("Leche")
        );
    }

    @ParameterizedTest
    @MethodSource("food")
    public void testMetodosPutYContains(String food) {
        Fridge fridge = new Fridge();
        assertTrue(fridge.put(food));
        assertTrue(fridge.contains(food));
    }

    @ParameterizedTest
    @MethodSource("food")
    public void testMetodoTake(String food) {
        Fridge fridge = new Fridge();
        
        assertTrue(fridge.put(food));
        assertTrue(fridge.contains(food));

        try {
            fridge.take(food);
            assertFalse(fridge.contains(food));
        } catch (NoSuchItemException e) {
            fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @MethodSource("food")
    public void testNoSuchItemException(String food) {
        Fridge fridge = new Fridge();
        assertFalse(fridge.contains(food));

        try {
            fridge.take(food);
            fail("Se lanza la excepción");
        } catch (NoSuchItemException e) {
            assertEquals(food + " no está en la nevera", e.getMessage());
        }
    }
	
	
}
