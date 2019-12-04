package RadixCountingSort;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    static String[] getArrayFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<String> content = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (!line.equals(""))
                    content.add(line);
            }

            String[] output = new String[content.size()];

            for (int i = 0; i < content.size(); i++) {
                output[i] = content.get(i);
            }
            return output;
        }
    }

    static void writeArrayToFile(String[] array, String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (String line : array) {
            printWriter.print(line + "\n");
        }
        printWriter.close();
    }

    static String[] getSurnamesOnly(String[] surnames) {
        String[] output = new String[surnames.length];
        for (int i = 0; i < surnames.length; i++)
            output[i] = surnames[i].split(" ")[1];
        return output;
    }

    static String[] concatenateSurnamesAndNumbers(String[] originalArray, String[] sortedSurnames) {
        String[] trimmedSurnames = new String[sortedSurnames.length];
        String[] outputArray = new String[originalArray.length];

        for (int i=0; i < sortedSurnames.length; i++) {
            trimmedSurnames[i] = sortedSurnames[i].trim();
        }

        for (int i=0; i < trimmedSurnames.length; i++) {
            String currSortedSurname = trimmedSurnames[i];
            for (int j=0; j < trimmedSurnames.length; j++) {
                String currOriginalNumberAndSurname = originalArray[j];
                String currOriginalSurname = originalArray[j].split(" ")[1];
                if (currSortedSurname.equals(currOriginalSurname)) {
                    outputArray[i] = currOriginalNumberAndSurname;
                }
            }
        }
        return outputArray;
    }


}
