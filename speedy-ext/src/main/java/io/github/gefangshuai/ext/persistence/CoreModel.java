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
    private Date createTime;
    private Date updateTime;


    @PrePersist
    protected void onCreate() {
        createTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
