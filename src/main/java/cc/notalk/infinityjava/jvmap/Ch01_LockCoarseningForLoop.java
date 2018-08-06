package cc.notalk.infinityjava.jvmap;

public class Ch01_LockCoarseningForLoop {


    public static void main(String[] args) {
        Object obj = new Object();
        for (int i = 0; i < 100; i++) {
            synchronized (obj) {
                System.out.println(">the lock is in for loop ");
            }
        }
        synchronized (obj) {
            for (int i = 0; i < 100; i++) {
                System.out.println("<the lock is out for loop");
            }
        }
    }
}



