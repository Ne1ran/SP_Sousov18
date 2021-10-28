package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        long timestart =  System.currentTimeMillis();
        Path fileread1 = Paths.get("fileread1.txt");
        Path fileread2 = Paths.get("fileread2.txt");
        Files.copy(fileread1, Paths.get("filewriteposled1.txt"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(fileread2, Paths.get("filewriteposled2.txt"), StandardCopyOption.REPLACE_EXISTING);
        long timeend = System.currentTimeMillis();
        long timePosled = timeend - timestart;
        System.out.println(timePosled + " мс при последовательном считывании");
        FileThread filereaderwriter1 = new FileThread("filewriteparallel1.txt", fileread1);
        FileThread filereaderwriter2 = new FileThread("filewriteparallel2.txt", fileread2);

        filereaderwriter1.join();
        filereaderwriter2.join();
    }
}