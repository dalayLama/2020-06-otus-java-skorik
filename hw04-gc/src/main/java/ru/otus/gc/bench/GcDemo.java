package ru.otus.gc.bench;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.MBeanServer;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import java.io.File;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;


public class GcDemo {

    private static GcStatistic statistic = new GcStatistic();

    public static void main(String... args) throws Exception {
        System.out.println(new File(".").getAbsolutePath());
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        switchOnMonitoring();
        long beginTime = System.currentTimeMillis();
//
        int loopCounter = 5;
        int size = 5 * 1000 * 1000;
        //int loopCounter = 100000;
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.otus:type=Benchmark");

        Benchmark mbean = new Benchmark(loopCounter);
        mbs.registerMBean(mbean, name);
        mbean.setSize(size);
        mbean.run();

        statistic.setStopCounting(true);

        System.out.println("time:" + (double) (System.currentTimeMillis() - beginTime) / (double) 1000);
        System.out.println("" +
                "gc quantity of calls: " + statistic.getQuantityCalls() + ", " +
                String.format("total duration: %.4f, ", (double) statistic.getSumDuration() / (double) 1000) +
                String.format("avg duration: %.6f" ,statistic.avgDuration() / 1000));
    }

    private static void switchOnMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            System.out.println("GC name:" + gcbean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    String gcName = info.getGcName();
                    String gcAction = info.getGcAction();
                    String gcCause = info.getGcCause();

                    long startTime = info.getGcInfo().getStartTime();
                    long duration = info.getGcInfo().getDuration();

                    System.out.println("start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)");
                    if (gcAction.equals("end of minor GC")) {
                        statistic.addDuration(duration);
                    }
                }
            };
            emitter.addNotificationListener(listener, null, null);
        };
    }
}
