package person.jzh.hello.rabbit.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jzh
 * @version 1.0.0
 * @title RabbitConfig
 * @date 2019/12/23 21:15
 * @description：
 */
@Configuration
public class RabbitConfig {
    @Bean("vipFirstQueue")
    public Queue getFirstQueue(){
        return null;
    }
}
