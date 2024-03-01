package cn.fisher.common.es.util;

import org.elasticsearch.action.search.SearchRequest;

/**
 * @author fisher
 */
public class Fields {

    public static final String[] EMPTY_NAMES = new String[]{};

    /**
     * 包含指定字段的处理
     * @param searchRequest
     * @param names 指定的动态字段数组
     * @return 搜索条件
     */
    public static SearchRequest include(SearchRequest searchRequest, String... names){
        searchRequest.source().fetchSource(names, EMPTY_NAMES);
        return searchRequest;
    }

    /**
     * 排除指定字段的处理
     */
    public static SearchRequest exclude(SearchRequest searchRequest, String... names){
        searchRequest.source().fetchSource(EMPTY_NAMES, names);
        return searchRequest;
    }


}
