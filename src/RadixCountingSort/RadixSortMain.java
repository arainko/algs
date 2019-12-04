package RadixCountingSort;

import java.io.IOException;

public class RadixSortMain {
    public static void main(String[] args) throws IOException {
        String[] surnames = Utilities
                .getArrayFromFile("/home/aleksander/IdeaProjects/algs/src/RadixCountingSort/surnames");
        String[] surnamesOnly = Utilities.getSurnamesOnly(surnames);

        long startRadix = System.currentTimeMillis();
        String[] sortedSurnames = RadixSort.radixSort(surnamesOnly);
        long endRadix = System.currentTimeMillis();

        long startQuick = System.currentTimeMillis();
        QuickSortForStrings.quickSort(surnamesOnly, 0, surnames.length-1);
        long endQuick = System.currentTimeMillis();

        System.out.println("Radix took " + (endRadix-startRadix) + "ms.");
        System.out.println("Quick took " + (endQuick-startQuick) + "ms.");

        String[] sortedWhole = Utilities.concatenateSurnamesAndNumbers(surnames, sortedSurnames);
        Utilities.writeArrayToFile(sortedWhole, "/home/aleksander/IdeaProjects/algs/src/RadixCountingSort/sortedBySurname");

//        for (String str : surnamesOnly)
//            System.out.println(str);
    }
}
