package person.jzh.rpc.factory;

import person.jzh.rpc.annotation.ServiceMapped;
import person.jzh.rpc.dto.RPCTransformObj;
import person.jzh.rpc.net.BIOClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jzh
 * @version 1.0.0
 * @title RPCInvocationHandler
 * @date 2019/12/20 16:45
 * @description：
 */
public class RPCInvocationHandler implements InvocationHandler {
    /**
     * 代码的增强
     * 1、保护我们的目标对象
     * 2、增强我们的目标对象
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 网络请求的部分内容

        RPCTransformObj rpcTransformObj = new RPCTransformObj();

        rpcTransformObj.setParams(args);
        rpcTransformObj.setMethodName(method.getName());
        ServiceMapped mapped = method.getDeclaringClass().getAnnotation(ServiceMapped.class);
        rpcTransformObj.setFullClassPath(mapped.value());

        // 网络编程，完成服务端的调用
        return BIOClient.callRemoteService(rpcTransformObj, "localhost", 7777);
    }
}
