定时调度领域 最通用一般的做法，支持特性：
- 
1。灵活的扩展性



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
