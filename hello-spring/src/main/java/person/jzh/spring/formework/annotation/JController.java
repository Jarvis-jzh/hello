package person.jzh.spring.formework.annotation;

import java.lang.annotation.*;

/**
 * 页面交互
 * @author jzh
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JController {
	String value() default "";
}
