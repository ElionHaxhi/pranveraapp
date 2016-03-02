package com.pranveraapp.core.timetoread.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by elion on 31/01/16.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "el_time_to_read")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "elCoreElements")
public class TimeToReadImpl implements TimeToRead {

    @Id
    @GeneratedValue(generator = "TimeToReadId")
    @GenericGenerator(
            name = "TimeToReadId",
            strategy = "com.pranveraapp.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name="segment_value",value="TimeToReadImpl"),
            @org.hibernate.annotations.Parameter(name="segment_value",value="com.pranveraapp.core.timetoread.domain.TimeToReadImpl")
            }
    )
    @Column(name = "TIME_TO_READ_ID")
    protected Long id;

    @Column(name = "VALUE",nullable = false)
    protected Integer value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeToReadImpl)) return false;

        TimeToReadImpl that = (TimeToReadImpl) o;

        if (!getId().equals(that.getId())) return false;
        return getValue().equals(that.getValue());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getValue().hashCode();
        return result;
    }
}
