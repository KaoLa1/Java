package singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author gwh
 * @Desc 最佳单例模式--枚举
 */
public enum EnumSingleton {

    INSTANCE;

    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }

}