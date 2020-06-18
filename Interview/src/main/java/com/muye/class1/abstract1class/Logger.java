package com.muye.class1.abstract1class;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;

/**
 * 抽象类
 *
 * @author gwh
 */

public abstract class Logger {
    private String name;
    private boolean enabled;
    private Level minPermittedLevel;

    public Logger(String name, boolean enabled, Level minPermittedLevel) {
        this.name = name;
        this.enabled = enabled;
        this.minPermittedLevel = minPermittedLevel;
    }

    public void log(Level level, String message) throws IOException {
        boolean loggable = enabled && (minPermittedLevel.intValue() <= level.intValue());
        if (!loggable) {
            return;
        }
        doLog(level, message);
    }

    protected abstract void doLog(Level level, String message) throws IOException;
}


/**
 * 抽象类的子类：输出日志到文件
 */
class FileLogger extends Logger {
    private Writer fileWriter;

    public FileLogger(String name, boolean enabled,
                      Level minPermittedLevel, String filepath) throws IOException {
        super(name, enabled, minPermittedLevel);
        this.fileWriter = new FileWriter(filepath);
    }

    @Override
    public void doLog(Level level, String mesage) throws IOException {
// 格式化 level 和 message, 输出到日志文件
        fileWriter.write("");
    }
}

/**
 * 抽象类的子类: 输出日志到消息中间件 (比如 kafka)
 */
class MessageQueueLogger extends Logger {
    private MessageQueueClient msgQueueClient;

    public MessageQueueLogger(String name, boolean enabled,
                              Level minPermittedLevel, MessageQueueClient msgQueueClient) {
        super(name, enabled, minPermittedLevel);
        this.msgQueueClient = msgQueueClient;
    }

    @Override
    protected void doLog(Level level, String mesage) {
// 格式化 level 和 message, 输出到消息中间件
        msgQueueClient.send();
    }
}