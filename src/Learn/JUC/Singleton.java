package Learn.JUC;

public class Singleton {
    public static volatile Singleton singleton;
    /**
     * 构造函数私有，禁止外部实例化
     */
    private Singleton() {};
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    /**
                     *  这段代码其实是分为三步执行：
                     * <p>
                     * 1. 为 uniqueInstance 分配内存空间
                     * 2. 初始化 uniqueInstance
                     * 3. 将 uniqueInstance 指向分配的内存地址
                     * 但是由于操作系统可以`对指令进行重排序`，所以上面的过程也可能会变成如下过程：
                     * 	1. 分配内存空间
                     * 	2. 将内存空间的地址赋值给对应的引用
                     * 	3. 初始化对象
                     * 如果是这个流程，多线程环境下就可能将一个未初始化的对象引用暴露出来，从而导致不可预料的结果。
                     */
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}