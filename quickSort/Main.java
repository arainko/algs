import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = QuickSort.generateDescendingArray(10000);
//        int[] arr = {10,9,8,7,6,5,4,3,2,1};
//        int[] arr1 = QuickSort.generateDescendingArray(100000);
//        int[] arr2 = arr;

        int last = arr.length-1;

        long startTime = System.currentTimeMillis();
//            QuickSort.quickSort(arr, 0, last);
            QuickSort.betterQuickSort(arr, 0, last);
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        // -- // -- // -- // -- // -- //

//        startTime = System.currentTimeMillis();
//            QuickSort.betterQuickSort(arr1, 0, last);
//        endTime = System.currentTimeMillis();
//        System.out.println("better took " + (endTime - startTime) + " milliseconds");




//      System.out.println(Arrays.toString(arr));

    }

}
