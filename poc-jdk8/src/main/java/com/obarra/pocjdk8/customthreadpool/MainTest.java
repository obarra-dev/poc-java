package com.obarra.pocjdk8.customthreadpool;

public class MainTest {
    public static void main(String[] args) {
        PolicyEvaluationThreadPoolExecutor executor = new PolicyEvaluationThreadPoolExecutor();

        for (int i = 0; i < 10; i++) {
            executor.submit(new Task(false));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("All threads finished");
    }
}
