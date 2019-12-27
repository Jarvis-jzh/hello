package person.jzh.spring.formework.annotation;

import java.lang.annotation.*;

/**
 * 业务逻辑,注入接口
 * @author jzh
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JService {
	String value() default "";
}
