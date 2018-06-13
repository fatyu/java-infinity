package cc.notalk.infinityjava.collection.list;

import java.util.ArrayList;
import java.util.Vector;

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


    public void vector() {
        Vector<User> users = new Vector<User>();//默认创建10个元素的数组
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setName("user:" + i);
            user.setAge(i);
            users.add(user);
        }
        users.add(6, new User());
        //添加用户信息到vector

    }

    class User {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        private String name;
        private String password;
        private int age;
    }
}

//----关于arraylist的描述
/*
Resizable-array implementation of the <tt>List</tt> interface.  Implements
 all optional list operations, and permits all elements, including
 <tt>null</tt>.  In addition to implementing the <tt>List</tt> interface,
 this class provides methods to manipulate the size of the array that is
 used internally to store the list.  (This class is roughly equivalent to
 <tt>Vector</tt>, except that it is unsynchronized.)
 #翻译如下:
 arraylist是实现了list接口的可扩容数组,实现了List接口的所有方法,和存储所有元素(包括null)
 为了扩展实现list接口,这个类提供了操作数组(这个类是基于数组实现)的很多方法(和arraylist相似的是vector,只不过arraylist不是同步的.)
 */