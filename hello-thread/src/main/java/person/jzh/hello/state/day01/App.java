package person.jzh.hello.state.day01;

import person.jzh.hello.thread.TDownloader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        TDownloader td1 = new TDownloader("http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg", "1.jpg");
        TDownloader td2 = new TDownloader("http://file02.16sucai.com/d/file/2015/0128/8b0f093a8edea9f7e7458406f19098af.jpg", "2.jpg");
        TDownloader td3 = new TDownloader("http://file02.16sucai.com/d/file/2015/0408/779334da99e40adb587d0ba715eca102.jpg", "3.jpg");

        td1.start();
        td2.start();
        td3.start();
    }
}
