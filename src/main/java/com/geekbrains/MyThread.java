package com.geekbrains;
import java.util.Arrays;

public class MyThread {
    private final Object mon = new Object();

    // первый вариант метода:
    // бежит по массиву и вычисляет значения
    public long var01 (float myArray[]){
        long a = System.currentTimeMillis();
        for (int i = 0; i < myArray.length; i++){
            myArray[i] = 1f;
        }
        // добавляем формулу
        // arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = (float) (myArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        // возвращаем результат
        return (System.currentTimeMillis() - a);
    }

    public synchronized long var02 (float myArray[], int half, int size){
        long a = System.currentTimeMillis();
        for (int i = 0; i < myArray.length; i++){
            myArray[i] = 1f;
        }
        // делим на 2 массива
        float[] halfArr1 = Arrays.copyOfRange(myArray, 0, myArray.length/2);
        float[] halfArr2 = Arrays.copyOfRange(myArray, myArray.length/2, myArray.length);

        new Thread(()->{
            for (int i = 0; i < halfArr1.length; i++){
                halfArr1 [i] = (float)(halfArr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < halfArr2.length; i++){
                halfArr2 [i] = (float)(halfArr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }).start();
        synchronized (mon){
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        // склеиваем массивы в один
        System.arraycopy(halfArr1, 0 , myArray, 0, half);
        System.arraycopy(halfArr2, 0, myArray, 0, half);
        // возвращаем результат
        return (System.currentTimeMillis() - a);
    }
}
