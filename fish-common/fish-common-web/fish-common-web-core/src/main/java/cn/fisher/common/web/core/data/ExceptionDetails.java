package cn.fisher.common.web.core.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExceptionDetails extends EmptyData{

    private String desc;

    private List<String> details;

    public static ExceptionDetails of(Throwable throwable){
        ExceptionDetails details = new ExceptionDetails();
        details.setDesc(throwable.getMessage());
        details.setDetails(
                Arrays.stream(throwable.getStackTrace())
                        .map(e -> e.getClassName() + "@" + e.getMethodName() + ":" + e.getLineNumber())
                        .collect(Collectors.toList())
        );
        return details;
    }

}
