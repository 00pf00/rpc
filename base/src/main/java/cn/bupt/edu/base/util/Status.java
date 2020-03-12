package cn.bupt.edu.base.util;

public class Status {
    public static int STATUS_DISCONNECT = 507;//连接断开
    public static int STATUS_REJECT = 508; //Server端队列已满，请求被拒绝
    public static int STATUS_NOTINIT = 509; //Server端处理请求的线程池没有初始化
    public static int STATUS_HEARTBEAT = 600;
}
