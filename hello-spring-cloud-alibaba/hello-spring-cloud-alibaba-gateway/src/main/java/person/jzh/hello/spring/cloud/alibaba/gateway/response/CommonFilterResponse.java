package person.jzh.hello.spring.cloud.alibaba.gateway.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author jzh
 * @version 1.0.0
 * @title CommonFilterResponse
 * @date 2019/11/25 8:37
 * @description：
 */
@Component
public class CommonFilterResponse {

    public Publisher commmonResponse(ServerWebExchange exchange, Map<String, Object> responseData, HttpStatus httpStatus){
        ServerHttpResponse response = exchange.getResponse();

        // 将信息转换为 JSON
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] data = new byte[0];
        try {
            data = objectMapper.writeValueAsBytes(responseData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 输出错误信息到页面
        DataBuffer buffer = response.bufferFactory().wrap(data);
        response.setStatusCode(httpStatus);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return Mono.just(buffer);
    }

    /**
     * 没有权限
     *
     * @param exchange
     * @param responseData
     * @return
     */
    public Publisher unauthorized(ServerWebExchange exchange, Map<String, Object> responseData){
        return commmonResponse(exchange, responseData, HttpStatus.UNAUTHORIZED);
    }

}
