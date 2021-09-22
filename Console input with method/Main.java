package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите выражение");
        String[] expression = in.nextLine().split("\\*");
        Multiply(Integer.parseInt(expression[0]), Integer.parseInt(expression[1]));
    }

    public static void Multiply(int x1, int x2){
        double log1 = Math.log10(x1);
        double log2 = Math.log10(x2);
        System.out.println((int)Math.pow(10, log1+log2));
    }
}