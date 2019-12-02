package QuickSort;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- DANE LOSOWE ---");
        System.out.println("ILOSC \t MOD \t NORMAL");
        for (int i = 50000; i <= 200000; i += 50000) {
            int[] array = Utilities.generateRandomizedArray(i);
            long averageTime1 = 0;
            long averageTime2 = 0;
            for (int j = 0; j < 100; j++) {
                averageTime1 += Utilities.testTimeModified(array);
                averageTime2 += Utilities.testTimeNormal(array);
            }
            System.out.println(i + "\t "+ averageTime1 + "\t " + averageTime2 );
        }

        System.out.println("--- DANE NIEKORZYSTNE ---");
        System.out.println("ILOSC \t MOD \t NORMAL");
        for (int i = 50000; i <= 200000; i += 50000) {
            int[] array = Utilities.generateAscendingArray(i);
            long averageTime1 = 0;
            long averageTime2 = 0;
            for (int j = 0; j < 100; j++) {
                averageTime1 += Utilities.testTimeModified(array);
                averageTime2 += Utilities.testTimeNormal(array);
            }
            System.out.println(i + "\t "+ averageTime1 + "\t " + averageTime2 );
        }



    }

}
