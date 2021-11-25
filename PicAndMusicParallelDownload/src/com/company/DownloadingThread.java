package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

//Класс потоков скачивания
@WorkingCLASS
public class DownloadingThread extends Thread{
    String name;
    String extension;
    String link;
    DownloadingThread(String name, String extension, String link){
        this.name = name;
        this.extension = extension;
        this.link = link;
        this.start();
    }

    //Переопределенный метод run для скачивания
    @Override
    public void run(){
        try{
        URL site = new URL(this.link);
        ReadableByteChannel rbc = Channels.newChannel(site.openStream());
        FileOutputStream fos = new FileOutputStream(this.name + this.extension);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        catch (IOException e){
            System.out.println("Ошибка");
        }
    }
}
