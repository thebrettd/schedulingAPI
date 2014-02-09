package com.brett.schedulingapi;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Calendar;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findTimeSlotsByActivityTimeBetween", "findTimeSlotsByActivityTimeEquals", "findTimeSlotsByUsedCapacityLessThanAndActivityTimeBetween", "findTimeSlotsByHasCapacityEqualsAndActivityTimeBetween" })
public class TimeSlot {

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Calendar activityTime;

    /**
     */
    private Integer capacity;

    /**
     */
    private Integer usedCapacity;

    /**
     */
    private Double price;

    /**
     */
    private Boolean hasCapacity;

    /**
     */
    @ManyToOne
    private Activity activity;

    public TimeSlot(Calendar activityTime, int capacity, double price) {
        this.activityTime = activityTime;
        this.capacity = capacity;
        this.price = price;
        this.usedCapacity = 0;
        hasCapacity = this.usedCapacity < this.capacity;
    }
}
