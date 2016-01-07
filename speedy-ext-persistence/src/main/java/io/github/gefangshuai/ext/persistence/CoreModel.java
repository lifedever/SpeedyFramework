package io.github.gefangshuai.ext.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by gefangshuai on 2015/11/13.
 */
@MappedSuperclass
public class CoreModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdTime;
    private Date updatedTime;


    @PrePersist
    protected void onCreate() {
        createdTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return id == null || id == 0;
    }

    @Transient
    public boolean isNotNew() {
        return id != null && id > 0;
    }
}
