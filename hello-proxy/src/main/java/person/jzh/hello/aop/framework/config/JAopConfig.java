package person.jzh.hello.aop.framework.config;

import lombok.Data;

/**
 * @author jzh
 * @version 1.0.0
 * @title JAopConfig
 * @date 2019/12/21 14:45
 * @description：
 */
@Data
public class JAopConfig {

    private String pointCut;

    private String aspectClass;

    private String aspectBefore;

    private String aspectAfter;

    private String aspectAfterThrow;

    private String aspectAfterThrowingName;
}
