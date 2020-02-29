package com.arainko.rbtreesjava;

public enum Color {
    RED, BLACK;


    @Override
    public String toString() {
        String output;

        if (this == Color.RED) output = "R";
        else output = "B";

        return output;
    }
}
