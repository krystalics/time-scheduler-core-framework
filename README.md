目标：生产可用的分布式定时调度器
- 
已支持特性:</br>
1.job、多trigger 简单重复运行完成</br>
2.</br>

todo list</br>
1.将job发送到执行端并行执行</br>
2.job调度端分片，分散压力</br>
3.对于cron的支持</br>
4.任务手动重跑历史的记录，如何确保幂等性</br>
5.集群分布式特性

```java
public class TimeSchedulerTest {

    private static final Logger logger = LoggerFactory.getLogger(TimeSchedulerTest.class);

    static class MyJob implements Job {
        @Override
        public void execute(JobContext context) {
            logger.info(context.getTrigger().getTriggerName() + " " + context.getTrigger().getNextFireTime().toString());
        }
    }

    /**
     * 额外的线程在test中不起作用、于是写在了main里
     */
    public static void main(String[] args) {
        String jobGroup = "j-group1";
        String jobName = "job1";
        String triggerGroup = "t-group1";
        String triggerName = "trigger1";
        String triggerName2 = "trigger2";

        JobDetail jobDetail = new JobDetail.Builder(jobGroup, jobName)
                .concurrent(true)
                .jobClass(MyJob.class)
                .build();

        SimpleTrigger trigger = new TriggerDetail.Builder(jobGroup, jobName, triggerGroup, triggerName)
                .repeatInterval(1000 * 60)
                .startTime(new Date())
                .simpleBuild();


        SimpleTrigger trigger2 = new TriggerDetail.Builder(jobGroup, jobName, triggerGroup, triggerName2)
                .repeatInterval(1000 * 38)
                .startTime(new Date())
                .simpleBuild();


        final TimeScheduler timeScheduler = new TimeScheduler(new RamJobStorage(), new JDBCLock());

//        timeScheduler.storeJobAndTrigger(jobDetail, trigger);
        timeScheduler.storeJobs(jobDetail);
        timeScheduler.storeTriggers(trigger, trigger2);
        timeScheduler.start();
    }
}

```
