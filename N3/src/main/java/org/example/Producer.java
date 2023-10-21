package org.example;

import java.util.ArrayDeque;
import java.util.Random;
import java.util.concurrent.Callable;

public class Producer implements Callable<Boolean> {
    private final ArrayDeque<Integer> deque;
    private boolean[] info;

    Producer(ArrayDeque<Integer> deque, boolean[] info) {
        this.deque = deque;
        this.info = info;
    }

    @Override
    public Boolean call() throws Exception {
        Random random = new Random();
        while (info[0]) {
            synchronized (deque) {
                if (deque.size() < 100 && info[0]) {
                    int a = random.nextInt(1, 100);
                    deque.push(a);
                    System.out.println(Thread.currentThread().getName() + " producer put " + a);
                }
                else {
//                    Thread.sleep(1);
                }
            }
        }
        return null;
    }
}