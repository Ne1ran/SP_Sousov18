package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	long timestart =  System.currentTimeMillis();
        try(BufferedReader breader = new BufferedReader(new FileReader("fileread1.txt"))) {
            try(BufferedWriter bwriter = new BufferedWriter(new FileWriter("filewriteposled1.txt"))) {
                String line;
            while ((line = breader.readLine()) != null){
                    bwriter.write(line + "\n");
                }
            }
        } catch (Exception exception) {
            System.out.println("Ошибка");
        }
        try(BufferedReader breader = new BufferedReader(new FileReader("fileread2.txt"))) {
            try(BufferedWriter bwriter = new BufferedWriter(new FileWriter("filewriteposled2.txt"))) {
                String line;
                while ((line = breader.readLine()) != null){
                    bwriter.write(line + "\n");
                }
            }
        } catch (IOException exception) {
            System.out.println("Ошибка");
        }
    long timeend = System.currentTimeMillis();
    long timePosled = timeend - timestart;
        System.out.println(timePosled + " мс при последовательном считывании");

        FileThread filereaderwriter1 = new FileThread("filewriteparallel1");
        FileThread filereaderwriter2 = new FileThread("filewriteparallel2");

        filereaderwriter1.join();
        filereaderwriter2.join();
    }
}
