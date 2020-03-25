package cn.bupt.edu.base.log;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public class ElasticsearchRestClient {
    private static RestHighLevelClient restClient;
    private static ArrayBlockingQueue<JSONObject> objs = new ArrayBlockingQueue<JSONObject>(10000);

    public static void initElasticsearchRestClient(String iindex) {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "Paas,321"));
        restClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("es-rx7fl1wc.public.tencentelasticsearch.com", 9200, "https"))
                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                httpClientBuilder.disableAuthCaching();
                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            }
                        }));
        initBulkQueue(10000);
        initBulkThread(restClient, iindex);
    }

    public static void initBulkQueue(int length) {
        objs = new ArrayBlockingQueue<JSONObject>(length);
    }

    public static void initBulkThread(final RestHighLevelClient client, final String iindex) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    BulkRequest bulk = new BulkRequest();
                    for (int i = 0; i < 100; i++) {
                        IndexRequest request = new IndexRequest(iindex);
                        try {
                            request.source(objs.take());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        bulk.add(request);
                    }
                    try {
                        client.bulk(bulk, RequestOptions.DEFAULT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void insertJson(JSONObject obj) {
        objs.add(obj);
    }
}
