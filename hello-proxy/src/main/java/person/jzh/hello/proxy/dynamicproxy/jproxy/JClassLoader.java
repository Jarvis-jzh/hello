package person.jzh.hello.proxy.dynamicproxy.jproxy;

import java.io.*;

/**
 * @author jzh
 * @version 1.0.0
 * @title JClassLoader
 * @date 2019/12/21 10:35
 * @description：
 */
public class JClassLoader {

    private File classPathFile;

    public JClassLoader() {
        String classPath = JClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    public Class<?> findClass(String name) throws ClassNotFoundException {
        String className = JClassLoader.class.getPackage().getName() + "." + name;
        if (classPathFile != null) {
            File classFile = new File(classPathFile, name.replaceAll("\\.", "/") + ".class");
            if (classFile.exists()){
                FileInputStream in = null;
                ByteArrayOutputStream out = null;
                try {
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while((len = in.read(buff)) != -1) {
                        out.write(buff, 0, len);
                    }
                    return defineClass(className, out.toByteArray(), 0, out.size());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private Class<?> defineClass(String className, byte[] toByteArray, int i, int size) {

        return null;
    }
}
