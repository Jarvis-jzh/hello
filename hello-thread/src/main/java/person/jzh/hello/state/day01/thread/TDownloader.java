package person.jzh.hello.thread;

import person.jzh.hello.state.day01.util.WebDownloader;

/**
 * @author jzh
 * @version 1.0.0
 * @title TDownloader
 * @date 2019/12/11 10:25
 * @description：
 */
public class TDownloader extends Thread {

    private String url;

    private String name;

    public TDownloader(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader wd = new WebDownloader();
        wd.download(url, name);
        System.out.println(name);
    }
}
