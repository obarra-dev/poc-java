package com.obarra.pocjdk8;

import java.util.function.Function;

public class DecoratorFunction<T, R>
    implements Function<T, R>

{
  private final Function<T, R> wrapped;

  public DecoratorFunction(Function<T, R> wrapped) {
    this.wrapped = wrapped;
  }

  @Override
  public R apply(T t) {
    System.out.println("IN DecoratorFunction Thread: " + Thread.currentThread().getName());

    return wrapped.apply(t);
  }
}
