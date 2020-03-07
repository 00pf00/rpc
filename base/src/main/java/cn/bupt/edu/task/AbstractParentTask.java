package cn.bupt.edu.task;

import cn.bupt.edu.thread.ParentThread;

public abstract class AbstractParentTask implements ParentTask {
    public ParentThread getThread() {
        Thread t = Thread.currentThread();
        if (t instanceof ParentThread) {
            return (ParentThread) t;
        }
        return null;
    }
}
