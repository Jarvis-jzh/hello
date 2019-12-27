package person.jzh.hello.spring.cloud.alibaba.rocketmq.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author jzh
 * @version 1.0.0
 * @title RocketMQProviderApplication
 * @date 2019/11/25 19:04
 * @description：
 */
@SpringBootApplication
@EnableBinding({Source.class})
public class RocketMQProviderApplication implements CommandLineRunner {

    @Autowired
    private MessageChannel output;

    public static void main(String[] args) {
        SpringApplication.run(RocketMQProviderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello");
        output.send(MessageBuilder.withPayload("Hello RockeMQ").build());
    }
}
