package person.jzh.hello.state.day01.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author jzh
 * @version 1.0.0
 * @title WebDownloader
 * @date 2019/12/11 10:20
 * @description：图片下载
 */
public class WebDownloader {
    /**
     * 下载
     *
     * @param url       下载地址
     * @param name      保存名字
     */
    public void download(String url, String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (MalformedURLException e) {
            System.out.println("不合法的url");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("下载失败");
            e.printStackTrace();
        }
    }
}
