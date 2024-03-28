package cn.fisher.common.web.core.param.page;

import cn.fisher.common.web.core.param.RequestLayout;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 分页参数
 */
@Data
public class PageParam implements RequestLayout {

    @Schema(description = "页位置")
    @Min(value = 1, message = "页位置不能小于1")
    private Integer index;

    @Schema(description = "页大小")
    @Min(value = 1, message = "页大小不能小于1")
    private Integer size;

}
