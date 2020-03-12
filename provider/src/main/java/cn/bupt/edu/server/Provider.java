package cn.bupt.edu.server;

import cn.bupt.edu.server.channel.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Provider {
    private final static Logger logger = LoggerFactory.getLogger(Provider.class);

    public static void main(String[] args) {
        SpringApplication.run(Provider.class, args);
        logger.info("Provider load springBean success");
        Runnable rc = new Runnable() {
            @Override
            public void run() {
                Client.Start();
            }
        };
        new Thread(rc).start();
        logger.info("Provider start clinet");
        ChannelServer.getInstance().initChannelServer();
    }
}
