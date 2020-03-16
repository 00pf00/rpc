package cn.bupt.edu.analysis;

import cn.bupt.edu.analysis.restclient.RestClient;
import cn.bupt.edu.analysis.util.Search;
import cn.bupt.edu.base.log.ElasticsearchRestClient;
import cn.bupt.edu.base.util.LogInfo;

public class Analysis {
    public static void main(String[] args) {
        RestClient.initElasticsearchRestClient();
        ElasticsearchRestClient.initBulkQueue(20000);
        ElasticsearchRestClient.initBulkThread(RestClient.client, "analysis");
        //Search.search(RestClient.client,"chains", LogInfo.SERVER_PROCESSING_METHD_START,LogInfo.SERVER_PROCESSING_METHD_END);
        //Search.search(RestClient.client,"chains", LogInfo.SERVER_TASK_SATRT,LogInfo.SERVER_TASK_END);
        //Search.search(RestClient.client,"chains",LogInfo.SERVER_DECODING_END,LogInfo.SERVER_TASK_SATRT);
        //Search.search(RestClient.client,"chains",LogInfo.SERVER_ENCODING_START,LogInfo.SERVER_ENCODING_END);
        Search.search(RestClient.client, "chains", LogInfo.SERVER_TASK_END, LogInfo.SERVER_ENCODING_START);

    }
}
