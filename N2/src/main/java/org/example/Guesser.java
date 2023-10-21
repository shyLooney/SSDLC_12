package org.example;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.lang.Boolean;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

public class Guesser<T> implements Callable<Boolean> {
    private LinkedList<Character> list;
    private String ourStr;
    private ConcurrentLinkedQueue<String> concurrentLinkedQueue;
    private long time;

    Guesser(String ourStr, ConcurrentLinkedQueue<String> concurrentLinkedQueue)  {
        list = new LinkedList<>();
        this.ourStr = ourStr;
        this.concurrentLinkedQueue = concurrentLinkedQueue;

        time = System.currentTimeMillis();
    }

    public void addCharacter(Character ch) {
        list.add(ch);
    }

    @Override
    public Boolean call() throws Exception {
        while (!list.isEmpty()) {
            char c = list.poll();
            System.out.println(Thread.currentThread().getName() + " execute " + c);
            getAllVariants("" + c, 5, ourStr);
        }
        return false;
    }

    private void getAllVariants(String str, int len, String ourStr) throws NoSuchAlgorithmException {
        if (str.length() == len) {
            if (ourStr.equals(Main.getHash(str))) {
                concurrentLinkedQueue.add(str);
                System.out.println("Найден возможный пароль: " + str + " за " +
                        (System.currentTimeMillis() - time) / 1000 + " секунд");
            }
        }
        else {
            for (char i = 'a'; i <= 'z'; i++) {
                getAllVariants(str + i, len, ourStr);
            }
        }
    }

}
