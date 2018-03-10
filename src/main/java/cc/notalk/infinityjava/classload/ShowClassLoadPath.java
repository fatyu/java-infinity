package cc.notalk.infinityjava.classload;

/**
 * 显示类加载器父子关系
 * 在jvm中类加载器是采用双亲委派模型进行加载
 * BootStrap->ExtClassLoader->AppClassLoader->CustomClassLoader
 */
public class ShowClassLoadPath {

    public static void main(String[] args) {
        ClassLoader classLoader = ShowClassLoadPath.class.getClassLoader();
        String prefix = "";
        while (classLoader != null) {
            prefix += "-";
            System.out.println(prefix + classLoader.toString());
            classLoader = classLoader.getParent();
        }
        System.out.println(prefix + "-root:" + classLoader);
    }
}
