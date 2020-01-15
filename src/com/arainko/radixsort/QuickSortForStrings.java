package com.arainko.radixsort;

public class QuickSortForStrings {

    static void swap(String[] array, int indA, int indB) {
        String temp = array[indB];
        array[indB] = array[indA];
        array[indA] = temp;
    }

     static int partition(String[] array, int border, int pivotIndex) {
        String pivot = array[pivotIndex];
        int borderWall = border - 1;

        for (int i = border; i <= pivotIndex; i++)
            if (pivot.compareToIgnoreCase(array[i]) >= 0) {
                borderWall++;
                swap(array, borderWall, i);
            }

        if (borderWall < pivotIndex) return borderWall;
        else return borderWall - 1;
    }

    static void quickSort(String[] array, int border, int pivotIndex) {
        if (border < pivotIndex) {
            int partIndex = partition(array, border, pivotIndex);
            quickSort(array, border, partIndex);
            quickSort(array, partIndex +1, pivotIndex);
        }
    }

}
