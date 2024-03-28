package cn.fisher.common.web.core.data;

import cn.fisher.common.web.core.param.ResponseLayout;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamError implements ResponseLayout {

    private String field;

    private Object desc;
}
