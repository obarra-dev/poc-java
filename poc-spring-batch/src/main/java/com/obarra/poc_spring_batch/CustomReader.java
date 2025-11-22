package com.obarra.poc_spring_batch;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemReader;

public class CustomReader
    implements ItemReader<String>
{
  private final String[] data = {"one", "two", "three" };

  private int index = 0;

  @Override
  public @Nullable String read() throws Exception {
    if (index < data.length) {
      String item = index + " " + data[index++];
      System.out.println("Reading item: " + item);
      return item;
    }

    return null;
  }
}
