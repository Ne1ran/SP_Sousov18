package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	try(BufferedReader reader = new BufferedReader(new FileReader("expression.txt"))){
	    String[] strnums = reader.readLine().split("\\*");
	    Multiply(Integer.parseInt(strnums[0]), Integer.parseInt(strnums[1]));
    } catch (IOException exception) {
        System.out.println("Ошибка");
    }
    }

    public static void Multiply(int x1, int x2){
        double log1 = Math.log10(x1);
        double log2 = Math.log10(x2);
        System.out.println((int)Math.pow(10, log1+log2));
    }
}
