package com.brett.schedulingapi;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import java.util.Map;
import java.util.HashMap;

import java.util.Calendar;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Activity {

    /**
     */
    @NotNull
    private String name;

    /**
     */
    private String description;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<TimeSlot> timeSlots = new HashSet<TimeSlot>();

    public void addAvailability(Calendar activityDate, int capacity, double price) {
        TimeSlot t = new TimeSlot(activityDate, capacity, price);
        timeSlots.add(t);
    }

    public void bookActivity(Calendar activityDate) {
        //find the timeslot corresponding to this activityDate
        TimeSlot t = TimeSlot.findTimeSlotsByActivityTimeEquals(activityDate).getSingleResult();

        //update the capacity
        if (!t.getHasCapacity()){
            throw new IllegalStateException("Attempted to book activity on invalid time slot");
        }else{
            t.setUsedCapacity(t.getUsedCapacity() + 1);
            if(t.getCapacity().equals(t.getUsedCapacity())){
                t.setHasCapacity(Boolean.FALSE);
            }
        }
    }

    public List<Calendar> getAvailableDatesInRange(Calendar startDate, Calendar endDate) {
        List<TimeSlot> resultList = TimeSlot.findTimeSlotsByHasCapacityEqualsAndActivityTimeBetween(Boolean.TRUE, startDate, endDate).getResultList();

        //Notch everything to the day and add it to the map
        Map<Calendar, TimeSlot> foundDaysMap = new HashMap<Calendar, TimeSlot>();
        for(TimeSlot t: resultList){
            Calendar notchedDate = notchToDay(t.getActivityTime());
            foundDaysMap.put(notchedDate,t);
        }
        //Convert the map's keyset to a list
        List<Calendar> foundDays = new ArrayList<Calendar>();
        for(Calendar c : foundDaysMap.keySet()){
            foundDays.add(c);
        }

        return foundDays;
    }

    public List<Calendar> getAvailableSlotsInDay(Calendar singleDate) {
        Calendar startOfDay = Activity.notchToDay(singleDate);
        Calendar endOfDay = Activity.notchToDay(singleDate);
        endOfDay.add(Calendar.DAY_OF_YEAR, 1);

        List<TimeSlot> resultList = TimeSlot.findTimeSlotsByHasCapacityEqualsAndActivityTimeBetween(Boolean.TRUE, startOfDay, endOfDay).getResultList();

        List<Calendar> availableSlots = new ArrayList<Calendar>();
        for(TimeSlot t: resultList ){
            availableSlots.add(t.getActivityTime());
        }
        return availableSlots;
    }

    public static  Calendar notchToDay(Calendar activityDate) {
        Calendar notchedToDay = (Calendar) activityDate.clone();
        notchedToDay.set(Calendar.HOUR_OF_DAY, 0);
        notchedToDay.set(Calendar.MINUTE, 0);
        notchedToDay.set(Calendar.SECOND, 0);
        notchedToDay.set(Calendar.MILLISECOND, 0);
        return notchedToDay;
    }
}
