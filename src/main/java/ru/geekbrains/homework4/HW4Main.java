package ru.geekbrains.homework4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HW4Main {
    private static Object monitor = new Object();
    private static volatile char currentChar = 'A';
    private static final int MAX_ITERATIONS = 5;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() -> {
            try {
                synchronized (monitor) {
                    for (int i = 0; i < MAX_ITERATIONS; i++) {
                        while (currentChar != 'A') {

                            monitor.wait();
                        }

                        System.out.print(currentChar);
                        currentChar = 'B';
                        monitor.notifyAll();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });

        executorService.execute(() -> {
            try {
                synchronized (monitor) {
                    for (int i = 0; i < MAX_ITERATIONS; i++) {
                        while (currentChar != 'B') {

                            monitor.wait();
                        }

                        System.out.print(currentChar);
                        currentChar = 'C';
                        monitor.notifyAll();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });

        executorService.execute(() -> {
            try {
                synchronized (monitor) {
                    for (int i = 0; i < MAX_ITERATIONS; i++) {
                        while (currentChar != 'C') {

                            monitor.wait();
                        }

                        System.out.print(currentChar);
                        currentChar = 'A';
                        monitor.notifyAll();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });

        executorService.shutdown();

    }

}

