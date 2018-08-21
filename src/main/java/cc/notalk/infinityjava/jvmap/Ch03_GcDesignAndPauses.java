package cc.notalk.infinityjava.jvmap;

import java.util.ArrayList;
import java.util.List;

/**
 * 前提：使用最新的jdk9
 * 1.运行G1作为垃圾回收器  -Xms4G -Xmx4G -Xlog:gc
 * 2.使用并行回收器 -XX:+UseParallelOldGC -Xms4G -Xmx4G -Xlog:gc
 * 3.使用CMS -XX:+UseConcMarkSweepGC -Xms4G -Xmx4G -Xlog:gc
 * 4.使用开发中的Shenandoah -XX:+UseConcMarkSweepGC -Xms4G -Xmx4G -Xlog:gc
 * 5.使用Epsilon???没看懂! -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC -Xms4G -Xmx4G
 *  无操作GC，会根据当前环境进行判断是否需要做GC，减少gc的时间消耗?
 * */

public class Ch03_GcDesignAndPauses {
    static List<Object> l;

    public static void main(String... args) {
        l = new ArrayList<>();
        for (int c = 0; c < 100_000_000; c++) {
            l.add(new Object());
        }
    }
}