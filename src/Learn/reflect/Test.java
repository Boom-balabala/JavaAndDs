package Learn.reflect;

import java.lang.reflect.Field;

interface I1 {
}

interface I2 {
}

class Cell {
    public int mCellPublic;
}

class Animal extends Cell {
    private int mAnimalPrivate;
    protected int mAnimalProtected;
    int mAnimalDefault;
    public int mAnimalPublic;
    private static int sAnimalPrivate;
    protected static int sAnimalProtected;
    static int sAnimalDefault;
    public static int sAnimalPublic;
}

class Dog extends Animal implements I1, I2 {
    private int mDogPrivate;
    public int mDogPublic;
    protected int mDogProtected;
    private int mDogDefault;
    private static int sDogPrivate;
    protected static int sDogProtected;
    static int sDogDefault;
    public static int sDogPublic;
}

public class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<Dog> dog = Dog.class;
        //类名打印

        System.out.println(dog.getName());
        System.out.println(dog.getSimpleName());
        System.out.println(dog.getCanonicalName());
        /*
        Learn.reflect.Dog
        Dog
        Learn.reflect.Dog
         */
        //接口
        System.out.println(dog.isInterface());
        /*
        false
         */
        for (Class iI : dog.getInterfaces()) {
            System.out.println(iI);
        }
        /*
        interface Learn.reflect.I1
        interface Learn.reflect.I2
         */
         /*
          interface com.cry.I1
          interface com.cry.I2
         */

        //父类
        System.out.println(dog.getSuperclass());
        /*
        class Learn.reflect.Animal
         */
        //创建对象
        Dog d = dog.newInstance();
        //字段
        for (Field f : dog.getFields()) {
            System.out.println(f.getName());
        }
        /*
        获得某个类的所有的公共（public）的字段，包括继承自父类的所有公共字段。
            mDogPublic
            sDogPublic
            mAnimalPublic
            sAnimalPublic
            mCellPublic  //父类的父类的公共字段也打印出来了
         */
        System.out.println("---------");
        for (Field f : dog.getDeclaredFields()) {
            System.out.println(f.getName());
        }
        /* 获得某个类的自己声明的字段，即包括public、private和proteced，默认但是不包括父类声明的任何字段。
         mDogPrivate
         mDogPublic
         mDogProtected
         mDogDefault
         sDogPrivate
         sDogProtected
         sDogDefault
         sDogPublic
         */
    }
}
