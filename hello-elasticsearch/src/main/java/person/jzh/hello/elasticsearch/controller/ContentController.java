package person.jzh.hello.elasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import person.jzh.hello.elasticsearch.service.ContentService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/27 16:09
 * @description
 */
@RestController
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/parse/{keyword}")
    public Boolean parse(@PathVariable("keyword") String keyword) throws Exception {
        return contentService.parseContent(keyword);
    }

    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public List<Map<String, Object>> searchPage(
            @PathVariable("keyword") String keyword,
            @PathVariable("pageNo") int pageNo,
            @PathVariable("pageSize") int pageSize) throws IOException {
        return contentService.searchPageHighlightBuilder(keyword, pageNo, pageSize);
    }
}
