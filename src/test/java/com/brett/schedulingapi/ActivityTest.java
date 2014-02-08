package com.brett.schedulingapi;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by brett on 2/7/14.
 */
public class ActivityTest {
    private Activity myActivity = null;

    @Before
    public void testSetup(){
        myActivity = new Activity();

    }

    @Test
    public void testGetAvailableDatesInRangeEmptySchedule() throws Exception {
        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        List<Calendar> foundDates = myActivity.getAvailableDatesInRange(startDate, endDate);
        assertTrue(foundDates.size() == 0);
    }
    @Test
    public void testAddAvailability(){
        Calendar activityDate = new GregorianCalendar();
        myActivity.addAvailability(activityDate, 5, 50.00);
        assertTrue(myActivity.getAvailableSlotsInDay(activityDate).size() == 1);

        Calendar activityDate2 = new GregorianCalendar();
        activityDate2.add(Calendar.MINUTE, 1); //Ensure
        myActivity.addAvailability(activityDate2, 5, 55.00);
        assertTrue(myActivity.getAvailableSlotsInDay(activityDate).size() == 2);

        Calendar activityDate3 = new GregorianCalendar();
        activityDate3.add(Calendar.DAY_OF_WEEK,1);
        myActivity.addAvailability(activityDate3, 5, 55.00);
        assertTrue(myActivity.getAvailableSlotsInDay(activityDate).size() == 2);
    }

    @Test
    public void testGetAvailableHoursInDay() throws Exception {
        Calendar activityDate = new GregorianCalendar();
        myActivity.addAvailability(activityDate, 5, 50.00);
        assertTrue(myActivity.getAvailableSlotsInDay(activityDate).size() == 1);
    }

    @Test
    public void testBookActivity() throws Exception {
        Calendar activityDate = new GregorianCalendar();
        myActivity.addAvailability(activityDate, 1, 50.00);
        assertTrue(myActivity.getAvailableSlotsInDay(activityDate).size() == 1);

        myActivity.bookActivity(activityDate);
        assertTrue(myActivity.getAvailableSlotsInDay(activityDate).size() == 0);

    }
}
