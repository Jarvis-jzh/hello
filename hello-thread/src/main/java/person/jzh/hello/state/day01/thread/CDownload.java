package person.jzh.hello.thread;

import person.jzh.hello.state.day01.util.WebDownloader;

import java.util.concurrent.*;

/**
 * @author jzh
 * @version 1.0.0
 * @title CDownload
 * @date 2019/12/11 16:10
 * @description：
 */
public class CDownload implements Callable<Boolean> {

    private String url;

    private String name;

    public CDownload(String url, String name) {
        this.url = url;
        this.name = name;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CDownload cd1 = new CDownload("http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg", "1.jpg");
        CDownload cd2 = new CDownload("http://file02.16sucai.com/d/file/2015/0128/8b0f093a8edea9f7e7458406f19098af.jpg", "2.jpg");
        CDownload cd3 = new CDownload("http://file02.16sucai.com/d/file/2015/0408/779334da99e40adb587d0ba715eca102.jpg", "3.jpg");

        // 创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);
        // 提交执行
        Future<Boolean> result1 = ser.submit(cd1);
        Future<Boolean> result2 = ser.submit(cd2);
        Future<Boolean> result3 = ser.submit(cd3);
        // 获取结果
        Boolean r1 = result1.get();
        Boolean r2 = result1.get();
        Boolean r3 = result1.get();
        System.out.println(r3);
        // 关闭服务
        ser.shutdownNow();
    }

    @Override
    public Boolean call() throws Exception {
        WebDownloader wd = new WebDownloader();
        wd.download(url, name);
        System.out.println(name);
        return true;
    }
}
