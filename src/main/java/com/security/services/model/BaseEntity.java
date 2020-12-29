package com.security.services.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.security.services.model.jsonSerializers.LocalDateTimeDeserializer;
import com.security.services.model.jsonSerializers.LocalDateTimeSerializer;
import lombok.Data;


@Data
@MappedSuperclass
public class BaseEntity {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "created_time", insertable = true, updatable = false)
    private LocalDateTime createdTime;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "updated_time", insertable = false, updatable = true)
    private LocalDateTime updatedTime;


    @PrePersist
    protected void onCreate() {
        createdTime = LocalDateTime.now();
        updatedTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        if (Objects.nonNull(createdTime)) {
            updatedTime = LocalDateTime.now();
        }
    }


}
