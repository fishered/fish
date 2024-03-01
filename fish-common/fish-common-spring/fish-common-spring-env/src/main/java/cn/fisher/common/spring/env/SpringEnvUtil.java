package cn.fisher.common.spring.env;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author fisher
 */
@Data
@Component
public class SpringEnvUtil {

    @Value("${spring.profiles.active}")
    private String active;

    /**
     * 当前是不是生产环境
     *
     * @return true 是 false 不是
     */
    public boolean isProd() {
        return active != null && (active.equals("prod") || active.equals("local"));
    }

    /**
     * 当前是不是生产环境
     *
     * @return true 不是 false 是
     */
    public boolean isNotProd() {
        return !isProd();
    }

}
