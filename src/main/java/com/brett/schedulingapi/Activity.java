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

    private HashMap<Calendar, HashMap<Calendar, TimeSlot>> timeSlotMap = new HashMap<Calendar, HashMap<Calendar, TimeSlot>>();

    public List<Calendar> getAvailableDatesInRange(Calendar startDate, Calendar endDate) {
        ArrayList<Calendar> foundDates = new ArrayList<Calendar>();

        while (!startDate.after(endDate)) {

            List<Calendar> availableSlotsInDay = getAvailableSlotsInDay(startDate);
            if (availableSlotsInDay.size() >= 1) {
                foundDates.add(startDate);
            }
            startDate.add(Calendar.DATE, 1);
        }
        return foundDates;
    }

    public List<Calendar> getAvailableSlotsInDay(Calendar day) {
        List<Calendar> availableSlots = new ArrayList<Calendar>();

        Calendar notchedDay = notchToDay(day);
        Map<Calendar, TimeSlot> dayTimeSlots = getSlotsForDay(notchedDay);

        if (dayTimeSlots != null) {
            for (TimeSlot t : dayTimeSlots.values()) {
                if (t.hasAvailableCapacity()) {
                    availableSlots.add(t.getSlotDate());
                }
            }
        }

        return availableSlots;
    }

    public void bookActivity(Calendar time) {
        Calendar notchedToDay = notchToDay(time);
        Map<Calendar, TimeSlot> timeSlots = getDaysSlots(notchedToDay);

        if (timeSlots == null) {
            throw new IllegalStateException("Attempted to book activity on invalid time slot");
        }

        TimeSlot t = timeSlots.get(time);

        if(t.hasAvailableCapacity()){
            t.bookTimeSlot();
        }else{
            throw new IllegalStateException("Time slot has no capacity");
        }
    }

    public void addAvailability(Calendar activityDate, Integer capacity, Double cost) {
        TimeSlot t = new TimeSlot(activityDate, capacity, cost);

        Calendar notchedToDay = notchToDay(activityDate);
        HashMap<Calendar, TimeSlot> timeSlots = getDaysSlots(notchedToDay);

        if (timeSlots == null) {
            timeSlots = new HashMap<Calendar, TimeSlot>();
        }
        timeSlots.put(activityDate, t);

        timeSlotMap.put(notchedToDay, timeSlots);
    }

    private Calendar notchToDay(Calendar activityDate) {
        Calendar notchedToDay = (Calendar) activityDate.clone();
        notchedToDay.set(Calendar.HOUR_OF_DAY, 0);
        notchedToDay.set(Calendar.MINUTE, 0);
        notchedToDay.set(Calendar.SECOND, 0);
        notchedToDay.set(Calendar.MILLISECOND, 0);
        return notchedToDay;
    }

    private HashMap<Calendar, TimeSlot> getDaysSlots(Calendar notchedToDay) {
        return timeSlotMap.get(notchedToDay);
    }

    /* Return the map of time slots for a given day. Day is expected to be notched to start of day  */
    private Map<Calendar, TimeSlot> getSlotsForDay(Calendar day) {
        return timeSlotMap.get(day);
    }


}
