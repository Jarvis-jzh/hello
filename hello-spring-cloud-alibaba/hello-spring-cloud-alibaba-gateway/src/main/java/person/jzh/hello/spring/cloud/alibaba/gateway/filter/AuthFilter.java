package person.jzh.hello.spring.cloud.alibaba.gateway.filter;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import person.jzh.hello.spring.cloud.alibaba.gateway.response.CommonFilterResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author jzh
 * @version 1.0.0
 * @title AuthFilter
 * @date 2019/11/25 8:20
 * @description： 鉴权过滤器
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private CommonFilterResponse commonFilterResponse;

    /**
     * 要过滤的内容
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");

        if (StringUtils.isBlank(token)){
            ServerHttpResponse response = exchange.getResponse();

            // 封装错误信息
            Map<String, Object> responseData = Maps.newHashMap();
            responseData.put("code", 401);
            responseData.put("message", "非法请求");
            responseData.put("cause", "Token is empty");

            return response.writeWith(commonFilterResponse.unauthorized(exchange,responseData));
        }

//        if (StringUtils.isBlank(token)){
//            ServerHttpResponse response = exchange.getResponse();
//
//            // 封装错误信息
//            Map<String, Object> responseData = Maps.newHashMap();
//            responseData.put("code", 401);
//            responseData.put("message", "非法请求");
//            responseData.put("cause", "Token is empty");
//
//            try {
//                // 将信息转换为 JSON
//                ObjectMapper objectMapper = new ObjectMapper();
//                byte[] data = objectMapper.writeValueAsBytes(responseData);
//
//                // 输出错误信息到页面
//                DataBuffer buffer = response.bufferFactory().wrap(data);
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//                return response.writeWith(Mono.just(buffer));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }

        return chain.filter(exchange);
    }

    /**
     * 执行顺序，数据越小，优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
