package org.example;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.lang.Boolean;


// a6c79a27049109e472b246b5dfbe08aedff1e9e2259597e54032dbad4958d4ad - aboba
// 68a55e5b1e43c67f4ef34065a86c4c583f532ae8e3cda7e36cc79b611802ac07 - zzzzz
// 1115dd800feaacefdf481f1f9070374a2a81e27880f187396db67958b207cbad - ? (zyzzx)
// 3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b - ? (apple)
// 74e1bb62f8dabb8125a58852b63bdf6eaef667cb56ac7f7cdba6d7305c50a22f - ? (mmmmm)
public class Main {
    public static char a = 97;
    public static char z = 122;
    public static AtomicInteger symbols = new AtomicInteger(97);

    public static String getHash(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(str.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, InterruptedException, ExecutionException {
        Scanner scanner = new Scanner(System.in);
        int N;

        System.out.println("Input count of threads: ");
        N = scanner.nextInt();
        if (N <= 0) {
            System.err.println("Неверное число потоков");
            return;
        }

        String hash = scanner.next();

        long start = System.currentTimeMillis();
        ArrayList<Guesser<String>> guessers = new ArrayList<>();
        ConcurrentLinkedQueue<String> list = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < N; i++) {
            guessers.add(new Guesser<>(hash, list));
        }
        for (int i = a, index = 0; i <= z; i++) {
            guessers.get(index).addCharacter((char) i);
            if (index == N - 1)
                index = 0;
            else
                index++;
        }

        try (ExecutorService service = Executors.newFixedThreadPool(N);) {
            List<Future<Boolean>> futures = service.invokeAll(guessers);
            for (var item : futures)
                item.get();
            service.shutdown();
        }

        System.out.println("Список найденных паролей");
        System.out.println(list);

        long end = System.currentTimeMillis();

        System.out.println("Прошло времени " + (end - start) / 1000 + " секунд");
    }
}
