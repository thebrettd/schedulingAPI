package com.brett.schedulingapi;

/**
 * Created by brett on 2/8/14.
 */
public class RangeQuery {

    private String startDate;
    private String endDate;

    public RangeQuery(){}

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
