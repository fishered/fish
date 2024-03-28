package cn.fisher.common.web.mongo.adapter;

import cn.fisher.common.exception.BizException;
import cn.fisher.common.web.core.param.page.TimePageParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class TimePageParamAdapter {

    /**
     * 获取查询时间
     * @param timePageParam
     * @param fieldDesc
     * @return
     */
    public static Query getQuery(TimePageParam timePageParam, String fieldDesc){
        if (timePageParam == null) {
            throw new BizException("查询参数不能是空的");
        }

        Query query = new Query();
        Criteria timeCriteria = Criteria.where(fieldDesc);
        if (timePageParam.getStartTime() != null) {
            timeCriteria.gte(timePageParam.getStartTime());
        }
        if (timePageParam.getEndTime() != null) {
            timeCriteria.lte(timePageParam.getEndTime());
        }

        if (timePageParam.isTimeQuery()){
            query.addCriteria(timeCriteria);
        }
        query.with(PageRequest.of(timePageParam.getIndex() - 1, timePageParam.getSize()));
        return query;
    }

}
