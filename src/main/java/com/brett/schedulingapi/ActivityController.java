package com.brett.schedulingapi;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RequestMapping("/activitys")
@Controller
@RooWebScaffold(path = "activitys", formBackingObject = Activity.class)
public class ActivityController {

    @RequestMapping(value="/addActivity", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    public String addSchedule(@RequestBody Activity activity) throws ParseException {
        activity.persist();
        return "Added activity " + activity;
    }

    @RequestMapping(value="/addAvailability/{id}", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    public String addSchedule(@PathVariable("id") Long activityId, @RequestBody Availability availability, Model uiModel) throws ParseException {
        Activity activity = Activity.findActivity(activityId);

        if (activity == null){
            return "Unable to locate activity " + activityId;
        }

        Calendar activityDate = new GregorianCalendar();
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy HH:mm");
        Date date = formatter.parse(availability.getDate());
        activityDate.setTime(date);

        activity.addAvailability(activityDate, Integer.parseInt(availability.getCapacity()), Double.parseDouble(availability.getCost()));
        activity.persist();

        return "Availability added on " + formatter.format(date);
    }

    @RequestMapping(value="/book/{id}", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    public String book(@PathVariable("id") Long activityId, @RequestBody Booking booking) throws ParseException {
        Activity activity = Activity.findActivity(activityId);

        if (activity == null){
            return "Unable to locate activity " + activityId;
        }

        Calendar activityDate = new GregorianCalendar();
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy HH:mm");
        Date date = formatter.parse(booking.getDate());
        activityDate.setTime(date);

        try{
            activity.bookActivity(activityDate);
        }catch(IllegalStateException e){
            return e.getMessage();
        }

        activity.merge();

        return "Activity booked on " + formatter.format(date);
    }

    @RequestMapping(value="/queryRange/{id}", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    public String queryRange(@PathVariable("id") Long activityId, @RequestBody RangeQuery rangeQuery) throws ParseException {
        Activity activity = Activity.findActivity(activityId);

        if (activity == null){
            return "Unable to locate activity " + activityId;
        }

        Calendar startDate = new GregorianCalendar();
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy HH:mm");
        Date date = formatter.parse(rangeQuery.getStartDate());
        startDate.setTime(date);

        Calendar endDate = new GregorianCalendar();
        date = formatter.parse(rangeQuery.getEndDate());
        endDate.setTime(date);

        List<Calendar> availableDatesInRange = activity.getAvailableDatesInRange(startDate, endDate);

        StringBuilder response = new StringBuilder();
        for(Calendar availableDate : availableDatesInRange){
            response.append(availableDate.toString()).append(",").append("\n");
        }

        return "Found " + availableDatesInRange.size() + " days: " + response.toString();
    }

    @RequestMapping(value="/queryDate/{id}", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    public String queryDate(@PathVariable("id") Long activityId, @RequestBody DayQuery day) throws ParseException {
        Activity activity = Activity.findActivity(activityId);

        if (activity == null){
            return "Unable to locate activity " + activityId;
        }

        Calendar singleDate = new GregorianCalendar();
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy HH:mm");
        Date date = formatter.parse(day.getStartDate());
        singleDate.setTime(date);

        List<Calendar> availableTimesInDay = activity.getAvailableSlotsInDay(singleDate);

        StringBuilder response = new StringBuilder().append("\n");
        for(Calendar availableDate : availableTimesInDay){
            response.append(availableDate.getTime().toString()).append("\n");
        }

        return "Found " + availableTimesInDay.size() + " slots: " + response.toString();
    }



}
