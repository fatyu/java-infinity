package cc.notalk.infinityjava.basic;

/**
 * dump 你的堆；
 * 下载 Eclipse Memory Analyzer ；
 * 导入你 dump 文件；
 * 然后 oql 搜索 select * from java.lang.String s where s.toString() = "java"，在结果中 list incoming object
 */
public class StringIntern {
    public static void main(String[] args) {
        String str1 = new StringBuilder("hel").append("lo").toString();
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str1.intern() == str1); // true
        System.out.println(str2.intern() == str2); // false
    }
}

