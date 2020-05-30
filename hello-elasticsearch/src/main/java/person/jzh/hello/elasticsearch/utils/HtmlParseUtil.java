package person.jzh.hello.elasticsearch.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import person.jzh.hello.elasticsearch.pojo.Content;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/27 15:37
 * @description 爬虫工具类，获取京东数据
 */
@Component
public class HtmlParseUtil {
//    public static void main(String[] args) throws IOException {
//        new HtmlParseUtil().parseJD("java").forEach(System.out::println);
//    }

    /**
     * 获取京东有关关键字的数据
     * @param keywords          关键字
     * @return                  数据集
     * @throws IOException
     */
    public List<Content> parseJD(String keywords) throws IOException {
        // 获取请求 https://search.jd.com/Search?keyword=java
        // 前提，需要联网
        String url = "https://search.jd.com/Search?keyword=" + keywords;
        // 解析网页，返回的就是浏览器 Document 对象
        Document document = Jsoup.parse(new URL(url), 30000);
        // 所有你在 js 中可以使用的方法，这里都能用
        Element element = document.getElementById("J_goodsList");
        // System.out.println(element.html());
        // 获取所有的li元素
        Elements elements = element.getElementsByTag("li");

        ArrayList<Content> goodsList = new ArrayList<>();

        // 获取元素中的内容，这里el，就是每个li标签了
        for (Element el : elements) {
            //TODO 这里和狂神的教程不一样，我用 source-data-lazy-img 无法获取图片地址，但用 src 可以
            // String img = el.getElementsByTag("img").eq(0).attr("source-data-lazy-img");
            String img = el.getElementsByTag("img").eq(0).attr("src");

            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

            Content content = new Content();
            content.setImg(img);
            content.setPrice(price);
            content.setTitle(title);
            goodsList.add(content);
        }
        return goodsList;
    }
}
