// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.brett.schedulingapi;

import com.brett.schedulingapi.Activity;
import com.brett.schedulingapi.Vendor;

privileged aspect Activity_Roo_JavaBean {
    
    public String Activity.getName() {
        return this.name;
    }
    
    public void Activity.setName(String name) {
        this.name = name;
    }
    
    public Vendor Activity.getOwner() {
        return this.owner;
    }
    
    public void Activity.setOwner(Vendor owner) {
        this.owner = owner;
    }
    
    public Integer Activity.getDuration() {
        return this.duration;
    }
    
    public void Activity.setDuration(Integer duration) {
        this.duration = duration;
    }
    
}
