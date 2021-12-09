package sousov;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args){
        String classes = ""; // Создание массив для параллельной записи названий классов и их сокращений
        String classeskeys = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\UserMyromeц\\IdeaProjects\\PicAndMusicParallelDownload\\src\\com\\company\\DownloadingThread.java"))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\sousov\\ThreadTemp.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String templine = line;
                    if (line.contains("class")) { // Проверка на содержание строки class в строке
                        int index = 1;
                        String[] tempArr = templine.split(" ");
                        for (int i = 0; i < tempArr.length; i++) { // Поиск номера необходимого сообщения
                            if (tempArr[i].equals("class")) {
                                index = i + 1;
                                break;
                            }
                        }
                        String classname = tempArr[index];
                        String classkey;
                        if (classname.length() > 3) { // Не сокращать если имя меньше 3 символов.
                            classkey = classname.charAt(0) + classname.substring(classname.length() - 1); // Если больше - берется первый и последний символ для сокращения
                            classes += classname + " ";
                            classeskeys += classkey + " ";
                        } else
                        {
                            classes += classname + " ";
                            classeskeys += classname + " ";
                        }

                    }
                    for (int i = 0; i < classes.split(" ").length; i++) { // Проход по элементам строки, если есть совпадения с названиями класса - переименование.
                        templine = templine.replaceAll(classes.split(" ")[i], classeskeys.split(" ")[i]);
                    }
                    writer.write(templine + "\n");
                }
            } catch (Exception e) {
                System.out.println("Ошибка writer");
            }
        } catch (Exception e) {
            System.out.println("Ошибка reader");
        }
        // Тоже самое для другого файла, однако в нем идет только замена из массива. Никакие новые классы не записываются.
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\UserMyromeц\\IdeaProjects\\PicAndMusicParallelDownload\\src\\com\\company\\Main.java"))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\sousov\\MainTemp.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String templine = line;
                    for (int i = 0; i < classes.split(" ").length; i++) {
                        templine = templine.replaceAll(classes.split(" ")[i], classeskeys.split(" ")[i]);
                    }
                    writer.write(templine + "\n");
                }
            } catch (Exception e) {
                System.out.println("Ошибка writer");
            }
        } catch (Exception e) {
            System.out.println("Ошибка reader");
        }
        // Два потока для классов Main и DownloadingThread.
	    ObsDeleter Main = new ObsDeleter("C:\\Users\\UserMyromeц\\IdeaProjects\\PicAndMusicParallelDownload\\src\\com\\company\\Main.java", "src\\sousov\\MainTemp.txt", "src\\sousov\\MainFinale.txt");
	    ObsDeleter DownloadingThread = new ObsDeleter("C:\\Users\\UserMyromeц\\IdeaProjects\\PicAndMusicParallelDownload\\src\\com\\company\\DownloadingThread.java", "src\\sousov\\ThreadTemp.txt", "src\\sousov\\Dd.txt");

    }
}
