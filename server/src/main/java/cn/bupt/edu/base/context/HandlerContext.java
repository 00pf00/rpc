package cn.bupt.edu.base.context;

public interface HandlerContext {
    public void RegisterHandler(Object obj, String path);

    public void RemoveHandler(String path);

    public HandlerMethod GetHandler(String path);

    public void SetHandler(HandlerMethod hm);

    public void Handler();
}
