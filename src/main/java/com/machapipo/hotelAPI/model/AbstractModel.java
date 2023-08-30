package com.machapipo.hotelAPI.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class AbstractModel implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;


    @Override
    public Long getId () {

        return id;
    }


    @Override
    public void setId (Long id) {

        this.id = id;
    }


    @Override
    public Integer getVersion () {

        return version;
    }


    @Override
    public void setVersion (Integer version) {

        this.version = version;
    }

}
