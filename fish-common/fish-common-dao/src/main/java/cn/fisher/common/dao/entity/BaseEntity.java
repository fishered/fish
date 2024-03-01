package cn.fisher.common.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author fisher
 */
@Data
@FieldNameConstants
public class BaseEntity<T> implements Serializable {

    /**
     * 主键
     */
    @TableId
    protected Long id;

    /**
     * extend
     */
    protected String extendData;

    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime cTime;

    @TableField(fill = FieldFill.INSERT)
    protected T cUser;

    @TableField(fill = FieldFill.UPDATE)
    protected LocalDateTime uTime;

    @TableField(fill = FieldFill.UPDATE)
    protected T uUser;

}
