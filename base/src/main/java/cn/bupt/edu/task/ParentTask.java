package cn.bupt.edu.task;

import cn.bupt.edu.thread.ParentThread;
import org.slf4j.Logger;

public interface ParentTask {
    public void initThread();

    public Logger getLogger();

    public ParentThread getThread();
}
