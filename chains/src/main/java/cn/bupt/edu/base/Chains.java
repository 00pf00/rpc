package cn.bupt.edu.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chains {
    private final static Logger logger = LoggerFactory.getLogger(Chains.class);
    public static void main(String[] args){
        SpringApplication.run(Chains.class, args);
        logger.info("Chains load springBean success");
        ChannelServer.getInstance().initChannelServer(7000);
    }
}
