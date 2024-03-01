package cn.fisher.common.auth.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 忽略权限认证
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Pass {
}
