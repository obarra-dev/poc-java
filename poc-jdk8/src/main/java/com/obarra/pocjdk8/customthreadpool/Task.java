
package com.obarra.pocjdk8.customthreadpool;

import java.math.BigInteger;

public class Task implements Runnable {

    private final boolean isActive;

    public Task(final boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public void run() {
        if (isActive) {
            runActiveThread();
        }
        else {
            runIdleThread();
        }
    }

    public static void runActiveThread() {
        try {
            BigInteger i = new BigInteger("0");
            BigInteger j = new BigInteger("0");

            while (i.compareTo(new BigInteger("200000000")) < 0) {
                i = i.add(new BigInteger("1"));
                j = j.add(j);
            }
            System.out.println("Active run Thread is done");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runActiveThreadWithParam(String maxValue) {
        try {
            BigInteger i = new BigInteger("0");
            BigInteger j = new BigInteger("0");

            while (i.compareTo(new BigInteger(maxValue)) < 0) {
                i = i.add(new BigInteger("1"));
                j = j.add(j);
            }
            System.out.println("LoadWork is done");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runIdleThread() {
        try {
            // 5min
            Thread.sleep(300000);
            System.out.println("Idle run Thread is done");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}