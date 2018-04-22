package cc.notalk.infinityjava.collection;

import java.util.HashMap;

public class HashMapAnalysis {
    public static void main(String[] args) {
        final HashMap map = new HashMap();
        Thread wt1 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(i, i);
                }
            }
        });


        Thread wt2 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(i, i);
                }
            }
        });


        Thread wt3 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(i, i);
                }
            }
        });
        Thread wt4 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(i, i);
                }
            }
        });


        Thread wt5 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(i, i);
                }
            }
        });


        Thread rt1 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.get(i);
                    System.out.println(Thread.currentThread().getName() + " get " + i);
                }
            }
        });
        Thread rt2 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.get(i);
                    System.out.println(Thread.currentThread().getName() + " get " + i);
                }
            }
        });
        Thread rt3 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.get(i);
                    System.out.println(Thread.currentThread().getName() + " get " + i);
                }
            }
        });
        Thread rt4 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.get(i);
                    System.out.println(Thread.currentThread().getName() + " get " + i);
                }
            }
        });
        Thread rt5 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.get(i);
                    System.out.println(Thread.currentThread().getName() + " get " + i);
                }
            }
        });

        wt1.start();
        wt2.start();
        wt3.start();
        wt4.start();
        wt5.start();


        rt1.start();
        rt2.start();
        rt3.start();
        rt4.start();
        rt5.start();


    }
}
