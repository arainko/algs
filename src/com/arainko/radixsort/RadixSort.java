package com.arainko.radixsort;

public class RadixSort {
    static String[] countingSort(String[] inputArray, int index) {
        String[] outputArray = new String[inputArray.length];
        int upperLimit = Utilities.getUpperLimit(inputArray, index);
        int lowerLimit = Utilities.getLowerLimit(inputArray, index);
        int[] countsArray = new int[upperLimit-lowerLimit+1];

        // Tabela wystapien, gdzie kazda z liter to indeks (jej numer ASCII) w countsArray.
        for (int i=0; i < inputArray.length; i++) {
            int charIndex = inputArray[i].charAt(index)-lowerLimit;
            countsArray[charIndex]++;
        }

        for (int i=1; i < countsArray.length; i++)
            countsArray[i] += countsArray[i-1];

        for (int i=inputArray.length-1; i >= 0; i--) {
            int charIndex = inputArray[i].charAt(index)-lowerLimit;
            outputArray[countsArray[charIndex]-1] = inputArray[i];
            countsArray[charIndex]--;
        }

        return outputArray;
    }

    static String[] radixSort(String[] inputArray) {
        String[] outputArr = Utilities.normalizeArray(inputArray, Utilities.findMaxLength(inputArray));
        int maxLength = Utilities.findMaxLength(inputArray)-1;
        for (int i=maxLength; i >= 0; i--) {
            outputArr = countingSort(outputArr, i);
        }
        return outputArr;
    }


}
