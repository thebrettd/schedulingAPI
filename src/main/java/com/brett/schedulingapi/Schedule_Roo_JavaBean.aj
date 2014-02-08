// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.brett.schedulingapi;

import com.brett.schedulingapi.Activity;
import com.brett.schedulingapi.Schedule;
import com.brett.schedulingapi.TimeSlot;
import java.util.Calendar;
import java.util.Map;

privileged aspect Schedule_Roo_JavaBean {
    
    public Activity Schedule.getOwner() {
        return this.owner;
    }
    
    public void Schedule.setOwner(Activity owner) {
        this.owner = owner;
    }
    
    public Map<Calendar, Map<Calendar, TimeSlot>> Schedule.getTimeSlotMap() {
        return this.timeSlotMap;
    }
    
    public void Schedule.setTimeSlotMap(Map<Calendar, Map<Calendar, TimeSlot>> timeSlotMap) {
        this.timeSlotMap = timeSlotMap;
    }
    
}
