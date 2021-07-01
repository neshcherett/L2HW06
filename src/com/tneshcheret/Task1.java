package com.tneshcheret;

import java.util.ArrayList;
import java.util.Arrays;

public class Task1 {
    private static final int MAX_COUNT_CYCLE_RUN = 10000;

    public static void main(String[] args) {
        int[] array = new int[15];
        fillArray(array, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
        System.out.println(Arrays.toString(array));
    }

    public static int[] fillArray(int[] array, int... numbers) {
        ArrayList<Integer> usedIndexArray = new ArrayList<>();
        for (int number : numbers) {
            int index = number % array.length;
            if (usedIndexArray.contains(index)) {
                index = checkFreeCell(array, index, usedIndexArray);
                if (index >= MAX_COUNT_CYCLE_RUN) {
                    System.out.println("Данный ряд чисел не возможно положить в массив");
                    break;
                }
            }
            array[index] = index;
            usedIndexArray.add(index);
        }
        return array;
    }

    public static int checkFreeCell(int[] array, int index, ArrayList<Integer> usedIndexArray) {
        int counterCycleRun = 0;

        while (counterCycleRun < MAX_COUNT_CYCLE_RUN && usedIndexArray.contains(index)) {
            index = index + 3;
            if (index >= array.length) {
                index = index % array.length;
            }
            counterCycleRun++;
        }
        if (counterCycleRun >= MAX_COUNT_CYCLE_RUN) {
            index = counterCycleRun;
        }
        return index;
    }
}
