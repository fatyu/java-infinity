package cc.notalk.infinityjava.basic;

/**
 * @author fatyu
 * 介绍java中Integer和基本类型int的区别
 */
public class IntAndInteger {
    public static void main(String[] args) {

        int i = 127;
        Integer num = 127;
        /*
         * 输出结果为true
         * 解释:在java1.5版本后对于int和Integer比较会进行自动拆箱操作,比较的是具体的值
         */
        System.out.println("Is int equals with Integer ? " + (i == num));
        Integer num127 = 127;
        Integer numValueOf127 = Integer.valueOf(127);
        Integer new127 = new Integer(127);
//        System.out.println(i == numValueOf127); // 结果等同 i==num 自动拆箱的原因
//        System.out.println(i == new127);// 结果等同 i==num 自动拆箱的原因
        /*
         * 输出结果为true
         * 解释: == 比较的是对象的引用地址,但是查看Integer.valueOf源码可以看出jvm是对-128~127的数字进行了缓存,所以这两个变量指向的是同一个对象.故而两个声明的==比较是true
         * 如果数字改为128,输出结果就会是false;
         */
        System.out.println("Is valueOf equals with new ? " + (num127 == numValueOf127));
        /*
         * 输出结果为false
         * 解释: == 比较的是对象的引用地址,不相等
         */
        System.out.println("Is valueOf equals with new ? " + (num127 == new127));


    }
}
