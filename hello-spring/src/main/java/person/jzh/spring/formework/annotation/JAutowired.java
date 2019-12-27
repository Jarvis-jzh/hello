package person.jzh.spring.formework.annotation;

import java.lang.annotation.*;


/**
 * 自动注入
 * @author jzh
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JAutowired {
	String value() default "";
}
