package io.github.gefangshuai.ext.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by gefangshuai on 2015/11/13.
 */
@MappedSuperclass
public class SupportModel implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "created_time")
    private Date createdTime;
    @Column(name = "updated_time")
    private Date updatedTime;

    public SupportModel() {
        this.id = UUID.randomUUID().toString().replace("-", "");
    }

    @PrePersist
    protected void onCreate() {
        createdTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
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
