package cc.notalk.infinityjava.jvmap;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class Ch06_UserInit {

    @Benchmark
    public Object init() {
        return new Init(42);
    }

    @Benchmark
    public Object initLeaky() {
        return new InitLeaky(42);
    }

    static class Init {
        private int x;

        public Init(int x) {
            this.x = x;
        }
    }

    static class InitLeaky {
        private int x;

        public InitLeaky(int x) {
            doSomething();
            this.x = x;
        }

        @CompilerControl(CompilerControl.Mode.DONT_INLINE)
        void doSomething() {
            // intentionally left blank
        }
    }
}