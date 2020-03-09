package cn.bupt.edu.base.task;

import cn.bupt.edu.base.thread.ParentThread;

public abstract class AbstractParentTask implements ParentTask {
    public ParentThread getThread() {
        Thread t = Thread.currentThread();
        if (t instanceof ParentThread) {
            return (ParentThread) t;
        }
        return null;
    }
}
