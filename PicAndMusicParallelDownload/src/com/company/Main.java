package com.company;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//Main-класс
@MainCLASS
public class Main {

    public static void main(String[] args) {
        String[] links = new String[2];
        try(BufferedReader reader = new BufferedReader(new FileReader("startfile.txt"))){
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null){
                links[i] = line;
                i++;
            }
        } catch (Exception e){
            System.out.println("Ошибка считывания");
        }
        /*
        Два экземпляра класс image и music.
        Один скачивает изображение, другой - музыку.
         */
	DownloadingThread image = new DownloadingThread("Korella", ".jpg", links[0]);
	DownloadingThread music = new DownloadingThread("Tsoi", ".mp3", links[1]);
        Scanner in = new Scanner(System.in);
        System.out.println("Хотите ли вы воспроизвести музыку? Напишите Да - если хотите.");
        String choice = in.nextLine();
        if (choice.equals("Да")) {
            // Создание плеера из входного потока на воспроизведение музыки.
            try (FileInputStream inputStream = new FileInputStream("Tsoi.mp3")) {
                try {
                    Player player = new Player(inputStream);
                    player.play();
                } catch (JavaLayerException e) {
                    System.out.println("Ошибка создания и воспроизведения плеера");
                }
            } catch (IOException e) {
                System.out.println("Ошибка создания FIS плеера");
            }
        }
    }
}
