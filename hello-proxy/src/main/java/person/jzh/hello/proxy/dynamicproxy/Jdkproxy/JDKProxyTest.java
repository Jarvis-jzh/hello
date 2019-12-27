package person.jzh.hello.proxy.dynamicproxy.Jdkproxy;

import person.jzh.hello.proxy.dynamicproxy.Jdkproxy.Girl;
import person.jzh.hello.proxy.Person;
import person.jzh.hello.proxy.dynamicproxy.Jdkproxy.JDKMeipo;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Method;

/**
 * @author jzh
 * @version 1.0.0
 * @title JDKProxyTest
 * @date 2019/12/21 8:51
 * @description：
 */
public class JDKProxyTest {
    public static void main(String[] args) throws Exception {
        Object obj = new JDKMeipo().getInstance(new Girl());
        Method method = obj.getClass().getMethod("findLove", null);
        method.invoke(obj);

        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
        FileOutputStream os = new FileOutputStream("E://$Proxy0.class");
        os.write(bytes);
        os.flush();

        if (os != null) {
            os.close();
        }
    }
}
