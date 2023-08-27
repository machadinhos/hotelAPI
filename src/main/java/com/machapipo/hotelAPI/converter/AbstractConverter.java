package com.machapipo.hotelAPI.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.List;

public abstract class AbstractConverter<S, T> implements Converter<S, T> {

    public List<T> convert (List<S> sources) {

        return sources.stream()
                .map(this :: convert)
                .collect(java.util.stream.Collectors.toList());
    }

}
