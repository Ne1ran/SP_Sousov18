package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileThread extends Thread {
    String filename;
    FileThread(String filename){
        this.filename = filename;
        this.start();
    }

    @Override
    public void run(){
        long timestart =  System.currentTimeMillis();
        try(BufferedReader breader = new BufferedReader(new FileReader("fileread1.txt"))) {
            try(BufferedWriter bwriter = new BufferedWriter(new FileWriter(this.filename + ".txt"))) {
                String line;
                while ((line = breader.readLine()) != null){
                    bwriter.write(line + "\n");
                }
            }
        } catch (Exception exception) {
            System.out.println("Ошибка");
        }
        long timeend = System.currentTimeMillis();
        long timePosled = timeend - timestart;
        System.out.println(timePosled + " мс - время потока " + this.filename);
    }
}
