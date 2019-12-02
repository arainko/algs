package RadixCountingSort;

import java.util.HashSet;
import java.util.Set;

public class Utilities {

    static String[] normalizeArray(String[] inputArr, int max) {
        String[] outputArr = new String[inputArr.length];
        for (int i = 0; i < inputArr.length; i++)
            outputArr[i] = fillSingularStringToMax(inputArr[i], max);
        return outputArr;
    }

    static String fillSingularStringToMax(String input, int max) {
        StringBuilder output = new StringBuilder(input);
        while (output.length() < max) {
            output.append(" ");
        }
        return output.toString();
    }

    static int findMaxLength(String[] arr) {
        int outputMaxLength = arr[0].length();
        for (String str : arr) {
            if (outputMaxLength < str.length())
                outputMaxLength = str.length();
        }
        return outputMaxLength;
    }


    static int getUpperLimit(String[] normalizedArr, int index) {
        int localMax = normalizedArr[0].charAt(index);
        for (String str : normalizedArr) {
            if (localMax < (int) str.charAt(index))
                localMax = str.charAt(index);
        }
        return localMax;
    }

    static int getLowerLimit(String[] normalizedArr, int index) {
        int localMin = normalizedArr[0].charAt(index);
        for (String str : normalizedArr) {
            if (localMin > (int) str.charAt(index))
                localMin = str.charAt(index);
        }
        return localMin;
    }


}
