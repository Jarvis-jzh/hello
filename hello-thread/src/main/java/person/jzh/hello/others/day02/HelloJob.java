package person.jzh.hello.others.day02;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author jzh
 * @version 1.0.0
 * @title HelloJob
 * @date 2019/12/16 17:19
 * @description：
 */
public class HelloJob implements Job {

    public HelloJob(){

    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("--------------start--------------");
        System.out.println("hello world! - " + new Date());
        System.out.println("---------------end---------------");
    }
}
