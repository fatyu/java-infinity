package cc.notalk.infinityjava.jvmap;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * */
@Warmup(iterations = 3)//热身jvm，执行三次
@Measurement(iterations = 3)
@Fork(3)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Ch04_TLAB {
    @Benchmark
    public Object test() {
        final int size = 50_000_000;
        Object[] objects = new Object[size];
        for (int i = 0; i < size; i++) {
            objects[i] = new Object();
        }
        return objects;
    }
}