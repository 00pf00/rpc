package cn.bupt.edu.analysis.util;

import cn.bupt.edu.base.log.ElasticsearchRestClient;
import cn.bupt.edu.base.util.LogInfo;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.Map;

public class Search {
    public static void search(RestHighLevelClient client, String index, String attr1, String attr2) {
        MatchQueryBuilder match = QueryBuilders.matchQuery(LogInfo.UUID, "");
        TermsQueryBuilder tsb = QueryBuilders.termsQuery(LogInfo.LOGO, attr1, attr2);
        BoolQueryBuilder bl = QueryBuilders.boolQuery();
        bl.filter(tsb);
        bl.mustNot(match);
        SearchSourceBuilder search = new SearchSourceBuilder();
        search.fetchSource(new String[]{LogInfo.UUID, LogInfo.LOGO, LogInfo.TIME}, new String[]{});
        search.query(bl);
        search.size(30000);
        search.sort(LogInfo.UUID, SortOrder.ASC);
//        search.sort(LogInfo.TIME,SortOrder.ASC);
        SearchRequest req = new SearchRequest(index);
        req.source(search);
        SearchResponse resp = null;
        try {
            resp = client.search(req, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String attr = attr2 + "-" + attr1;
        Long time = 0L;
        String uuid = "";
        for (int i = 0; i < resp.getHits().getHits().length; i++) {
            Map<String, Object> map = resp.getHits().getHits()[i].getSourceAsMap();
            System.out.println(map.get(LogInfo.UUID).toString());
            if (time == 0L || !uuid.equals(map.get(LogInfo.UUID).toString())) {
                time = (Long) map.get(LogInfo.TIME);
                uuid = map.get(LogInfo.UUID).toString();
            } else {
                if (map.get(LogInfo.LOGO).equals(attr1) && uuid.equals(map.get(LogInfo.UUID).toString())) {
                    JSONObject json = new JSONObject();
                     json.put(attr, time - Long.valueOf(String.valueOf( map.get(LogInfo.TIME))).longValue());
                    ElasticsearchRestClient.insertJson(json);


                }
                if (map.get(LogInfo.LOGO).equals(attr2) && uuid.equals(map.get(LogInfo.UUID).toString())) {
                    JSONObject json = new JSONObject();
                    json.put(attr, Long.valueOf(String.valueOf( map.get(LogInfo.TIME))).longValue() - time);
                    ElasticsearchRestClient.insertJson(json);
                }
                uuid = "";
                time = 0L;
            }

        }
    }
}
