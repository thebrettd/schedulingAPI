package com.brett.schedulingapi;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Calendar;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class TimeSlot {

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Calendar slotDate;

    /**
     */
    private Integer capacity;

    /**
     */
    private Integer capacityUsed;

    /**
     */
    private Double cost;

    public TimeSlot(Calendar activityDate, Integer capacity, Double cost) {
        this.slotDate = activityDate;
        this.capacity = capacity;
        this.cost = cost;
        this.capacityUsed = 0;

    }

    public boolean hasAvailableCapacity() {
        return capacity > capacityUsed;
    }

    public void bookTimeSlot() {
        capacityUsed += 1;
    }
}
