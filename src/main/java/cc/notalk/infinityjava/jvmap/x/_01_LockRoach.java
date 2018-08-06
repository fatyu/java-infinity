package cc.notalk.infinityjava.jvmap.x;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)//预热 防止jit优化导致测试不准确
@Measurement(iterations = 4, time = 1, timeUnit = TimeUnit.SECONDS)//度量测试规则
//jvm进程数量及参数
@Fork(value = 1, jvmArgsPrepend = {"-XX:LoopUnrollLimit=1", "-XX:-TieredCompilation", "-XX:-UseBiasedLocking"})
//@Fork(value = 1, jvmArgsPrepend = {"-XX:-UseBiasedLocking"})
@State(Scope.Benchmark)//所有的线程共享一个实例，用于测试有状态在多线程环境性能
@BenchmarkMode(Mode.AverageTime)//基准测试的模式 吞吐量，平均时间，随机取样，只运行一次
@OutputTimeUnit(TimeUnit.NANOSECONDS)//
public class _01_LockRoach {
    int x;

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)//编译器行为
    public void test() {
        for (int i = 0; i < 1000; i++) {
            synchronized (this) {
                x += 0x42;
            }
        }
    }

    //public static void main(String[] args) {
    //    Options opt = new OptionsBuilder()
    //            .include(_01_LockRoach.class.getSimpleName())
    //            .forks(1)
    //            .build();
    //    try {
    //        new Runner(opt).run();
    //    } catch (RunnerException e) {
    //        e.printStackTrace();
    //    }
    //}
}
/**
 /Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/bin/java-Dfile.encoding=UTF-8-classpath/Users/notalk/workspace/labs/java-infinity/target/classes:/Users/notalk/.m2/repository/com/google/guava/guava/24.0-jre/guava-24.0-jre.jar:/Users/notalk/.m2/repository/com/google/code/findbugs/jsr305/1.3.9/jsr305-1.3.9.jar:/Users/notalk/.m2/repository/org/checkerframework/checker-compat-qual/2.0.0/checker-compat-qual-2.0.0.jar:/Users/notalk/.m2/repository/com/google/errorprone/error_prone_annotations/2.1.3/error_prone_annotations-2.1.3.jar:/Users/notalk/.m2/repository/com/google/j2objc/j2objc-annotations/1.1/j2objc-annotations-1.1.jar:/Users/notalk/.m2/repository/org/codehaus/mojo/animal-sniffer-annotations/1.14/animal-sniffer-annotations-1.14.jar:/Users/notalk/.m2/repository/commons-io/commons-io/2.6/commons-io-2.6.jar:/Users/notalk/.m2/repository/org/apache/commons/commons-lang3/3.7/commons-lang3-3.7.jar:/Users/notalk/.m2/repository/net/bytebuddy/byte-buddy/1.8.0/byte-buddy-1.8.0.jar:/Users/notalk/.m2/repository/org/openjdk/jmh/jmh-core/1.21/jmh-core-1.21.jar:/Users/notalk/.m2/repository/net/sf/jopt-simple/jopt-simple/4.6/jopt-simple-4.6.jar:/Users/notalk/.m2/repository/org/apache/commons/commons-math3/3.2/commons-math3-3.2.jar org.openjdk.jmh.Main cc.notalk.infinityjava.jvmap.x._01_LockRoach.test
 # JMH version:1.21
 # VM version:JDK1.8.0_162,Java HotSpot(TM)64-Bit Server VM,25.162-b12
 # VM invoker:/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/bin/java
 # VM options:-XX:-UseBiasedLocking-Dfile.encoding=UTF-8
 # Warmup:10iterations,1s each
 # Measurement:4iterations,1s each
 # Timeout:10min per iteration
 # Threads:1thread,will synchronize iterations
 # Benchmark mode:Average time,time/op
 # Benchmark:cc.notalk.infinityjava.jvmap.x._01_LockRoach.test

 # Run progress:0.00%complete,ETA 00:00:14
 # Fork:1of 1
 # Warmup Iteration 1:8282.762ns/op
 # Warmup Iteration 2:9957.629ns/op
 # Warmup Iteration 3:8065.504ns/op
 # Warmup Iteration 4:8723.336ns/op
 # Warmup Iteration 5:8774.220ns/op
 # Warmup Iteration 6:6983.072ns/op
 # Warmup Iteration 7:6952.450ns/op
 # Warmup Iteration 8:6968.694ns/op
 # Warmup Iteration 9:6936.622ns/op
 # Warmup Iteration 10:6942.407ns/op
 Iteration 1:6982.734ns/op
 Iteration 2:6910.389ns/op
 Iteration 3:7080.861ns/op
 Iteration 4:7125.333ns/op


 Result"cc.notalk.infinityjava.jvmap.x._01_LockRoach.test":
 7024.829 ±(99.9%)625.504ns/op[Average]
 (min,avg,max)=(6910.389,7024.829,7125.333),stdev=96.797
 CI(99.9%):[6399.325,7650.334](assumes normal distribution)


 # Run complete.Total time:00:00:15

 REMEMBER:The numbers below are just data.To gain reusable insights,you need to follow up on
 why the numbers are the way they are.Use profilers(see-prof,-lprof),design factorial
 experiments,perform baseline and negative tests that provide experimental control,make sure
 the benchmarking environment is safe on JVM/OS/HW level,ask for reviews from the domain experts.
 Do not assume the numbers tell you what you want them to tell.

 Benchmark Mode Cnt Score Error Units
 _01_LockRoach.test avgt 4 7024.829 ± 625.504ns/op

 Process finished with exit code 0

 运行环境：

 13年版本mbp，MacBook Pro (Retina, 13-inch, Late 2013)

 Processor 2.4 GHz Intel Core i5

 Memory 8 GB 1600 MHz DDR3

 */
