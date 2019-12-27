package person.jzh.hello.rabbit.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author jzh
 * @version 1.0.0
 * @title FirstConsumer
 * @date 2019/12/23 21:19
 * @description：
 */
@Component
@PropertySource("classpath:mq.properties")
@RabbitListener(queues = "", containerFactory = "rabbitListenerContainerFactory")
public class FirstConsumer {

    StringBuffer sb = new StringBuffer("1");
//    @RabbitHandler
//    public void process(@Payload Merchant merchant){
//        System.out.println("First Queue received msg : " + merchant);
//    }
}
