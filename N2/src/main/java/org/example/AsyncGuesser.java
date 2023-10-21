package org.example;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Supplier;

public class AsyncGuesser implements Supplier<Boolean> {
    private String ourStr;
    private ConcurrentLinkedQueue<String> concurrentLinkedQueue;
    private int ch;

    AsyncGuesser(String ourStr, ConcurrentLinkedQueue<String> concurrentLinkedQueue, int ch)  {
        this.ourStr = ourStr;
        this.concurrentLinkedQueue = concurrentLinkedQueue;
        this.ch = ch;
    }

    @Override
    public Boolean get() {
        if (ch > 'z') {
            System.out.println(Thread.currentThread().getName() + " stops");
            return true;
        }
        try {
            System.out.println(Thread.currentThread().getName() + " execute " + (char) ch);
            getAllVariants("" + (char) ch, 5, ourStr);
        }
        catch (NoSuchAlgorithmException ignored) {

        }
        return false;
    }

    private void getAllVariants(String str, int len, String ourStr) throws NoSuchAlgorithmException {
        if (str.length() == len) {
            if (ourStr.equals(Main.getHash(str))) {
                concurrentLinkedQueue.add(str);
                System.out.println("Найден возможный пароль: " + str);
            }
        }
        else {
            for (char i = 'a'; i <= 'z'; i++) {
                getAllVariants(str + i, len, ourStr);
            }
        }
    }
}
