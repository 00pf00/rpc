package cn.bupt.edu.server.context;

import cn.bupt.edu.server.controller.HandlerController;

public interface HandlerContext {
    public void RegisterHandler(String path, HandlerController handler);

    public void RemoveHandler(String path);

    public HandlerMethod GetHandler(String path);

    public void SetHandler(HandlerMethod hm);

}
