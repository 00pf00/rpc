package cn.bupt.edu.chains;

import cn.bupt.edu.base.log.ElasticsearchRestClient;
import cn.bupt.edu.server.ChannelServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChainServer {
    public static void main(String[] args) {
        ElasticsearchRestClient.initElasticsearchRestClient("chains");
        SpringApplication.run(ChainServer.class, args);
        ChannelServer.getInstance().initChannelServer(7000);
    }
}
