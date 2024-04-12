package com.obarra.pocjdk8.customthreadpool;

import java.util.concurrent.Callable;

public class TenantAwareOneTimeCallable<T> implements Callable<T>
{
  private final Callable<T> wrapped;

  private boolean previouslyRun = false;

  public TenantAwareOneTimeCallable(Callable<T> wrapped) {
    this.wrapped = wrapped;
  }

  @Override
  public T call() throws Exception {
    if (previouslyRun) {
      /*
        This is to fail fast. The request will fail when the wrapped runnable is called and gets the tenant anyway but
        by failing fast we get a better stack trace, making it easier to find and resolve the problem.
       */
      throw new RuntimeException("TenantAwareOneTimeCallable cannot be reused");
    }

    previouslyRun = true;

    return wrapped.call();

  }
}
