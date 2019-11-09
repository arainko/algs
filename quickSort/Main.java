import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {-1,20,30,123,2,0,3,4,1,1,1,-20};
        int last = arr.length-1;
       InsertionSort.insertionSort(arr);
       System.out.println(Arrays.toString(arr));

    }

}
