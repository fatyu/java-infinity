package cc.notalk.infinityjava.classload.custom;

import java.io.*;

/**
 * @author fatyu
 * 自定义类加载器
 * 使用场景:https://www.zhihu.com/question/46719811
 * 自定义类加载器,只需要重写父类的findClass方法即可,在调用的时候调用加载器的loadClass方法,会自动使用双亲委派模式进行类加载
 * @see cc.notalk.infinityjava.classload.custom.CustomClassLoaderTest
 */
public class CustomClassLoader extends ClassLoader {
    private String loaderName;
    private String classFilePath;


    public CustomClassLoader(String loaderName, String classFilePath) {
        super();
        this.classFilePath = classFilePath;
        this.loaderName = loaderName;
    }

    public CustomClassLoader(ClassLoader parent, String loaderName, String classFilePath) {
        super(parent);
        this.classFilePath = classFilePath;
        this.loaderName = loaderName;
    }

    @Override
    public String toString() {
        return "CustomClassLoader{" +
                "loaderName='" + loaderName + '\'' +
                ", classFilePath='" + classFilePath + '\'' +
                '}';
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = readClassData(name);
        if (null == classData) {
            throw new ClassNotFoundException();
        }
        return defineClass(name, classData, 0, classData.length);
    }

    private byte[] readClassData(String name) {
        byte[] result = null;
        InputStream is = null;
        name = name.replaceAll("\\.", "/");
        File file = new File(classFilePath + name + ".class");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            is = new FileInputStream(file);
            int data = 0;
            while ((data = is.read()) != -1) {
                baos.write(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return baos.toByteArray();
    }
}
