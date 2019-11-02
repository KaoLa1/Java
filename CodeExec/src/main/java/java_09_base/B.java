package java_09_base;

/**
 *  1.用static申明，JVM加载类时执行，仅执行一次
 *  2.类中直接用{}定义，每一次创建对象时执行
 *  3.静态域的初始化顺序是靠他们的位置决定的!
 */

public class B {

    static {
        System.out.println("静态块");
    }

    public static B t1 = new B();
    public static B t2 = new B();

    {
        System.out.println("构造块");
    }


    public static void main(String[] args) {
        B t = new B();
    }
}