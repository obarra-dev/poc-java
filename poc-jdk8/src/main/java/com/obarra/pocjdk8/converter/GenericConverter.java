package com.obarra.pocjdk8.converter;

import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
public interface GenericConverter<E, D> {

    E apply(D dto);

    default List<E> apply(List<D> entities) {
        return entities.stream().map(this::apply).collect(Collectors.toList());
    }
}
