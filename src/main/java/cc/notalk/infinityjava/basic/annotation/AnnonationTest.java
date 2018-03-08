package cc.notalk.infinityjava.basic.annotation;

import java.lang.annotation.Annotation;

@ClassInfo(author = "zhangyu", remark = "this is an annotation test", date = "2018-03-08", version = "1.0.0")
@Deprecated
public class AnnonationTest {
    public static void main(String[] args) {
        AnnonationTest clazz = new AnnonationTest();


        Annotation[] annotations = clazz.getClass().getAnnotations();
        for (Annotation annotation : annotations
                ) {
            if (annotation instanceof ClassInfo) {
                ClassInfo classInfo = (ClassInfo) annotation;
                System.out.println(classInfo.author() + " ,version:" + classInfo.version() + ",remark:" + classInfo.remark());
            } else {
                System.out.println(annotation.toString());
            }

        }
    }
}
