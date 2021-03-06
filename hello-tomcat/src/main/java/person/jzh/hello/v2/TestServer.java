package person.jzh.hello.v2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * @author jzh
 * @version 1.0.0
 * @title TestServer
 * @date 2019/12/19 9:06
 * @description：
 */
public class TestServer {
    // 存放服务端web的绝对路径
    public static String WEB_ROOT = System.getProperty("user.dir") + "\\" + "WebContent";
    // 本次请求的静态页面
    private static String url = "";

    // 存储服务端配置信息
    private static Map<String, String> map = new HashMap<>(16);

    static {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(WEB_ROOT + "\\conf.properties"));
            Set set = prop.keySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()){
                String key = (String) iterator.next();
                String value = prop.getProperty(key);
                map.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(map);
        // System.out.println(WEB_ROOT);
        ServerSocket socket = null;
        Socket accept = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            socket = new ServerSocket(8080);
            while (true) {
                accept = socket.accept();
                is = accept.getInputStream();
                os = accept.getOutputStream();

                parse(is);
                System.out.println(url);

                if (null != url){
                    if (url.indexOf(".")!=-1){
                        sendStaticResource(os);
                    } else {
                        sendDynamicResource(is, os);
                    }
                }

                sendStaticResource(os);

                if (null != is) {
                    is.close();
                }
                if (null != os) {
                    os.close();
                }
                if (accept != null) {
                    accept.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private static void sendDynamicResource(InputStream is, OutputStream os) throws Exception {
        os.write("HTTP/1.1 200 OK\n".getBytes());
        os.write("Server:Apache\n".getBytes());
        os.write("Content-type:text/html;charset=utf-8\n".getBytes());
        os.write("\n".getBytes());

        if (map.containsKey(url)){
            String value = map.get(url);
            Class<?> clazz = Class.forName(value);
            Servlet instance = (Servlet) clazz.newInstance();
            instance.service(is, os);
        }
    }

    private static void sendStaticResource(OutputStream os) {
        byte[] buffer = new byte[2048];
        FileInputStream fis = null;
        try {
            File file = new File(WEB_ROOT, url);
            if (file.exists()) {
                os.write("HTTP/1.1 200 OK\n".getBytes());
                os.write("Content-Type:text/html;charset=utf-8\n".getBytes());
                os.write("Server:Apache-Coyote/1.1\n".getBytes());
                os.write("\n".getBytes());
                fis = new FileInputStream(file);
                int ch = fis.read(buffer);
                while (ch != -1) {
                    os.write(buffer, 0, ch);
                    ch = fis.read(buffer);
                }
            } else {
                os.write("HTTP/1.1 404 OK\n".getBytes());
                os.write("Content-Type:text/html;charset=utf-8\n".getBytes());
                os.write("Server:Apache-Coyote/1.1\n".getBytes());
                os.write("\n".getBytes());

                String errorMessage = "file not found";
                os.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void parse(InputStream is) throws IOException {
        // 存放Http协议请求部分数据
        StringBuffer content = new StringBuffer();
        // 存放Http协议请求部分数据
        byte[] buffer = new byte[2048];
        // 数据量
        int i = -1;
        // 读取数据，并存到buffer里，i代表读取的数据量d大小
        i = is.read(buffer);
        for (int j = 0; j < i; j++) {
            content.append((char) buffer[j]);
        }
        System.out.println(content);
        // 截取url
        parseUrl(content.toString());
    }

    private static void parseUrl(String content) {
        int index1, index2;
        index1 = content.indexOf(" ");
        if (index1 != -1) {
            index2 = content.indexOf(" ", index1 + 1);
            if (index2 > index1) {
                url = content.substring(index1 + 2, index2);
            }
        }
    }
}
