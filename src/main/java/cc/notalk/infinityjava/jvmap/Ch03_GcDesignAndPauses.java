package cc.notalk.infinityjava.jvmap;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.运行G1作为垃圾回收器
 */

public class Ch03_GcDesignAndPauses {
    static List<Object> l;

    public static void main(String... args) {
        l = new ArrayList<>();
        for (int c = 0; c < 100_000_000; c++) {
            l.add(new Object());
        }
    }
}