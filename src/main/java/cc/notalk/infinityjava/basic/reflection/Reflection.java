package cc.notalk.infinityjava.basic.reflection;

import java.lang.reflect.Field;

/**
 * @author fatyu
 * java反射常见用法
 * 提供类和接口，以获得关于类和对象的反射信息。在安全限制内，反射允许编程访问关于加载类的字段、方法和构造方法的信息，并允许使用反射字段、方法和构造方法对其底层对等项进行操作
 * 用途:
 * 通过 xml 或注解等元数据，实现依赖注入 (DI)，注解处理，动态代理，单元测试等功能。比如SSH 框架
 * 调用一些私有方法，实现黑科技。
 * 实现序列化与反序列化，比如 PO 的 ORM，Json 解析等
 */
public class Reflection {


    public static void main(String[] args) {
        ReflectionTraget rt = new ReflectionTraget();
        Class<? extends ReflectionTraget> reflctionTargetClass = rt.getClass();
        java.lang.reflect.Constructor<?>[] constructors = reflctionTargetClass.getDeclaredConstructors();
        /*
         * class对象getFields()方法只能获取public修饰的成员变量
         */
        java.lang.reflect.Field[] fields = reflctionTargetClass.getFields();
        for (java.lang.reflect.Field field : fields) {
            System.out.println(field.getName() + ":" + field.getAnnotatedType().getType().getTypeName());
        }
        /*
         *获取所有的成员变量getDeclared***()
         */
        Field[] declaredFields = reflctionTargetClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("declaredFields -->" + field.getName() + ":" + field.getAnnotatedType().getType().getTypeName());
        }
        try {
            Class<?> aClass = Class.forName(rt.getClass().getName());
            Object rtForName = aClass.newInstance();
            System.out.println(rt.getClass().getName() == rtForName.getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

//        try {
//            ReflectionTraget reflectionTraget1 = reflctionTargetClass.newInstance();
//            System.out.println(reflectionTraget1 == rt);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }
}
