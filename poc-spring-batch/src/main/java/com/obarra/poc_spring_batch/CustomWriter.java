package com.obarra.poc_spring_batch;

import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;

public class CustomWriter
    implements ItemWriter<String>
{
  @Override
  public void write(final Chunk<? extends String> chunk) throws Exception {
    for (String item : chunk) {
        System.out.println("Writing item: " + item);
    }

    System.out.println("Finished writing chunk of size: " + chunk.size());
  }
}
