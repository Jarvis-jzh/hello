package person.jzh.hello.aop.framework.support;

import person.jzh.hello.aop.framework.config.JAopConfig;
import person.jzh.hello.aop.framework.proxy.JClassLoader;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jzh
 * @version 1.0.0
 * @title JAdvisedSupport
 * @date 2019/12/21 14:27
 * @description：
 */
public class JAdvisedSupport {

    private Pattern pointCutClassPattern;

    private Object target;

    private Class<?> targetClass;

    private JClassLoader classLoader;

    private JAopConfig config;

    private transient Map<Method, Map<String, JAdvices>> methodCache;

    public JAdvisedSupport(JAopConfig config) {
        this.config = config;
        this.classLoader = new JClassLoader();
    }

    public JClassLoader getClassLoader() {
        return this.classLoader;
    }

    public Class<?> getTargetClass() {
        return this.targetClass;
    }

    public Object getTarget() {
        return this.target;
    }

    public Map<String, JAdvices> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) throws Exception {
        Map<String, JAdvices> cached = methodCache.get(method);
        if (cached == null){
            Method m = targetClass.getMethod(method.getName(), method.getParameterTypes());
            cached = methodCache.get(m);

            // 底层逻辑，对代理方法进行一个兼容处理
            this.methodCache.put(m, cached);
        }
        return cached;
    }

    public void setTargetClass(Class<?> clazz) {
        this.targetClass = clazz;
        parse();
    }

    private void parse() {
//        String pointCut = config.getPointCut()
//                .replaceAll("\\.", "\\\\")
//                .replaceAll("\\\\+\\*", ".*")
//                .replaceAll("\\\\", "\\.")
//                .replaceAll("\\(", "\\\\(")
//                .replaceAll("\\)", "\\\\)");
//        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 2);

        String pointCut = config.getPointCut()
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");

        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        pointCutClassPattern = Pattern.compile("class " + pointCutForClassRegex.substring(
                pointCutForClassRegex.lastIndexOf(" ") + 1
        ));
        try {
            methodCache = new HashMap<>();
            Pattern pattern = Pattern.compile(pointCut);

            Class<?> aspectClass = Class.forName(this.config.getAspectClass());
            Map<String, Method> aspectMethods = new HashMap<>(aspectClass.getMethods().length);
            for (Method method : aspectClass.getMethods()) {
                aspectMethods.put(method.getName(), method);
            }

            for (Method method : this.targetClass.getMethods()) {
                String methodString = method.toString();
                if (methodString.contains("throws")) {
                    methodString = methodString.substring(0, methodString.lastIndexOf("throws")).trim();
                }

                Matcher matcher = pattern.matcher(methodString);
                if (matcher.matches()) {
                    // 执行器链
                    Map<String, JAdvices> advices = new HashMap<String, JAdvices>();
                    // 把每一个方法包装成 MethodIterceptor
                    // before
                    if(!(null == config.getAspectBefore() || "".equals(config.getAspectBefore()))){
                        // 创建一个Advice
                        advices.put("before", new JAdvices(aspectClass.newInstance(), aspectMethods.get(config.getAspectBefore())));
                    }

                    // after
                    if(!(null == config.getAspectAfter() || "".equals(config.getAspectAfter()))){
                        // 创建一个Advice
                        advices.put("after", new JAdvices(aspectClass.newInstance(), aspectMethods.get(config.getAspectAfter())));
                    }

                    // afterThrowing
                    if(!(null == config.getAspectAfterThrow() || "".equals(config.getAspectAfterThrow()))){
                        // 创建一个Advice
                        JAdvices throwingAdvice = new JAdvices(aspectClass.newInstance(), aspectMethods.get(config.getAspectAfterThrow()));
                        throwingAdvice.setThrowName(config.getAspectAfterThrowingName());
                        advices.put("afterThrow", throwingAdvice);
                    }
                    methodCache.put(method, advices);
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void setTarget(Object Object) {
        this.target = Object;
    }
}
