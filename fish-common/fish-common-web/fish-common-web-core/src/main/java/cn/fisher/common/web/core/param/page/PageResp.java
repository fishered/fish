package cn.fisher.common.web.core.param.page;

import cn.fisher.common.web.core.param.ResponseLayout;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * 分页返回数据
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResp<T> implements ResponseLayout {
    /**
     * 页位置
     */
    @Schema(description = "页位置")
    private Integer index;
    /**
     * 总数量
     */
    @Schema(description = "总数量")
    private Long count;
    /**
     * 数据
     */
    @Schema(description = "分页数据")
    private List<T> data;

    /**
     * 简化构造
     *
     * @param index    索引
     * @param <T>      类型
     * @return 数据列表
     */
    public static <T> PageResp<T> empty(Integer index) {
        return new PageResp<>(index, 0L, Collections.emptyList());
    }

    /**
     * 简化构造
     *
     * @param index    索引
     * @param dataList 数据列表
     * @param <T>      类型
     * @return 数据列表
     */
    public static <T> PageResp<T> of(Integer index, List<T> dataList) {
        return new PageResp<>(index, 0L, dataList);
    }

    /**
     * 简化构造
     *
     * @param index    索引
     * @param count    总数
     * @param dataList 数据列表
     * @param <T>      类型
     * @return 数据列表
     */
    public static <T> PageResp<T> of(Integer index, Long count, List<T> dataList) {
        return new PageResp<>(index, count, dataList);
    }

}
