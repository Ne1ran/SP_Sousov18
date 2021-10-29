package com.company;

import java.util.Random;

public class Account extends Thread{
    int balance;
    int months = 0;
    Account(int balance){
        this.balance = balance;
    }

    @Override
    public void run(){
        Random random = new Random();
        while (true) {
            months++;
            int balanceIncome = 0;
            for (int i = 0; i < 5; i++) {
                balanceIncome += random.nextInt(50);
            }
            this.giveInMoney(balanceIncome);
            if (this.checkoutMoney()) {
                break;
            }
        }
    }

    boolean checkoutMoney(){
        if (this.balance >= 300){
            this.balance -= 300;
            System.out.println("Покупка произошла, вы проработали " + this.months + " месяцев на счету осталось " + this.balance + " $");
            return true;
        } else System.out.println("Недостаточно средств на счету, необходимо 300$, а на балансе " + this.balance +" $");
        return false;
    }
    void giveInMoney(int income){
       this.balance += income;
        System.out.println("Вы заработали " + income + " $ за этот месяц" );
    }
}
