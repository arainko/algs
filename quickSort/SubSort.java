public class SubSort {
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
}
