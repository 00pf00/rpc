package cn.bupt.edu.base.task;

import cn.bupt.edu.base.thread.ParentThread;
import org.slf4j.Logger;

public interface ParentTask {
    public void initThread();

    public Logger getLogger();

    public ParentThread getThread();
}
