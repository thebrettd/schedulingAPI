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
    public void testGetAvailableSlotsInDay() throws Exception {
        Calendar activityDate = new GregorianCalendar();
        myActivity.addAvailability(activityDate, 5, 50.00);
        assertTrue(myActivity.getAvailableSlotsInDay(activityDate).size() == 1);
    }

    @Test
    public void testAddAvailability(){
        Calendar activityDate = new GregorianCalendar();
        myActivity.addAvailability(activityDate, 5, 50.00);
        assertTrue(myActivity.getAvailableSlotsInDay(activityDate).size() == 1);

        //Now add more availability 1 minute later
        Calendar activityDate2 = new GregorianCalendar();
        activityDate2.add(Calendar.MINUTE, 1); //Ensure dates are not the same
        myActivity.addAvailability(activityDate2, 5, 55.00);
        assertTrue(myActivity.getAvailableSlotsInDay(activityDate).size() == 2);

        //Now add more availability far in the future. Today's timeslots should be the same.
        Calendar activityDate3 = new GregorianCalendar();
        activityDate3.add(Calendar.DAY_OF_YEAR,1);
        myActivity.addAvailability(activityDate3, 5, 55.00);
        assertTrue(myActivity.getAvailableSlotsInDay(activityDate).size() == 2);
    }

    @Test
    public void testBookActivity() throws Exception {
        Calendar activityDate = new GregorianCalendar();
        myActivity.addAvailability(activityDate, 1, 50.00);
        assertTrue(myActivity.getAvailableSlotsInDay(activityDate).size() == 1);

        myActivity.bookActivity(activityDate);
        assertTrue(myActivity.getAvailableSlotsInDay(activityDate).size() == 0);

    }

    @Test(expected = IllegalStateException.class)
    public void testOverBookActivity() throws Exception {
        Calendar activityDate = new GregorianCalendar();
        myActivity.addAvailability(activityDate, 1, 50.00);
        assertTrue(myActivity.getAvailableSlotsInDay(activityDate).size() == 1);

        myActivity.bookActivity(activityDate);
        assertTrue(myActivity.getAvailableSlotsInDay(activityDate).size() == 0);

        myActivity.bookActivity(activityDate);
    }

    @Test
    public void testGetAvailableDatesInRangeEmptySchedule() throws Exception {
        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        List<Calendar> foundDates = myActivity.getAvailableDatesInRange(startDate, endDate);
        assertTrue(foundDates.size() == 0);
    }

    @Test
    public void testGetAvailableDatesInRange() throws Exception {
        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        endDate.add(Calendar.DAY_OF_YEAR, 7);

        Calendar activityDate = new GregorianCalendar();
        myActivity.addAvailability(activityDate, 5, 50.00);

        Calendar activityDate2 = new GregorianCalendar();
        activityDate2.add(Calendar.DAY_OF_YEAR, 1);
        myActivity.addAvailability(activityDate2, 5, 50.00);

        Calendar activityDate3 = new GregorianCalendar();
        activityDate3.add(Calendar.DAY_OF_YEAR, 2);
        myActivity.addAvailability(activityDate3, 5, 50.00);

        List<Calendar> foundDates = myActivity.getAvailableDatesInRange(startDate, endDate);
        assertTrue(foundDates.size() == 3);
    }

}
