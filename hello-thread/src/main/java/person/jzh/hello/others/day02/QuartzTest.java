package person.jzh.hello.others.day02;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.DateBuilder.evenSecondDateAfterNow;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


import java.util.Date;

/**
 * @author jzh
 * @version 1.0.0
 * @title QuartzTest
 * @date 2019/12/16 17:23
 * @description：
 */
public class QuartzTest {
    public void run() throws Exception {
        // 1、创建Scheduler的工厂
        SchedulerFactory sf = new StdSchedulerFactory();

        // 2、从工厂中获取调度器
        Scheduler sched = sf.getScheduler();

        // 3、创建JobDetail
        JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();

        // 时间
        // Date runTime = evenMinuteDate(new Date());
        Date runTime = evenSecondDateAfterNow();

        // 4、触发器
        // Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
        // 间隔5秒，重复4次
        Trigger trigger = newTrigger().withIdentity("trigger3", "group1").startAt(runTime)
                .withSchedule(simpleSchedule().withIntervalInSeconds(5).withRepeatCount(3)).build();

        // 5、注册任务和触发条件
        sched.scheduleJob(job, trigger);

        // 6、启动
        sched.start();

        // 5秒后停止
        Thread.sleep(50000);

        sched.shutdown();
    }

    public static void main(String[] args) throws Exception {
        QuartzTest test = new QuartzTest();
        test.run();
    }
}
