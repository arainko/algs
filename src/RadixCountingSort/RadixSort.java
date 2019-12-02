package RadixCountingSort;

public class RadixSort {
    static String[] countingSort(String[] inputArray, int index) {
        String[] normalizedInput = Utilities.normalizeArray(inputArray, Utilities.findMaxLength(inputArray));
        String[] outputArray = new String[inputArray.length];
        int upperLimit = Utilities.getUpperLimit(normalizedInput, index);
        int lowerLimit = Utilities.getLowerLimit(normalizedInput, index);
        int[] countsArray = new int[upperLimit-lowerLimit+1];

        // Tabela wystapien, gdzie kazda z liter to indeks (jej numer ASCII) w countsArray.
        for (int i=0; i < inputArray.length; i++) {
            int charIndex = normalizedInput[i].charAt(index)-lowerLimit;
            countsArray[charIndex]++;
        }

        for (int i=1; i < countsArray.length; i++)
            countsArray[i] += countsArray[i-1];

        for (int i=inputArray.length-1; i >= 0; i--) {
            int charIndex = normalizedInput[i].charAt(index)-lowerLimit;
            outputArray[countsArray[charIndex]-1] = normalizedInput[i];
            countsArray[charIndex]--;
        }

        for (String str : outputArray)
            System.out.println(str);

        return outputArray;
    }


}
