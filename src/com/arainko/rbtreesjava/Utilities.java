package com.arainko.rbtreesjava;

public class Utilities {
    public static int[] generateAscendingArray(int length) {
        int[] seq = new int[length];
        int j = 0;

        for (int i = 0; i < length; i++) {
            seq[i] = j % 5000;
            j++;
        }

        return seq;
    }
}
