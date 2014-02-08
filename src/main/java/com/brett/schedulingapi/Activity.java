package com.brett.schedulingapi;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.ArrayList;

import java.util.Calendar;

import java.util.Map;
import java.util.HashMap;

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
    @ManyToOne
    private Vendor owner;

    /**
     * duration in minutes
     */
    @NotNull
    private Integer duration;

    /**
     */
    @ManyToOne
    private Schedule schedule = new Schedule();

    public List<Calendar> getAvailableDatesInRange(Calendar startDate, Calendar endDate){
        return new ArrayList<Calendar>();
    }

    public List<Calendar> getAvailableSlotsInDay(Calendar day){
        List<Calendar> availableSlots = new ArrayList<Calendar>();

        Calendar notchedDay = notchToDay(day);
        Map<Calendar, TimeSlot> dayTimeSlots = schedule.getSlotsForDay(notchedDay);

        for(TimeSlot t: dayTimeSlots.values()){
            if (t.hasAvailableCapacity()){
                availableSlots.add(t.getSlotDate());
            }
        }

        return availableSlots;
    }

    public void bookActivity(Calendar time){
        Calendar notchedToDay = notchToDay(time);
        Map<Calendar, TimeSlot> timeSlots = getDaysSlots(notchedToDay);

        if(timeSlots == null){
            throw new IllegalStateException("Attempted to book activity on invalid time slot");
        }

        TimeSlot t = timeSlots.get(time);
        t.bookTimeSlot();

    }

    public void addAvailability(Calendar activityDate, Integer capacity, Double cost){
        TimeSlot t = new TimeSlot(activityDate, capacity, cost);

        Calendar notchedToDay = notchToDay(activityDate);
        int hour = notchedToDay.get(Calendar.HOUR_OF_DAY);
        int minute = notchedToDay.get(Calendar.MINUTE);
        int second = notchedToDay.get(Calendar.SECOND);
        if (hour != 0 || minute != 0 || second != 0){
            throw new IllegalStateException(String.format("Invalid notch %s %s %s", hour, minute, second));
        }

        Map<Calendar, TimeSlot> timeSlots = getDaysSlots(notchedToDay);

        if (timeSlots == null){
            timeSlots = new HashMap<Calendar, TimeSlot>();
        }
        int countBefore = timeSlots.size();
        timeSlots.put(activityDate, t);
        int countAfter = timeSlots.size();
        if(countBefore == countAfter){
            throw new IllegalStateException("Slot failed to add");
        }

        schedule.getTimeSlotMap().put(notchedToDay, timeSlots);
    }

    private Calendar notchToDay(Calendar activityDate) {
        Calendar notchedToDay = (Calendar) activityDate.clone();
        notchedToDay.set(Calendar.HOUR_OF_DAY, 0);
        notchedToDay.set(Calendar.MINUTE, 0);
        notchedToDay.set(Calendar.SECOND, 0);
        notchedToDay.set(Calendar.MILLISECOND, 0);
        return notchedToDay;
    }

    private Map<Calendar, TimeSlot> getDaysSlots(Calendar notchedToDay) {


        return schedule.getTimeSlotMap().get(notchedToDay);
    }
}
