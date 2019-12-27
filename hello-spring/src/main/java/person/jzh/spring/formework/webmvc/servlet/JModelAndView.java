package person.jzh.spring.formework.webmvc.servlet;

import java.util.Map;

/**
 * Created by jzh on 2019/4/13.
 */
public class JModelAndView {

    private String viewName;
    private Map<String, ?> model;

    public JModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public JModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

//    public void setViewName(String viewName) {
//        this.viewName = viewName;
//    }

    public Map<String, ?> getModel() {
        return model;
    }

//    public void setModel(Map<String, ?> model) {
//        this.model = model;
//    }
}
