package com.brett.schedulingapi;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;

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
    private Schedule schedule;
}
