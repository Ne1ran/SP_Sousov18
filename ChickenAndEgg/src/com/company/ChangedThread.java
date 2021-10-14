package com.company;

public class ChangedThread extends Thread { // Класс ChangedThread с атрибутами имени и количества раундов
    String name;
    int rounds;
    ChangedThread(String name, int  rounds){ // Инициализатор с запуском потока
        this.name = name;
        this.rounds = rounds;
        this.start();
    }

    @Override
    public void run(){ // Переопределенный метод run() для запуска потоков
        try{
            while (true){ // Бесконечный цикл, симулирующий спор. Завершается по истечению раундов
                Thread.sleep(100);
                rounds--;
                if (name.equals("Chicken")){
                    System.out.println("Курица");
                } else System.out.println("Яйцо");
                if (rounds <= 0){
                    this.stop(); // Остановка процесса, у которого кончились раунды первым.
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка");
    }
    }
}


