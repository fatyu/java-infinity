package cc.notalk.infinityjava.classload.custom;

public class CustomClassLoaderTest {

    public static void main(String[] args) {
        CustomClassLoader customClassLoader = new CustomClassLoader("fatyu-class-loader", "d:\\workspace\\tmp\\");
        CustomClassLoader sonCustomClassLoader = new CustomClassLoader("fatyu-subson-class-loader", "d:\\workspace\\tmp\\");
        try {
            /*调用父类的loadClass方法,会自动查找当前类加载器的parent对象,进行类加载*/
            Class clazz = sonCustomClassLoader.loadClass("cc.notalk.infinityjava.classload.custom.TestLoader");
            clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
