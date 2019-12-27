package person.jzh.spring.formework.webmvc.servlet;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.jzh.spring.formework.annotation.JController;
import person.jzh.spring.formework.annotation.JRequestMapping;
import person.jzh.spring.formework.context.JApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jzh
 * @version 1.0.0
 * @title JDispatcherServlet
 * @date 2019/12/4 17:15
 * @description：
 */
@Slf4j(topic = "JDispatcherServlet")
public class JDispatcherServlet extends HttpServlet {

    // private static Logger log = LoggerFactory.getLogger("JDispatcherServlet");

    // 与 web.xml 中的 param-name 的值一致
    private final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private JApplicationContext context;

    private List<JHandlerMapping> handlerMappings = new ArrayList<JHandlerMapping>();

    private Map<JHandlerMapping, JHandlerAdapter> handlerAdapters = new HashMap<JHandlerMapping, JHandlerAdapter>(16);

    private List<JViewResolver> viewResolvers = new ArrayList<JViewResolver>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.doDispatch(req, resp);
        } catch (Exception e) {
            // 如果匹配过程出现异常，将异常信息打印出去
            //processDispatchResult(req, resp, new JModelAndView("404"));
            resp.getWriter().write("500 Exception,Details:\r\n" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", "").replaceAll(",\\s", "\r\n"));
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1、通过从 request 中拿到 URL，去匹配一个 HandlerMapping
        // Handler：把 URL 和 Method 进行一对一的关联
        // Method 在 Spring 里叫 Handler
        JHandlerMapping handler = getHandler(req);

        if (handler == null) {
            processDispatchResult(req, resp, new JModelAndView("404"));
            return;
        }

        // 2、准备调用前的参数
        // HandlerAdapter：将 Handler 的形参与 Request 的实参进行一对一的匹配
        JHandlerAdapter ha = getHandlerAdapter(handler);

        // 3、真正的调用方法，返回 ModelAndView 存储了要传到页面上的值，和页面的模板的名称
        JModelAndView mv = ha.handle(req, resp, handler);

        // 4、这一步才是真正的输出
        processDispatchResult(req, resp, mv);
    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, JModelAndView mv) throws Exception {
        // 把给我的 ModelAndView 变成一个 HTML、OuputStream、json、freemark、veolcity
        // ContextType
        if (null == mv) {
            return;
        }

        // 如果 ModelAndView 不为null，怎么办？
        if (this.viewResolvers.isEmpty()) {
            return;
        }

        for (JViewResolver viewResolver : this.viewResolvers) {
            JView view = viewResolver.resolverViewName(mv.getViewName(), null);
            view.render(mv.getModel(), req, resp);
            // TODO
            return;
        }
    }

    private JHandlerAdapter getHandlerAdapter(JHandlerMapping handler) {
        if (this.handlerAdapters == null) {
            return null;
        }
        JHandlerAdapter ha = this.handlerAdapters.get(handler);
        if (ha.supports(handler)) {
            return ha;
        }
        return null;
    }

    private JHandlerMapping getHandler(HttpServletRequest req) throws Exception {
        if (this.handlerMappings.isEmpty()) {
            return null;
        }
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (JHandlerMapping handler : this.handlerMappings) {
            try {
                Matcher matcher = handler.getPattern().matcher(url);
                // 如果没有匹配上，继续下一个匹配
                if (!matcher.matches()) {
                    continue;
                }
                return handler;
            } catch (Exception e) {
                throw e;
            }
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1、初始化ApplicationContext
        context = new JApplicationContext(config.getInitParameter(CONTEXT_CONFIG_LOCATION));
        System.out.println("JApplicationContext 初始完成 context");
        // 2、初始化Spring MVC 九大组件
        initStrategies(context);
    }

    /**
     * 初始化策略
     *
     * @param context
     */
    protected void initStrategies(JApplicationContext context) {

        // 多文件上传的组件
        initMultipartResolver(context);

        // 初始化本地语言环境
        initLocaleResolver(context);

        // 初始化模板处理器
        initThemeResolver(context);


        // handlerMapping*
        initHandlerMappings(context);

        // 初始化参数适配器*
        initHandlerAdapters(context);

        // 初始化异常拦截器
        initHandlerExceptionResolvers(context);

        // 初始化视图预处理器
        initRequestToViewNameTranslator(context);

        // 初始化视图转换器*
        initViewResolvers(context);

        // 初始化参数缓存器
        initFlashMapManager(context);
    }

    private void initHandlerExceptionResolvers(JApplicationContext context) {

    }

    // 处理者适配器
    private void initHandlerAdapters(JApplicationContext context) {

        // 把一个 request 请求变成一个 handler，参数都是字符串的，自动配置 handler 中的形参

        // 可想而知，他要拿到 HandlerMapping 才能干活
        // 意味着，有几个 HandlerMapping 就有几个 HandlerAdapter
        for (JHandlerMapping handlerMapping : this.handlerMappings) {
            this.handlerAdapters.put(handlerMapping, new JHandlerAdapter());
        }
    }

    private void initRequestToViewNameTranslator(JApplicationContext context) {
    }

    private void initViewResolvers(JApplicationContext context) {
        // 拿到模版的存放目录
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);

        for (File template : templateRootDir.listFiles()) {
            // 这里主要是为了兼容多模板，所有模仿Spring用List保存
            // 在我写的代码中简化了，其实只有需要一个模板就可以搞定
            // 只是为了仿真，所有还是搞了个List
            this.viewResolvers.add(new JViewResolver(templateRoot));
        }

    }

    private void initFlashMapManager(JApplicationContext context) {

    }

    private void initHandlerMappings(JApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        try {
            for (String beanName : beanNames) {
                Object controller = context.getBean(beanName);
                Class<?> clazz = controller.getClass();
                if (!clazz.isAnnotationPresent(JController.class)) {
                    continue;
                }

                String baseUrl = "";

                // 获取Controller的url配置
                if (clazz.isAnnotationPresent(JRequestMapping.class)) {
                    JRequestMapping requestMapping = clazz.getAnnotation(JRequestMapping.class);
                    baseUrl = requestMapping.value();
                }

                // 获取Method的url配置
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {

                    // 没有RequestMapping注解的直接忽略
                    if (!method.isAnnotationPresent(JRequestMapping.class)) {
                        continue;
                    }

                    // 映射URL
                    JRequestMapping requestMapping = method.getAnnotation(JRequestMapping.class);

                    String regex = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("^\\.\\*", ".*")).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);
                    JHandlerMapping handlerMapping = new JHandlerMapping(pattern, controller, method);

                    if (handlerMappings.contains(handlerMapping)){
                        continue;
                    }
                    handlerMappings.add(handlerMapping);
                    log.info("Mapped {}, {}", regex, method);
//                    System.out.println("Mapped " + regex + ", " + method);
                }
                handlerMappings.sort((x, y)->{
                    return x.getClass().getName().compareTo(y.getClass().getName());
                });
//                handlerMappings.forEach((item) -> {
//                    log.info("Mapped {}, {}", item.get);
//                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLocaleResolver(JApplicationContext context) {
    }

    private void initThemeResolver(JApplicationContext context) {
    }

    private void initMultipartResolver(JApplicationContext context) {
    }

}
