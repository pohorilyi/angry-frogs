package com.pohorilyi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AngryFrogsTest {

    AngryFrogs sut = new AngryFrogs();

    @ParameterizedTest
    @MethodSource("paramsProvider")
    void bruteForceTest(int[] input, int expected) {
        int actual = sut.bruteForceSolution(input);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("paramsProvider")
    void stepsTest(int[] input, int expected) {
        int actual = sut.stepsSolution(input);
        assertEquals(expected, actual);
    }

    @Test
    void loadTest() {
        int sequenceSize = 100000;
        int[] ascendingSequence = IntStream.rangeClosed(1, sequenceSize).toArray();

        long startTime = System.nanoTime();
        int bruteForceResult = sut.bruteForceSolution(ascendingSequence);
        long stopTime = System.nanoTime();
        long bruteForceExecutionTime = stopTime - startTime;
        System.out.println("Brute force completed in: " + bruteForceExecutionTime + " ns");
        assertEquals(sequenceSize, bruteForceResult);

        startTime = System.nanoTime();
        int stepsResult = sut.stepsSolution(ascendingSequence);
        stopTime = System.nanoTime();
        long stepsExecutionTime = stopTime - startTime;
        System.out.println("Steps completed in: " + stepsExecutionTime + " ns");
        assertEquals(sequenceSize, stepsResult);
        System.out.println("For ascending sequence with size = " + sequenceSize +
                " one pass solution is " + bruteForceExecutionTime / stepsExecutionTime + " times faster");
    }

    private static Stream<Arguments> paramsProvider() {
        return Stream.of(
                arguments(new int[]{}, 0),
                arguments(new int[]{1}, 1),
                arguments(new int[]{1, 1}, 2),
                arguments(new int[]{1, 2}, 2),
                arguments(new int[]{2, 1}, 2),
                arguments(new int[]{1, 2, 3}, 3),
                arguments(new int[]{1, 1, 2}, 3),
                arguments(new int[]{3, 2, 1}, 3),
                arguments(new int[]{2, 1, 2}, 3),
                arguments(new int[]{1, 2, 1, 2}, 3),
                arguments(new int[]{1, 3, 3, 1}, 3),
                arguments(new int[]{1, 2, 1, 2, 1}, 3),
                arguments(new int[]{2, 1, 2, 1, 2, 1, 2}, 3),
                arguments(new int[]{1, 1, 2, 3}, 4),
                arguments(new int[]{3, 3, 2, 1}, 4),
                arguments(new int[]{2, 1, 2, 1, 1, 2, 1, 2}, 4),
                arguments(new int[]{3, 3, 2, 2, 1}, 5),
                arguments(new int[]{1, 2, 3, 2, 1, 2, 3}, 5),
                arguments(new int[]{1, 2, 3, 2, 1, 2, 3, 2, 1}, 5),
                arguments(new int[]{3, 3, 2, 2, 1, 2}, 6),
                arguments(new int[]{1, 2, 3, 2, 1, 1, 2, 3, 2, 1}, 6),
                arguments(new int[]{1, 2, 3, 2, 1, 2, 3, 3, 2, 1}, 6),
                arguments(new int[]{1, 2, 3, 3, 2, 1, 2, 3, 2, 1}, 6)
        );
    }
}