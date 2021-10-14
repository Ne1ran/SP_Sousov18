package com.company;

import java.util.Scanner;

public class ChickenAndEgg { // Класс ChickenAndEgg, являющийся основным.
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in); // Создание экземпляра класса Scanner для ввода значений с консоли
        System.out.println("Введите количество раундов спора: ");
        int number;
        while (true){ // Бесконечный цикл, получающий на ввод строку. Если в ней численное значение, то завершается
            try {
                number = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите другое значение");
            }
        }
        // Создание двух экземпляров класса ChangedThread
        ChangedThread chicken = new ChangedThread("Chicken", number);
        ChangedThread egg = new ChangedThread("Egg", number);
        chicken.join(); // метод join() заставляет поток ждать после вывода сообщения о победе
        egg.join();
        while (true) { // бесконечный цикл, который проверяет, не завершился ли один из потоков, если да, то выводит сообщение о победители
            if (!egg.isAlive()){
                System.out.println("Курица побеждает");
                chicken.stop();
                break;
            } else if (!chicken.isAlive()){
                System.out.println("Яйцо побеждает");
                egg.stop();
                break;
            }
        }
    }

}