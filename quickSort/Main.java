public class Main {
    public static void main(String[] args) {
        int[] array1 = Utilities.generateRandomizedArray(1000000);
        int[] array2 = array1.clone();

        Utilities.testTimeModified(array1);
        Utilities.testTimeNormal(array2);
        System.out.println(Utilities.isOrdered(array1));
        System.out.println(Utilities.isOrdered(array2));

    }

}
