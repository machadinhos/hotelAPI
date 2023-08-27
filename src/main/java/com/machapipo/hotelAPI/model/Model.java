package com.machapipo.hotelAPI.model;

import java.util.Date;

public interface Model {

    Long getId();

    void setId(Long id);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Integer getVersion();

    void setVersion(Integer version);

}
