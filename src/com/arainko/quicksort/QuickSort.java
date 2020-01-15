package com.arainko.quicksort;

public class QuickSort {

    static void swap(int[] array, int indA, int indB) {
        int temp = array[indB];
        array[indB] = array[indA];
        array[indA] = temp;
    }

     static int partition(int[] array, int border, int pivotIndex) {
        int pivot = array[pivotIndex];
        int borderWall = border - 1;

        for (int i = border; i <= pivotIndex; i++)
            if (pivot >= array[i]) {
                borderWall++;
                swap(array, borderWall, i);
            }

        if (borderWall < pivotIndex) return borderWall;
        else return borderWall - 1;
    }

    static void quickSort(int[] array, int border, int pivotIndex) {
        if (border < pivotIndex) {
            int partIndex = partition(array, border, pivotIndex);
            quickSort(array, border, partIndex);
            quickSort(array, partIndex +1, pivotIndex);
        }
    }

    static void modifiedQuickSort(int[] array, int border, int pivotIndex) {
        int value = 10;

        if (border < pivotIndex) {
            if (pivotIndex - border  + 1 < value) {
                Utilities.bubbleSort(array, border, pivotIndex);
                //            Utilities.subSort(array, border, pivotIndex);
            } else {
                int partIndex = partition(array, border, pivotIndex);
                modifiedQuickSort(array, border, partIndex);
                modifiedQuickSort(array, partIndex +1, pivotIndex);
            }
        }

    }

}
