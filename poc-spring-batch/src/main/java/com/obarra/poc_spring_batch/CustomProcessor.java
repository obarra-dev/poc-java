package com.obarra.poc_spring_batch;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;

public class CustomProcessor implements ItemProcessor<String, String>
{
  @Override
  public @Nullable String process(final String item) throws Exception {
    System.out.println("Processing item: " + item);
    return item.toUpperCase();
  }
}
