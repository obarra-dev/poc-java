package com.obarra.pocjdk8.customthreadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class PolicyEvaluationThreadPoolExecutor
        extends TenantThreadPoolExecutor {
    // corePoolSize
    static final int THREAD_POOL_SIZE = 200;
    static final int MAXIMUM_POOL_SIZE = 200;
    static final int KEEP_ALIVE_TIME_M = 5;


    // This means that the number of threads in this thread pool is always the same
    // similar to newFixedThreadPool THREAD_POOL_SIZE = MAXIMUM_POOL_SIZE
    public PolicyEvaluationThreadPoolExecutor() {
        super(THREAD_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME_M, TimeUnit.MINUTES, new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory());
        allowCoreThreadTimeOut(true);
    }

    @Override
    public void execute(Runnable command) {
        System.out.println("Policy evaluation executor state before submit:");
        System.out.println(" queueSize= " + getQueue().size() + " activeThreads= " + getActiveCount() + "totalThreads=" + getPoolSize());


        super.execute(command);

        int queueSize = getQueue().size();
        int activeThreadCount = getActiveCount();


        System.out.println("Policy evaluation executor state after submit:");
        System.out.println(" queueSize= " + getQueue().size() + " activeThreads= " + getActiveCount() + "totalThreads=" + getPoolSize());


        if (queueSize > 0 && activeThreadCount == getMaximumPoolSize()) {
            System.out.println("All policy evaluation threads are busy and there are " + queueSize + " tasks waiting in the queue.");
        }
    }
}
