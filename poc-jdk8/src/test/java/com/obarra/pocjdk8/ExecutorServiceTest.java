package com.obarra.pocjdk8;

import com.obarra.pocjdk8.customthreadpool.PolicyEvaluationThreadPoolExecutor;
import com.obarra.pocjdk8.customthreadpool.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ExecutorServiceTest {

    // corePoolSize = maximumPoolSize = 1
    // keepAliveTime = 0
    // use case: ideal for creating an event loop
    @Test
    void newSingleThreadExecutor() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            System.out.println("hola");
            counter.set(1);
        });
        executor.submit(() -> {
            System.out.println("mundo");
            counter.compareAndSet(1, 2);
        });
        executor.awaitTermination(1, TimeUnit.SECONDS);
        Assertions.assertEquals(2, counter.get());
    }


    @Test
    void newSingleThreadExecutorCannotBeCasted() {
        Assertions.assertThrows(ClassCastException.class, () -> {
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();
        });
    }

    // corePoolSize = maximumPoolSize
    // keepAliveTime = 0.
    // This means that the number of threads in this thread pool is always the same
    // use case:  when the workload is known in advance and predictable Workloads.
    @Test
    void newFixedThreadPoolCastingToThreadPoolExecutor() {
        // if the number of simultaneously running tasks is always less than or equal to two, they get executed right away.
        // Otherwise, some of these tasks may be put into a queue to wait for their turn.
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        Assertions.assertEquals(2, executor.getCorePoolSize());
        Assertions.assertEquals(2, executor.getMaximumPoolSize());

        // the current number of threads in the pool.
        Assertions.assertEquals(2, executor.getPoolSize());

        // the approximate total number of tasks that have ever been scheduled for execution.
        Assertions.assertEquals(3, executor.getTaskCount());
        // the approximate number of threads that are actively executing tasks.
        Assertions.assertEquals(2, executor.getActiveCount());
        Assertions.assertEquals(0, executor.getCompletedTaskCount());

        Assertions.assertEquals(1, executor.getQueue().size());
    }

    @Test
    void customThreadPoolExecutor() {
        PolicyEvaluationThreadPoolExecutor executor = new PolicyEvaluationThreadPoolExecutor();
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        Assertions.assertEquals(200, executor.getCorePoolSize());
        Assertions.assertEquals(200, executor.getMaximumPoolSize());

        // the current number of threads in the pool.
        Assertions.assertEquals(3, executor.getPoolSize());

        // the approximate total number of tasks that have ever been scheduled for execution.
        Assertions.assertEquals(3, executor.getTaskCount());
        // the approximate number of threads that are actively executing tasks.
        Assertions.assertEquals(3, executor.getActiveCount());
        Assertions.assertEquals(0, executor.getCompletedTaskCount());

        Assertions.assertEquals(0, executor.getQueue().size());
    }



    @Test
    void customThreadPoolExecutor10_2() {
        PolicyEvaluationThreadPoolExecutor executor = new PolicyEvaluationThreadPoolExecutor();

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                Task.runActiveThread();
                return null;
            });
        }

        for (int i = 0; i < 2; i++) {
            executor.submit(() -> {
                Task.runIdleThread();
                return null;
            });
        }

        Assertions.assertEquals(200, executor.getCorePoolSize());
        Assertions.assertEquals(200, executor.getMaximumPoolSize());

        // the current number of threads in the pool.
        Assertions.assertEquals(12, executor.getPoolSize());

        // the approximate total number of tasks that have ever been scheduled for execution.
        Assertions.assertEquals(12, executor.getTaskCount());
        // the approximate number of threads that are actively executing tasks.
        Assertions.assertEquals(12, executor.getActiveCount());
        Assertions.assertEquals(0, executor.getCompletedTaskCount());

        Assertions.assertEquals(0, executor.getQueue().size());
    }


    @Test
    void newFixedThreadPoolWaitingBlockingResult() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String> future = executor.submit(() -> "thread response");
        String result = future.get();
        Assertions.assertEquals("thread response", result);
    }

    // corePoolSize = 0
    // maximumPoolSize = Integer.MAX_VALUE
    // keepAliveTime = 60 s
    // use case: when we have a lot of short-living tasks in our application and unpredictable workloads
    @Test
    void newCachedThreadPool() throws ExecutionException, InterruptedException {
        // the cached thread pool may grow without bounds to accommodate any number of submitted tasks
        // when the threads are not needed anymore, they will be disposed of after 60 seconds of inactivity
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        Future<Object> submitTwo = executor.submit(() -> {
            Thread.sleep(1000000);
            return null;
        });

        executor.submit(() -> {
            Thread.sleep(1000000);
            return null;
        });

        Thread.sleep(500);


        Assertions.assertEquals(0, executor.getCorePoolSize());
        Assertions.assertEquals(Integer.MAX_VALUE, executor.getMaximumPoolSize());

        // the current number of threads in the pool, current size of a thread pool, threadpool_current_size
        Assertions.assertEquals(3, executor.getPoolSize());

        // the approximate total number of tasks that have ever been scheduled for execution, threadpool_scheduled_size
        Assertions.assertEquals(3, executor.getTaskCount()); // important
        // the approximate number of threads that are actively executing tasks in a thread pool, threadpool_active_size
        Assertions.assertEquals(3, executor.getActiveCount());
        Assertions.assertEquals(0, executor.getCompletedTaskCount()); // important

        // need tests!!,  number of tasks in the queue of the threadpool
        long tasksToDo = executor.getTaskCount() - executor.getCompletedTaskCount() - executor.getActiveCount();

        //need tests!!,  For some reason though it does not always exclude all the cancelled tasks, only after some periodic clean ups. So I had to introduce a second counter:
        long tasksToDo2 = executor.getQueue().stream().filter(t -> !((FutureTask) t).isDone()).count();

        Assertions.assertEquals(0, executor.getQueue().size());
    }

    // given corePoolSize
    // unbounded maximumPoolSize
    // keepAliveTime = 0
    @Test
    void newScheduledThreadPoolSchedule() throws ExecutionException, InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        ScheduledFuture<String> future = executor.schedule(() -> "response from thread", 500, TimeUnit.MILLISECONDS);

        Assertions.assertEquals("response from thread", future.get());
    }

    @Test
    void newScheduledThreadPoolScheduleAtFixedRate() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(3);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            lock.countDown();
        }, 500, 100, TimeUnit.MILLISECONDS);

        lock.await(1000, TimeUnit.MILLISECONDS);
        future.cancel(true);

        Assertions.assertTrue(future.isCancelled());
        Assertions.assertFalse(executor.isShutdown());

    }

}
