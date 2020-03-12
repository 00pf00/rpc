package cn.bupt.edu.consumer;

import cn.bupt.edu.consumer.channel.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Consumer {
    public static void main(String[] args) {
        Client.Start();
        SpringApplication.run(Consumer.class, args);

    }
}
