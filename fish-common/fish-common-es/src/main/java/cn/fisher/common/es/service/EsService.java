package cn.fisher.common.es.service;

import cn.fisher.util.JsonUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author fisher
 */
@Service
public class EsService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 查找数据
     * @param searchRequest 搜索请求
     * @param targetClass 目标类型
     * @return
     * @param <T> 搜索结果
     */
    public <T> List<T> searchData(SearchRequest searchRequest, Class<T> targetClass){
        isSupportEs(searchRequest);

        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();

            List<T> res = new ArrayList<>();
            for (SearchHit hit : hits){
                res.add(JsonUtil.anyToData(hit.getSourceAsString(), targetClass));
            }
            return res;
        } catch (IOException e) {
            throw new RuntimeException(String.format("ES搜索异常：{%s}", e.getMessage()));
        }
    }

    /**
     * 搜索单条数据
     * @param searchRequest 搜索条件
     * @param targetClass 目标类型
     * @return 搜索结果
     * @param <T>
     */
    public <T> T searchOneData(SearchRequest searchRequest, Class<T> targetClass){
        isSupportEs(searchRequest);

        searchRequest.source().size(1);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();

            if (hits.length > 1){
                throw new RuntimeException("ES搜索一条数据，发现多条！");
            }
            List<T> res = null;
            if (hits.length > 0){
                res = new ArrayList<>();
            }
            for (SearchHit hit : hits){
                res.add(JsonUtil.anyToData(hit.getSourceAsString(),targetClass));
            }
            if (CollectionUtils.isEmpty(res)){
                return null;
            }
            return res.get(0);
        } catch (IOException e) {
            throw new RuntimeException(String.format("ES搜索异常：{%s}", e.getMessage()));
        }
    }


    private void isSupportEs(SearchRequest searchRequest){
        if (restHighLevelClient == null){
            throw new RuntimeException("查询失败，没有对应的ES客户端！");
        }
        if (searchRequest == null){
            throw new RuntimeException("查询失败，没有对应的查询条件！");
        }
    }

}
