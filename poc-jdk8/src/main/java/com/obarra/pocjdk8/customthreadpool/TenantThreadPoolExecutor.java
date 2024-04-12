package com.obarra.pocjdk8.customthreadpool;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;



public class TenantThreadPoolExecutor
        extends ThreadPoolExecutor
{
    public TenantThreadPoolExecutor(
            int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue,
            ThreadFactory threadFactory)
    {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public TenantThreadPoolExecutor(int corePoolSize,
                                    int maximumPoolSize,
                                    long keepAliveTime,
                                    TimeUnit unit,
                                    BlockingQueue<Runnable> workQueue,
                                    ThreadFactory threadFactory,
                                    RejectedExecutionHandler handler)
    {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(new TenantAwareOneTimeCallable<>(task));
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return super.submit(new TenantAwareOneTimeRunnable(task), result);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(new TenantAwareOneTimeRunnable(task));
    }

    @Override
    public void execute(Runnable task) {
        super.execute(new TenantAwareOneTimeRunnable(task));
    }

    @Override
    public <T> List<Future<T>> invokeAll(final Collection<? extends Callable<T>> tasks)
            throws InterruptedException
    {
        List<TenantAwareOneTimeCallable<T>> wrappedTasks = tasks.stream()
                .map(TenantAwareOneTimeCallable::new)
                .collect(Collectors.toList());
        return super.invokeAll(wrappedTasks);
    }
}
