package com.kanasinfo.ext.persistence;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by gefangshuai on 2015/11/13.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class SupportMongoModel implements Serializable {
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    @CreatedBy
    private ObjectId createdBy;

    @LastModifiedBy
    private ObjectId lastModifiedBy;

    public SupportMongoModel() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public ObjectId getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(ObjectId createdBy) {
        this.createdBy = createdBy;
    }

    public ObjectId getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(ObjectId lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Transient
    public boolean isNew() {
        return id == null;
    }

    @Transient
    public boolean isNotNew() {
        return !isNew();
    }
}
