package HeapSort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Utility {
    static int[] getArrayFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<Integer> content = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (!line.equals(""))
                    content.add(Integer.valueOf(line));
            }

            int[] output = new int[content.size()];

            for (int i = 0; i < content.size(); i++) {
                output[i] = content.get(i);
            }
            return output;
        }
    }

    static void writeArrayToFile(int[] array, String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int number : array) {
            printWriter.print(number + "\n");
        }
        printWriter.close();
    }

    static boolean isOrdered(int[] seq) {
        int length = seq.length;

        for (int i = 1; i < length; i++)
            if (seq[i - 1] > seq[i]) {
                return false;
            }

        return true;
    }

    static int[] generateRandomizedArray(int length) {
        int[] seq = new int[length];
        Random generator = new Random();

        for (int i = 0; i < length; i++)
            seq[i] = generator.nextInt(10000) - 5000;

        return seq;
    }
}

class HeapSort {

    private static void swap(int[] seq, int idxA, int idxB) {
        int temp = seq[idxA];
        seq[idxA] = seq[idxB];
        seq[idxB] = temp;
    }

    static void iterativeHeapify(int[] seq, int heapSize, int i) {
        int largest = i;
        int temp = i;
        do {
            int l = 2 * temp + 1;
            int r = 2 * temp + 2;
            if (l < heapSize && seq[l] > seq[temp] || r < heapSize && seq[r] > seq[largest]) {
                largest = l;
            } else {
                largest = i;
                temp = i;
            }
            if (r < heapSize && seq[r] > seq[largest]) {
                largest = r;
            }
            if (largest != temp) {
                swap(seq, temp, largest);
                temp = largest;
            }
        } while (largest != i);
    }

    static void recursiveHeapify(int[] seq, int heapSize, int i) {
        int largest;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < heapSize && seq[l] > seq[i])
            largest = l;
        else
            largest = i;

        if (r < heapSize && seq[r] > seq[largest])
            largest = r;

        if (largest != i) {
            swap(seq, i, largest);
            recursiveHeapify(seq, heapSize, largest);
        }
    }

    static void buildHeap(int[] seq) {
        int length = seq.length;

        for (int i = length / 2 - 1; i >= 0; i--) {
            iterativeHeapify(seq, length, i);
        }
    }

    public static void heapSort(int[] seq) {
        buildHeap(seq);

        for (int i = seq.length - 1; i >= 0; i--) {
            swap(seq, 0, i);
            iterativeHeapify(seq, i, 0);
        }
    }

}


public class heapSort {
    public static void main(String[] args) throws IOException {
        int[] a = Utility.getArrayFromFile("input");
        HeapSort.heapSort(a);
        Utility.writeArrayToFile(a, "output");
    }
}
