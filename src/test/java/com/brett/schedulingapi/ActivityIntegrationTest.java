package com.brett.schedulingapi;
import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RooIntegrationTest(entity = Activity.class)
public class ActivityIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

    @Test
    public void testAddAvailability() throws Exception {
        Calendar activityDate = new GregorianCalendar();
        Activity randomActivity = dod.getRandomActivity();

        assertTrue(randomActivity.getTimeSlots().isEmpty());
        randomActivity.addAvailability(activityDate, 5, 50.00);
        assertTrue(randomActivity.getTimeSlots().size() == 1);

        TimeSlot singleResult = TimeSlot.findTimeSlotsByActivityTimeEquals(activityDate).getSingleResult();
        assertTrue(singleResult.getActivityTime().equals(activityDate));

    }

    @Test
    public void testAddAvailabilityMidnight() throws Exception {
        Calendar activityDate = new GregorianCalendar();

        activityDate.set(Calendar.HOUR, 0);
        activityDate.set(Calendar.MINUTE, 0);
        activityDate.set(Calendar.SECOND, 0);
        activityDate.set(Calendar.MILLISECOND, 0);

        Activity randomActivity = dod.getRandomActivity();

        assertTrue(randomActivity.getTimeSlots().isEmpty());
        randomActivity.addAvailability(activityDate, 5, 50.00);
        assertTrue(randomActivity.getTimeSlots().size() == 1);

        TimeSlot singleResult = TimeSlot.findTimeSlotsByActivityTimeEquals(activityDate).getSingleResult();
        assertTrue(singleResult.getActivityTime().equals(activityDate));
        assertTrue(randomActivity.getAvailableSlotsInDay(activityDate).size() == 1);

    }

    @Test
    public void testGetAvailableSlotsInDay() throws Exception {
        Activity randomActivity = dod.getRandomActivity();

        //Add several time slots
        Calendar activityDate = new GregorianCalendar();
        randomActivity.addAvailability(activityDate, 5, 50.00);

        Calendar activityDate2 = new GregorianCalendar();
        activityDate2.add(Calendar.MINUTE, 1); //Ensure dates are not the same
        randomActivity.addAvailability(activityDate2, 5, 50.00);

        Calendar activityDate3 = new GregorianCalendar();
        activityDate3.add(Calendar.MINUTE, 2); //Ensure dates are not the same
        randomActivity.addAvailability(activityDate3, 5, 50.00);

        //Add one that is on another day
        Calendar activityDate4 = new GregorianCalendar();
        activityDate4.add(Calendar.YEAR, 1); //Ensure dates are not the same
        randomActivity.addAvailability(activityDate4, 5, 50.00);

        assertTrue(randomActivity.getAvailableSlotsInDay(activityDate).size() == 3);

    }

    @Test
    public void testGetAvailableDatesInRangeEmptySchedule() throws Exception {
        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        List<TimeSlot> resultList = TimeSlot.findTimeSlotsByHasCapacityEqualsAndActivityTimeBetween(Boolean.TRUE, startDate, endDate).getResultList();
        assertTrue(resultList.size() == 0);
    }

    @Test
    public void testGetAvailableDatesInRange() throws Exception {
        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        endDate.add(Calendar.DAY_OF_YEAR, 7);

        Activity randomActivity = dod.getRandomActivity();

        Calendar activityDate = new GregorianCalendar();
        randomActivity.addAvailability(activityDate, 5, 50.00);

        Calendar activityDate2 = new GregorianCalendar();
        activityDate2.add(Calendar.DAY_OF_YEAR, 1);
        randomActivity.addAvailability(activityDate2, 5, 10.00);

        Calendar activityDate3 = new GregorianCalendar();
        activityDate3.add(Calendar.DAY_OF_YEAR, 2);
        randomActivity.addAvailability(activityDate3, 5, 20.00);

        //Add a 4th that is out of range
        Calendar activityDate4 = new GregorianCalendar();
        activityDate4.add(Calendar.YEAR, 2);
        randomActivity.addAvailability(activityDate4, 5, 500.00);

        List<Calendar> foundDates = randomActivity.getAvailableDatesInRange(startDate, endDate);
        assertTrue(foundDates.size() == 3);
    }

    @Test
    public void testBookActivity() throws Exception {
        Activity randomActivity = dod.getRandomActivity();

        Calendar activityDate = new GregorianCalendar();
        randomActivity.addAvailability(activityDate, 1, 50.00);
        assertTrue(randomActivity.getAvailableSlotsInDay(activityDate).size() == 1);

        randomActivity.bookActivity(activityDate);
        assertTrue(randomActivity.getAvailableSlotsInDay(activityDate).size() == 0);

    }

    @Test(expected = IllegalStateException.class)
    public void testOverBookActivity() throws Exception {
        Activity randomActivity = dod.getRandomActivity();

        Calendar activityDate = new GregorianCalendar();
        randomActivity.addAvailability(activityDate, 1, 50.00);
        assertTrue(randomActivity.getAvailableSlotsInDay(activityDate).size() == 1);

        randomActivity.bookActivity(activityDate);
        assertTrue(randomActivity.getAvailableSlotsInDay(activityDate).size() == 0);

        randomActivity.bookActivity(activityDate);
    }


}
