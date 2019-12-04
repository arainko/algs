package RadixCountingSort;

import java.io.IOException;

public class RadixSortMain {
    public static void main(String[] args) throws IOException {
        String[] surnames = Utilities
                .getArrayFromFile("/home/aleksander/IdeaProjects/algs/src/RadixCountingSort/surnames");
        String[] surnamesOnly = Utilities.getSurnamesOnly(surnames);

        long start = System.currentTimeMillis();
        String[] sortedSurnamesDirty = RadixSort.radixSort(surnamesOnly);
        long end = System.currentTimeMillis();
        System.out.println(end-start);

//        for (String str : RadixSort.radixSort(Utilities.getSurnamesOnly(surnames)))
//            System.out.println(str);
    }
}
