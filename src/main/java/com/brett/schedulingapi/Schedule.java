package com.brett.schedulingapi;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Schedule {

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<TimeSlot> timeSlots = new HashSet<TimeSlot>();

    /**
     */
    @ManyToOne
    private Activity owner;
}
