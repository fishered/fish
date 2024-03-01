package cn.fisher.common.mongo;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.core.query.Criteria;

import java.io.Serializable;

/**
 * @author fisher
 */
@Data
@FieldNameConstants
public class BaseDocument implements Serializable {

    public static final String ID = "_id";

    /**
     * 主键
     */
    @MongoId
    private String id;

    /**
     * bson 获取 objectId
     * @param id
     * @return
     */
    public static BsonValue getBsonObjectId(String id){
        return new BsonObjectId(new ObjectId(id));
    }

    /**
     * 获取objectId
     * @return
     */
    public BsonValue resolveObjectId(){
        return getBsonObjectId(id);
    }

    /**
     * 得到ObjectId
     * @return
     */
    public Criteria resolveObjectIdCriteria(){
        return Criteria.where(ID).is(resolveObjectId());
    }



}
