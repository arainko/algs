package RadixCountingSort;

public class RadixSortMain {
    public static void main(String[] args) {
        String[] words = {"jedynka", "dwojka", "trojka", "piatka", "dziesiatka", "dwudziestka"};

        for (String str : RadixSort.radixSort(words))
            System.out.println(str);
    }
}
