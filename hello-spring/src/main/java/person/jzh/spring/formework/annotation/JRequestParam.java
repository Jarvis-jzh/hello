package person.jzh.spring.formework.annotation;

import java.lang.annotation.*;

/**
 * 请求参数映射
 * @author jzh
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JRequestParam {
	
	String value() default "";
	
	boolean required() default true;

}
