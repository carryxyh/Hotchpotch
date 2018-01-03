package com.netease.music.musician.batch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class BatchExecutor {

    /**
     * 任务分几批
     */
    private final Integer GROUP_COUNT;

    /**
     * 线程池
     */
    private ExecutorService executor;

    /**
     * 异常处理器
     */
    private final ExceptionHandler exceptionHandler;

    public BatchExecutor(Integer groupCount) {
        this(groupCount, new DefaultExceptionHandler());
    }

    public BatchExecutor(Integer groupCount, ExceptionHandler exceptionHandler) {
        if (groupCount == null || groupCount < 0 || exceptionHandler == null) {
            throw new IllegalArgumentException(" group count is must not be null and over zero !");
        }
        this.GROUP_COUNT = groupCount;
        this.exceptionHandler = exceptionHandler;
        executor = Executors.newWorkStealingPool(GROUP_COUNT);
    }

    /**
     * 批量执行一个任务
     *
     * @param task 任务
     */
    public void batchExecute(BatchTask task) {
        if (task == null) {
            throw new NullPointerException(" execute task is null !");
        }
        List datas = task.getData();
        int count = (datas.size() + GROUP_COUNT - 1) / GROUP_COUNT;
        CountDownLatch countDownLatch = new CountDownLatch(GROUP_COUNT);
        for (int index = 0; index < GROUP_COUNT; index++) {
            int startIndex = index * count;
            int endIndex;
            if ((index + 1) * count > datas.size()) {
                endIndex = datas.size();
            } else {
                endIndex = (index + 1) * count;
            }
            List subData;
            if (startIndex < endIndex) {
                subData = datas.subList(startIndex, endIndex);
            } else {
                subData = new ArrayList<>();
            }
            RealWorker rw = new RealWorker(subData, task, countDownLatch, exceptionHandler);
            executor.execute(rw);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
        }
        System.out.println("batch task over ...");
    }

    /**
     * 真正的线程
     */
    private class RealWorker implements Runnable {

        /**
         * 数据的分片
         */
        private final List subData;

        /**
         * 任务
         */
        private final BatchTask t;

        /**
         * count down latch
         */
        private final CountDownLatch c;

        private final ExceptionHandler exceptionHandler;

        RealWorker(List subData, BatchTask t, CountDownLatch c, ExceptionHandler exceptionHandler) {
            this.subData = subData;
            this.t = t;
            this.c = c;
            this.exceptionHandler = exceptionHandler;
        }

        @Override
        public void run() {
            try {
                t.realDone(subData);
            } catch (Exception e) {
                exceptionHandler.handle(subData, e);
            } finally {
                c.countDown();
            }
        }
    }

    public static void main(String[] args) {
        BatchExecutor batchExecutor = new BatchExecutor(2);

        //数据
        DemoData1 d1 = new DemoData1("修宇航");
        DemoData2 d2 = new DemoData2("云音乐");
        List<DemoData> datas = Arrays.asList(d1, d2);

        //把数据传进去  重写realDone方法，realDone中就是真正处理数据的过程，可以批处理list也可以一条一条处理
        BatchTask t = new AbstractBatchTask(datas) {

            @Override
            public void realDone(List d) {
                for (Object demoData : d) {
                    DemoData demo = (DemoData) demoData;
                    demo.sayName();
                }
            }
        };
        batchExecutor.batchExecute(t);
    }

    static class DemoData1 extends DemoData {

        protected DemoData1(String name) {
            super(name);
        }

        @Override
        public void sayName() {
            System.out.println(super.name);
        }
    }

    static class DemoData2 extends DemoData {

        protected DemoData2(String name) {
            super(name);
        }

        @Override
        public void sayName() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(super.name);
        }
    }
}
