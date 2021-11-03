package com.geekbrains;

public class Main {
    static final int SIZE = 1000000;
    static final int half = SIZE / 2;

    public static void main(String[] args)  {
        float myArray[] = new float[SIZE];
        new Thread(()->
                System.out.println("№1:Выполняем задание в 1 поток " +
                        new MyThread().var01(myArray) + " мс")
        ).start();
        new Thread(()->
                System.out.println("№2:Выполнение подсчета в два потока: " +
                        new MyThread().var02(myArray,half,SIZE) + " мс")
        ).start();
    }
}
