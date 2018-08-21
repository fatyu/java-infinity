package cc.notalk.infinityjava.jvmap;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)//预热五次 间隔一秒
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS) //测试五次 间隔一秒
@Fork(value = 3, jvmArgsAppend = {"-Xmx1m", "-Xms1m", "-XX:+PrintGCDetails"})
//@Fork(value = 3, jvmArgsAppend = {"-Xmx1g", "-Xms1g"})
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class Ch02_TransportHugePage {
    @Param({"1000",
            "10000",
            "1000000",
            "10000000",
            "100000000"})
    int size;

    byte[] mem;

    @Setup
    public void setup() {
        mem = new byte[size];
    }

    @Benchmark
    public byte test() {
        return mem[ThreadLocalRandom.current().nextInt(size)];
    }
}