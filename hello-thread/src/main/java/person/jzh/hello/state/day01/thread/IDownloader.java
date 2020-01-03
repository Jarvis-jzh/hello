package person.jzh.hello.thread;

import person.jzh.hello.state.day01.util.WebDownloader;

/**
 * @author jzh
 * @version 1.0.0
 * @title TDownloader
 * @date 2019/12/11 10:25
 * @description：
 */
public class IDownloader implements Runnable {

    private String url;

    private String name;

    public IDownloader(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader wd = new WebDownloader();
        wd.download(url, name);
        System.out.println(name);
    }

    public static void main(String[] args) {
        IDownloader td1 = new IDownloader("http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg", "1.jpg");
        IDownloader td2 = new IDownloader("http://file02.16sucai.com/d/file/2015/0128/8b0f093a8edea9f7e7458406f19098af.jpg", "2.jpg");
        IDownloader td3 = new IDownloader("http://file02.16sucai.com/d/file/2015/0408/779334da99e40adb587d0ba715eca102.jpg", "3.jpg");

        new Thread(td1).start();
        new Thread(td2).start();
        new Thread(td3).start();
    }
}
