package cc.notalk.infinityjava.collection.list;

import java.util.ArrayList;

public class ArrayListAnalysis {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(new Object());
        list.add(null);
        list.add(null);
        System.out.println("list data is :" + list.toString());
        int i = 1;
        for (Object object : list) {
            if (i == 2) {
                list.remove(object);
            }
            i++;
        }
        System.out.println("list data is :" + list.toString());
    }
}
