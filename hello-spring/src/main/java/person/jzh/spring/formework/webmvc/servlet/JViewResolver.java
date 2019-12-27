package person.jzh.spring.formework.webmvc.servlet;

import java.io.File;
import java.util.Locale;

/**
 * @author jzh
 * @version 1.0.0
 * @title JViewResolver
 * @date 2019/12/9 10:35
 * @description：
 */
public class JViewResolver {

    private final String DEFAULT_TEMPLATE_SUFFX = ".html";

    private File templateRootDir;

//    private String viewName;

    public JViewResolver(String templateRoot) {
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        templateRootDir = new File(templateRootPath);
    }

    public JView resolverViewName(String viewName, Locale locale) throws Exception {

        if (null == viewName || "".equals(viewName.trim())){
            return null;
        }

        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFX) ? viewName : (viewName + DEFAULT_TEMPLATE_SUFFX);
        File template = new File((templateRootDir.getPath() + "/" + viewName).replaceAll("//+", "/"));
        return new JView(template);
    }

//    public String getViewName() {
//        return viewName;
//    }
}
