package org.example;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    private static boolean[] info = new boolean[1];


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        info[0] = true;

        try (Scanner scanner = new Scanner(System.in);
             ExecutorService service = Executors.newFixedThreadPool(5);) {
            ArrayDeque<Integer> deque = new ArrayDeque<>(200);

            ArrayList<Producer> producers = new ArrayList<>();
            for (int i = 0; i < 3; i++)
                producers.add(new Producer(deque, info));

            ArrayList<Customer> customers = new ArrayList<>();
            for (int i = 0; i < 2; i++)
                customers.add(new Customer(deque, info));

            ArrayList<Future<Boolean>> arrayList = new ArrayList<>();

            arrayList.add(service.submit(producers.get(0)));
            arrayList.add(service.submit(producers.get(1)));
            arrayList.add(service.submit(producers.get(2)));
            arrayList.add(service.submit(customers.get(0)));
            arrayList.add(service.submit(customers.get(1)));

            Thread thread = new Thread(() -> {
                try {
                    while (info[0]) {
                        if (System.in.available() > 0) {
                            int input = System.in.read();
                            if (input == 10) {
                                System.out.println("Выход из программы...");
                                info[0] = false;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();

            for (var item : arrayList) {
                item.get();
                thread.interrupt();
            }

            service.shutdown();
        }
    }
}