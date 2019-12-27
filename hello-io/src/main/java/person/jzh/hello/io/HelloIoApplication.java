package person.jzh.hello.io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

//@SpringBootApplication
public class HelloIoApplication {

    public static void main(String[] args) throws Exception {
        InputStream inputStream = new FileInputStream(new File("1"));
        byte[] bytes = new byte[4096];
        inputStream.read(bytes);
    }

}
