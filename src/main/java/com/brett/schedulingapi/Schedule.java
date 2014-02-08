package com.brett.schedulingapi;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Schedule {

    /**
     */
    @ManyToOne
    private Activity owner;

    /**
     */
    private Map<Calendar, Map<Calendar, TimeSlot>> timeSlotMap = new HashMap<Calendar, Map<Calendar, TimeSlot>>();

    /* Return the map of time slots for a given day. Day is expected to be notched to start of day  */
    public Map<Calendar, TimeSlot> getSlotsForDay(Calendar day) {
        return timeSlotMap.get(day);
    }
}
