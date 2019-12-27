package person.jzh.spring.demo.action;

import lombok.extern.slf4j.Slf4j;
import person.jzh.spring.demo.service.IModifyService;
import person.jzh.spring.demo.service.IQueryService;
import person.jzh.spring.formework.annotation.JAutowired;
import person.jzh.spring.formework.annotation.JController;
import person.jzh.spring.formework.annotation.JRequestMapping;
import person.jzh.spring.formework.annotation.JRequestParam;
import person.jzh.spring.formework.webmvc.servlet.JModelAndView;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jzh
 * @version 1.0.0
 * @title MyAction
 * @date 2019/12/3 9:26
 * @description：
 */
@Slf4j
@JController
@JRequestMapping("/web")
public class MyAction {

    @JAutowired
    IQueryService queryService;
    @JAutowired
    IModifyService modifyService;

    @JRequestMapping("/query.json")
    public JModelAndView query(HttpServletRequest request, HttpServletResponse response,
                               @JRequestParam("name") String name) {
        String result = queryService.query(name);
        return out(response, result);
    }

    @JRequestMapping("/add*.json")
    public JModelAndView add(HttpServletRequest request, HttpServletResponse response,
                             @JRequestParam("name") String name, @JRequestParam("addr") String addr) {
        String result = null;
        try {
            log.info("name => {}, addr => {}", name, addr);
            result = modifyService.add(name, addr);
            return out(response, result);
        } catch (Exception e) {
//			e.printStackTrace();
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("detail", e.getMessage());
//			System.out.println(Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]",""));
            model.put("stackTrace", Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", ""));
//            model.put("stackTrace", "");
            return new JModelAndView("500", model);
        }

    }

    @JRequestMapping("/remove.json")
    public JModelAndView remove(HttpServletRequest request, HttpServletResponse response,
                                @JRequestParam("id") Integer id) {
        String result = modifyService.remove(id);
        return out(response, result);
    }

    @JRequestMapping("/edit.json")
    public JModelAndView edit(HttpServletRequest request, HttpServletResponse response,
                              @JRequestParam("id") Integer id,
                              @JRequestParam("name") String name) {
        String result = modifyService.edit(id, name);
        return out(response, result);
    }


    private JModelAndView out(HttpServletResponse resp, String str) {
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
