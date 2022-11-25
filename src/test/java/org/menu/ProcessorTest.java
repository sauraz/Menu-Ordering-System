package org.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ProcessorTest {

    Processor processor;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    InputStream sysInBackup;

    @BeforeEach
    void init() {
        processor = new Processor();
        sysInBackup = System.in;
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(sysInBackup);
    }

    @ParameterizedTest(name = "#{index} - Test with Int : {0}")
    @MethodSource("testCasesProvider")
    void processInputTest(String input, String expected) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try {
            processor.processInput();
        } catch (Exception e) {
            System.out.print("Unable to process: " + e.getMessage());
        }
        assertEquals(expected, outContent.toString());
    }

    static Stream<Arguments> testCasesProvider() {
        return Stream.of(
                arguments("Breakfast 1,2,3", "Eggs, Toast, Coffee"),
                arguments("Breakfast 2,3,1", "Eggs, Toast, Coffee"),
                arguments("Breakfast 1,2,3,3,3", "Eggs, Toast, Coffee(3)"),
                arguments("Breakfast 1", "Unable to process: Side is missing"),
                arguments("Lunch 1,2,3", "Sandwich, Chips, Soda"),
                arguments("Lunch 1,2", "Sandwich, Chips, Water"),
                arguments("Lunch 1,1,2, 3", "Unable to process: Sandwich cannot be ordered more than once"),
                arguments("Lunch 1,2,2", "Sandwich, Chips(2), Water"),
                arguments("Lunch", "Unable to process: Main is missing, Side is missing"),
                arguments("Dinner 1,2,3,4", "Steak, Potatoes, Wine, Water, Cake"),
                arguments("Dinner 1,2,3", "Unable to process: Dessert is missing")
        );
    }
}