package com.company;

public class AnimalThread extends Thread{ // Класс AnimalThread с параметрами AnimalName и priority
    String AnimalName;
    int priority;
    AnimalThread(String AnimalName, int priority){ // Инициализатор
        this.AnimalName = AnimalName;
        this.priority = priority;
        this.start(); // В инициализаторе сразу запускается поток
    }

    @Override
    public void run() { // Переопределенный метод run() для старта поток
        while (true){ // Начало работы потоков в бесконечном цикле
            try{
                AnimalThread.sleep(50);
                if (AnimalName.equals("Turtle")) { // Проверка на название потока. Если это Turtle, то для него идут отдельные команды
                    SpeedValues.TurtleRan(); // Метод TurtleRan() для класса SpeedValues складывает скорость бега и расстояние, которое пробежало животное до этого
                    System.out.println(AnimalName + " ran " + SpeedValues.TurtleMetres); // Вывод того, сколько пробежало животное
                    if (SpeedValues.TurtleMetres >= 100){ //  Выход из бесконечного цикла в случае, если животное пробежало более 100 метров
                        System.out.println(AnimalName + " finished!");
                        break;
                    }
                 } else { // Все тоже самое для Rabbit
                    SpeedValues.RabbitRan();
                    System.out.println(AnimalName + " ran " + SpeedValues.RabbitMetres );
                    if (SpeedValues.RabbitMetres >= 100){
                        System.out.println(AnimalName + " finished!");
                        break;
                    }
                if (SpeedValues.TurtleMetres <= SpeedValues.RabbitMetres - 5){ // Две проверки на ускорение. Если черепаха отстает на 5 метров, то она ускоряется
                    SpeedValues.IncreaseTurtleSpeed(); // Метод для ускорения
                }
                if (SpeedValues.RabbitMetres <= SpeedValues.TurtleMetres - 5){ // Тоже самое для кролика
                    SpeedValues.IncreaseRabbitSpeed();
                }
            }
        } catch (InterruptedException e){
            System.out.println("Error");
        }
    }
}}

