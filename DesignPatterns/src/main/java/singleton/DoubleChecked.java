package singleton;

/**
 * @author : gwh
 * @Desc: 双重检查锁
 * @date : 2020-06-18 15:14
 **/
public class DoubleChecked {
    public static class SingletonDemo {

        private static volatile SingletonDemo instance = null;

        private SingletonDemo() {
            System.out.println(Thread.currentThread().getName() + "\t 构造方法");
        }

        /**
         * 双重检测机制     * @return
         */
        public static SingletonDemo getInstance() {
            if (instance == null) {
                synchronized (SingletonDemo.class) {
                    if (instance == null) {
                        instance = new SingletonDemo();
                    }
                }
            }
            return instance;
        }

        public static void main(String[] args) {
            for (int i = 1; i <= 10; i++) {
                new Thread(() -> {
                    SingletonDemo.getInstance();
                }, String.valueOf(i)).start();
            }
        }
    }
}
