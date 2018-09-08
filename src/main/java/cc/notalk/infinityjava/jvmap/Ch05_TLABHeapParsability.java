package cc.notalk.infinityjava.jvmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Ch05_TLABHeapParsability {

    public static void main(String[] args) {
        final int TRAKTORISTOV = 300;
        CountDownLatch cdl = new CountDownLatch(TRAKTORISTOV);


        for (int i = 0; i < TRAKTORISTOV; i++) {
            new Thread(() -> allocateAndWait(cdl)).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Object> list = new ArrayList<>();
        new Thread(() -> allocateAndDie(list)).start();
    }


    public static void allocateAndWait(CountDownLatch cdl) {
        Object obj = new Object();
        cdl.countDown();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println(obj);
    }


    public static void allocateAndDie(Collection collection) {
        while (true) {
            collection.add(new Object());
        }
    }
}