package cn.bupt.edu.base;

import cn.bupt.edu.base.channel.RpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Consumer {
    public static void main(String[] args) {
        SpringApplication.run(Consumer.class, args);
        RpcClient.Start();
    }
}
