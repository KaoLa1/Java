package java8;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * <h3>Java8</h3>
 * <p>lambda基础语法</p>
 *
 * @author : gwh
 * @date : 2019-11-03 13:24
 **/
public class Lambda1 {
    @Test
    public void test1() {
        Runnable runnable = new Runnable() {
            int num = 0; //jdk 1.7 前，必须是 final

            @Override
            public void run() {
                System.out.println("Hello world " + num);
            }
        };
        runnable.run();
        System.out.println("----------------");
        //lambda 表达式
        Runnable r = ()-> System.out.println("Hello lambda");
        r.run();
    }

    @Test
    public void test2(){
        Consumer<String> consumer =(x)-> System.out.println(x);
        consumer.accept("测试有一个参数的");
    }

    @Test
    public void test3(){
        Comparator<Integer> comparator = (x,y)->{
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }

    @Test
    public void test4(){
        Comparator<Integer> com = (x,y)-> Integer.compare(x, y);
    }


}
