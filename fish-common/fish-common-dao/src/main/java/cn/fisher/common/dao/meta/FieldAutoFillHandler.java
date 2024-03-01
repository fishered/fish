package cn.fisher.common.dao.meta;

import cn.fisher.common.dao.entity.BaseEntity;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author fisher
 */
@Component
public class FieldAutoFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        fillStrategy(metaObject, BaseEntity.Fields.cTime, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        fillStrategy(metaObject, BaseEntity.Fields.uTime, LocalDateTime.now());
    }
}
