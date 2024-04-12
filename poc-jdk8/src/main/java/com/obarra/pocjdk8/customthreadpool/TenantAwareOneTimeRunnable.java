package com.obarra.pocjdk8.customthreadpool;

public class TenantAwareOneTimeRunnable
        implements Runnable {
    private final Runnable wrapped;


    private boolean previouslyRun = false;

    public TenantAwareOneTimeRunnable(Runnable wrapped) {
        this.wrapped = wrapped;
    }


    @Override
    public void run() {
        if (previouslyRun) {
      /*
        This is to fail fast. The request will fail when the wrapped runnable is called and gets the tenant anyway but
        by failing fast we get a better stack trace, making it easier to find and resolve the problem.
       */
            throw new RuntimeException("TenantAwareOneTimeRunnable cannot be reused");
        }

        previouslyRun = true;

        wrapped.run();

    }
}
