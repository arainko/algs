public class InsertionSort {
    static void swap(int[] array, int indA, int indB) {
        int temp = array[indB];
        array[indB] = array[indA];
        array[indA] = temp;
    }

    static void insertionSort(int[] array) {
        int lastSortedIndex = array.length-1;
        int localMaxInd = 0;
        while (lastSortedIndex != 0) {
            for (int i = 0; i <= lastSortedIndex; i++) {
                if (array[localMaxInd] <= array[i])
                    localMaxInd = i;
            }
            swap(array, lastSortedIndex, localMaxInd);
            lastSortedIndex--;
            localMaxInd = 0;
        }

    }
}
