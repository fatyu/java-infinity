package cc.notalk.infinityjava.collection.array;

/**
 * 介绍array的日常使用
 */
public class Arrays {
    //array basics
    int[] ints = new int[10];//穿件10个元素的整型数组

    //----------------------------------------------数组声明方式
    public void arrayBasic() {
        int array[] = new int[10000];//表示int类型在array数据中

        int[] intArray = new int[10000];//标识整形数组，变量名为intArray

    }

    //----------------------------------------------------------------------tips:1.3版本遍历数组时倒序遍历性能会比正序性能更佳
    //public static void main(String[] args) {
    //
    //    int array[] = initIntArray(10000000);
    //    System.out.println(
    //            costMillsAsc(array));
    //
    //    System.out.println(
    //            costMillsDesc(array));
    //}


    public static long costMillsAsc(int[] array) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long costMillsDesc(int[] array) {
        long start = System.currentTimeMillis();
        for (int i = array.length - 1; i > 0; --i) {
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static int[] initIntArray(int length) {
        if (length <= 0) {
            throw new RuntimeException("cannot init array length lt 0");

        }
        int i = 0;
        int[] array = new int[length];
        do {
            array[i] = i;
            i++;
        } while (i < length);
        return array;
    }
    //---------------基本数据类型数组，对象数组，多维数组
    

}
