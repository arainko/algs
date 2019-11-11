import java.util.Random;

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

    static void betterQuickSort(int[] array, int border, int pivotIndex) {
        int value = 4;

        if (pivotIndex - border  + 1 < value) {
            SubSort.subSort(array, border, pivotIndex);
        } else if (border < pivotIndex) {
            int partIndex = partition(array, border, pivotIndex);
            betterQuickSort(array, border, partIndex);
            betterQuickSort(array, partIndex +1, pivotIndex);
        }
    }

    static int[] generateRandomizedArray(int length) {
        int[] seq = new int[length];
        Random generator = new Random();

        for (int i = 0; i < length; i++)
            seq[i] = generator.nextInt(10000) - 5000;

        return seq;
    }

    static int[] generateDescendingArray(int length) {
        int[] seq = new int[length];
        int j = length;

        for (int i = 0; i < length; i++) {
            seq[i] = j;
            j--;
        }

        return seq;
    }
}
