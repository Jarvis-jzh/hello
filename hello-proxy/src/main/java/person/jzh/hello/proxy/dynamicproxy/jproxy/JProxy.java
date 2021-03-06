package person.jzh.hello.proxy.dynamicproxy.jproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

/**
 * @author jzh
 * @version 1.0.0
 * @title JProxy
 * @date 2019/12/21 10:33
 * @description：
 */
public class JProxy {

    public static final String ln = "\r\n";

    public static Object newProxyInstance(JClassLoader classLoader, Class<?>[] interfaces, JInvocationHandler h) {
        try {
            // 1、动态生成源代码  .java文件
            String src = generateSrc(interfaces);

            // 2、java文件输出磁盘
            String filePath = JProxy.class.getResource("").getPath();
            File f = new File(filePath + "$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();

            // 3、把生成的.java文件编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, Charset.defaultCharset());
            Iterable iterable = manager.getJavaFileObjects(f);

            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();

            // 4、编译生成的.class文件加载到JVM中来
            Class<?> proxyClass = classLoader.findClass("$Proxy");
            Constructor c = proxyClass.getConstructor(JInvocationHandler.class);
            f.delete();

            // 5、返回字节码重组后的新的代理对象
            return c.newInstance(h);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append("package person.jzh.hello.proxy.dynamicproxy.jproxy;" + ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);
        sb.append("JInvocationHandler h;" + ln);
        sb.append("public $Proxy0(JInvocationHandler h) {" + ln);
        sb.append("this.h = h;" + ln);
        sb.append("}" + ln);

        for (Method m : interfaces[0].getMethods()) {
            Class<?>[] params = m.getParameterTypes();

            StringBuffer paramNames = new StringBuffer();
            StringBuffer paramValues = new StringBuffer();
            StringBuffer paramClasses = new StringBuffer();

            for (int i = 0; i < params.length; i++) {
                Class<?> clazz = params[i];
                String type = clazz.getName();
                String paramName = thLowerFirstCase(clazz.getSimpleName());
                paramNames.append(type + " " + paramName);
                paramValues.append(paramName);
                paramClasses.append(clazz.getName() + ".class");
                if (i > 0 && i < params.length - 1) {
                    paramNames.append(",");
                    paramClasses.append(",");
                    paramValues.append(",");
                }
            }

            sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(" + paramNames.toString() + ") {" + ln);
            sb.append("try{ " + ln);
            sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\", new Class[]{ " + paramClasses.toString() + "});" + ln);
            sb.append((hasReturnValue(m.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this, m, new Object[]{});"));
            sb.append("} catch (Error _ex) {} catch (Throwable e) {" + ln);
            sb.append("throw new UndeclaredThrowableException(e);" + ln);
            sb.append("}}");
        }
        sb.append("}");
        return sb.toString();
    }

    private static String getCaseCode(String s) {
        return s;
    }

    private static boolean hasReturnValue(Class<?> returnType) {
        return !"void".equals(returnType.getName());
    }

    private static String thLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
