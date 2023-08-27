package com.machapipo.hotelAPI.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@MappedSuperclass
public abstract class AbstractModel implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;
    @CreatedDate
    private Date createdDate;


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


    @Override
    public Date getCreatedDate () {

        return createdDate;
    }


    @Override
    public void setCreatedDate (Date createdDate) {

        this.createdDate = createdDate;
    }

}
