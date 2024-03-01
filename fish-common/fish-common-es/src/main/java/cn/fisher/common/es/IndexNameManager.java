package cn.fisher.common.es;

import cn.fisher.common.spring.env.SpringEnvUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author fisher
 */
@Component
public class IndexNameManager {

    @Autowired
    private SpringEnvUtil springEnvUtil;

    /**
     * 处理环境下索引的名字
     * @return
     */
    public String getIndexName(String indexName){
        if (StringUtils.isBlank(indexName)){
            throw new RuntimeException("指定索引名是空的！");
        }

        if (springEnvUtil.isProd()){
            return indexName;
        }
        return springEnvUtil.getActive() + "_" + indexName;
    }

}
