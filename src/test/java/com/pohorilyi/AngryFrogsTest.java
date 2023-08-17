package com.pohorilyi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
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
        int similarStepsSize = 100;
        int[] input = generateLoadTestInput(similarStepsSize);

        long startTime = System.nanoTime();
        int bruteForceResult = sut.bruteForceSolution(input);
        long stopTime = System.nanoTime();
        long bruteForceExecutionTime = stopTime - startTime;
        System.out.println("Brute force completed in: " + bruteForceExecutionTime + " ns");
        assertEquals(similarStepsSize + 1, bruteForceResult);

        startTime = System.nanoTime();
        int stepsResult = sut.stepsSolution(input);
        stopTime = System.nanoTime();
        long stepsExecutionTime = stopTime - startTime;
        System.out.println("Steps completed in: " + stepsExecutionTime + " ns");
        assertEquals(similarStepsSize + 1, stepsResult);


        System.out.println("Steps is " + (float) bruteForceExecutionTime / stepsExecutionTime + " times faster");
    }

    private static int[] generateLoadTestInput(int similarStepsSize) {
        IntStream ones = IntStream.generate(() -> 1).limit(similarStepsSize);
        IntStream spike = IntStream.of(Integer.MAX_VALUE);
        IntStream randomInts = IntStream.generate(() -> new Random().nextInt(5)).limit(10000000);
        return IntStream.concat(IntStream.concat(ones, spike),randomInts).toArray();
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