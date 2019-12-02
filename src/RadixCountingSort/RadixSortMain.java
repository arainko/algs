package RadixCountingSort;

public class RadixSortMain {
    public static void main(String[] args) {
        String[] words = {"ene", "due", "like", "fake"};
        String[] normalizedWord = Utilities.normalizeArray(words, Utilities.findMaxLength(words));
        int diffs = Utilities.getNumberOfDifferentChars(normalizedWord, 2);
        int upper = Utilities.getUpperLimit(normalizedWord, 3);
        int lower = Utilities.getLowerLimit(normalizedWord, 0);
//        System.out.println(lower);

        RadixSort.countingSort(words, 1);
    }
}
