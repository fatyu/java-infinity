package cc.notalk.infinityjava.v1_8.lambda;

import com.google.common.collect.Lists;

/**
 * @author notalk
 */
public class LambdaFoo {
    public static void main(String[] args) {
        String params = "|||";
        //编译器自动检测
        Lists.asList("z", new String[]{"y", "zhangyu"}).forEach(str -> {
            //    params = new String("..."); //ERROR  variale in lambda should be final
            System.out.println("current index is " + str + ">" + params);
        });
    }
}
