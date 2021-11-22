定时调度、轮询job的核心模块聚合

```java
public class Test {
    static class MyJob implements Job {
        @Override
        public void execute(JobContext context) {
            System.out.println(context.getTrigger().getNextFireTime());
        }
    }

    /**
     * spi机制在test中不起作用、于是写在了main里
     */
    public static void main(String[] args) {
        JobDetail jobDetail = new JobDetail();
        jobDetail.setJobClass(MyJob.class);
        SimpleTrigger trigger = new SimpleTrigger();
        trigger.setStartTime(new Date());
        //设置3min的重复频率
        trigger.setRepeatInterval(1000 * 60 * 3);

        final TimeScheduler timeScheduler = new TimeScheduler();
        timeScheduler.setStorageType(new RamJobStorage());
        timeScheduler.start(jobDetail, trigger);
    }
}
```
