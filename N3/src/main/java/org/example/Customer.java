package org.example;

import java.util.ArrayDeque;
import java.util.Random;
import java.util.concurrent.Callable;

public class Customer implements Callable<Boolean> {
    private final ArrayDeque<Integer> deque;
    private boolean[] info;

    Customer(ArrayDeque<Integer> deque, boolean[] info) {
        this.deque = deque;
        this.info = info;
    }

    @Override
    public Boolean call() throws Exception {
        while (true) {
            synchronized (deque) {
                if (!deque.isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + " customer get " + deque.poll());
                }
                else {
//                    Thread.sleep(1);
                }
                if (deque.isEmpty() && !info[0]) {
                    break;
                }
            }
        }

        return null;
    }
}
