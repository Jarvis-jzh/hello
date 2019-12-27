package person.jzh.spring.formework.annotation;

import java.lang.annotation.*;

/**
 * 请求url
 * @author jzh
 *
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JRequestMapping {
	String value() default "";
}
