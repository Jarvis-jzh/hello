package person.jzh.rpc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jzh
 * @version 1.0.0
 * @title ServiceMapped
 * @date 2019/12/20 16:58
 * @description：
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceMapped {
    String value() default "";
}
