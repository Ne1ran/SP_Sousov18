package com.company;

public class SpeedValues { // Класс, содержащий различные переменные для работы программы
    public static int RabbitSpeed = 4; // Скорость кролика
    public static int TurtleSpeed = 1; // черепахи
    public static int RabbitMetres = 0; // Пробег метров кролика
    public static int TurtleMetres = 0; // черепахи
    static void IncreaseRabbitSpeed() { // Инкремент скорости кролика
        System.out.println("Rabbit speeds up!");
        RabbitSpeed++;
    }

    static void IncreaseTurtleSpeed() { // черепахи
        System.out.println("Turtle speeds up!");
        TurtleSpeed++;
    }

    static void RabbitRan() {
        RabbitMetres += RabbitSpeed;
    } //  Пробег кролика

    static void TurtleRan() {
        TurtleMetres += TurtleSpeed;
    } // черепахи
}
