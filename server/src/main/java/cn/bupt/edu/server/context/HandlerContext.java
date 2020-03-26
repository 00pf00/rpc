package cn.bupt.edu.server.context;

import cn.bupt.edu.server.controller.HandlerController;

import java.util.concurrent.ArrayBlockingQueue;

public interface HandlerContext {
    public void RegisterHandler(String path, HandlerController handler, ArrayBlockingQueue queue);

    public void RegisterMethod(String path, HandlerController handler, int... bc);

    public void RemoveHandler(String path);

    public HandlerMethod GetHandler(String path);

    public void SetHandler(HandlerMethod hm);

}
