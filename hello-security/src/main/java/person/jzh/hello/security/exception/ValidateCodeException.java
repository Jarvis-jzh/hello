package person.jzh.hello.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/26 22:46
 * @description
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
