// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.brett.schedulingapi;

import com.brett.schedulingapi.Activity;
import com.brett.schedulingapi.Vendor;
import java.util.Set;

privileged aspect Vendor_Roo_JavaBean {
    
    public String Vendor.getName() {
        return this.name;
    }
    
    public void Vendor.setName(String name) {
        this.name = name;
    }
    
    public Set<Activity> Vendor.getActivities() {
        return this.activities;
    }
    
    public void Vendor.setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
    
}
