package person.jzh.hello.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jzh
 * @version 1.0.0
 * @title ContainerSingleton
 * @date 2020/1/6 18:12
 * @description： 容器单例实现（例：IOC）
 */
public class ContainerSingleton {

    private ContainerSingleton() {
    }

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    public static Object getInstance(String className){
        // 加快速度
        if (ioc.containsKey(className)){
            return ioc.get(className);
        }
        synchronized (ioc) {
            Object obj = null;
            try {
                obj = Class.forName(className).newInstance();
                ioc.put(className, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

//    public static Object getInstance(String className) {
//        synchronized (ioc) {
//            if (!ioc.containsKey(className)) {
//                Object obj = null;
//                try {
//                    obj = Class.forName(className).newInstance();
//                    ioc.put(className, obj);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return obj;
//            } else {
//                return ioc.get(className);
//            }
//        }
//    }
}
