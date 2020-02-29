package com.arainko.quicksort;

import java.util.Random;

public class Utilities {
    static void swap(int[] array, int indA, int indB) {
        int temp = array[indB];
        array[indB] = array[indA];
        array[indA] = temp;
    }

    static void subSort(int[] array, int from, int to) {
        int lastSortedIndex = to;
        int localMaxInd = from;
        while (lastSortedIndex != from) {
            for (int i = from; i <= lastSortedIndex; i++) {
                if (array[localMaxInd] <= array[i])
                    localMaxInd = i;
            }
            swap(array, lastSortedIndex, localMaxInd);
            lastSortedIndex--;
            localMaxInd = from;
        }

    }

    static void bubbleSort(int[] array, int from, int to) {
        for (int i = from; i <= to; i++)
            for(int j = i + 1; j <= to; j++)
                if (array[i] > array[j])
                    swap(array, i, j);
    }

    public static int[] generateRandomizedArray(int length) {
        int[] seq = new int[length];
        Random generator = new Random();

        for (int i = 0; i < length; i++)
            seq[i] = generator.nextInt(10000) - 5000;

        return seq;
    }

    public static int[] generateAscendingArray(int length) {
        int[] seq = new int[length];
        int j = 0;

        for (int i = 0; i < length; i++) {
            seq[i] = j % 5000;
            j++;
        }

        return seq;
    }

    static long testTimeNormal(int[] array) {
        int[] arr = array.clone();
        int last = arr.length-1;
        long startTime = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, last);
        long endTime = System.currentTimeMillis();
//        System.out.println("Normal took " + (endTime - startTime) + " milliseconds");
        return endTime - startTime;
    }

    static long testTimeModified(int[] array) {
        int[] arr = array.clone();
        int last = arr.length-1;
        long startTime = System.currentTimeMillis();
            QuickSort.modifiedQuickSort(arr, 0, last);
        long endTime = System.currentTimeMillis();
//        System.out.println("Modified took " + (endTime - startTime) + " milliseconds");
        return endTime - startTime;
    }

    static boolean isOrdered(int[] seq) {
        int length = seq.length;

        for (int i = 1; i < length; i++)
            if (seq[i - 1] > seq[i]) {
                return false;
            }

        return true;
    }
}
