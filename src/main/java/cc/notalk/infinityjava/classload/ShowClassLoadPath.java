package cc.notalk.infinityjava.classload;

import java.net.URL;

/**
 * 显示类加载器父子关系
 * 在jvm中类加载器是采用双亲委派模型进行加载
 * BootStrap->ExtClassLoader->AppClassLoader->CustomClassLoader
 */
public class ShowClassLoadPath {

    public static void main(String[] args) {
//        ClassLoader classLoader = ShowClassLoadPath.class.getClassLoader();
//        String prefix = "";
//        while (classLoader != null) {
//            prefix += "-";
//            System.out.println(prefix + classLoader.toString());
//            classLoader = classLoader.getParent();
//        }
//        System.out.println(prefix + "-root:" + classLoader);
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        URL oracle = contextClassLoader.getResource("oracle");
        System.out.println("oracle url resource path is :"+oracle.getPath());
    }
}
