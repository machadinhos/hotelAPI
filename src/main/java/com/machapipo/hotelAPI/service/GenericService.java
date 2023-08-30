package com.machapipo.hotelAPI.service;

import com.machapipo.hotelAPI.persistence.model.Model;

import java.util.List;

public interface GenericService<T extends Model> {

    List<T> getAll ();

    T getById (Long id);

    T create (T t);

    T update (T t);

    void delete (T model);

}
