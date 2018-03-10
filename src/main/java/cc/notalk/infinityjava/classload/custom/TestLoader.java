package cc.notalk.infinityjava.classload.custom;

public class TestLoader {
    public TestLoader() {
        System.out.println("::>The java-infinity project TestLoader class concustract method was invoked!" + TestLoader.class.getClassLoader().toString());
    }
}
