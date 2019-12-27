package person.jzh.rpc.dispatch;

import person.jzh.rpc.api.impl.UserServiceImpl;
import person.jzh.rpc.dto.RPCTransformObj;
import person.jzh.rpc.net.RPCThreadProcessor;

import java.lang.reflect.Method;

/**
 * @author jzh
 * @version 1.0.0
 * @title ServiceDispatch
 * @date 2019/12/20 16:11
 * @description：
 */
public class ServiceDispatch {
    public static Object dispatch(Object reqObj) {
        // 反射方式
        // 1、类的全路径名称
        // 2、调用的方法名称
        // 3、调用方法的
        // 4、调用方法的参数值的列表
        RPCTransformObj rpcTransformObj = (RPCTransformObj) reqObj;
        String fullClassPath = rpcTransformObj.getFullClassPath();
        String methodName = rpcTransformObj.getMethodName();
        Object[] params = rpcTransformObj.getParams();
        Class[] types = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            types[i] = params[i].getClass();
        }

        Object respObject = null;
        try {
            Class<?> clazz = Class.forName(fullClassPath);
            Method method = clazz.getDeclaredMethod(methodName, types);
            respObject = method.invoke(clazz.newInstance(), params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return respObject;
    }
}
