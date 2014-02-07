package com.brett.schedulingapi;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/timeslots")
@Controller
@RooWebScaffold(path = "timeslots", formBackingObject = TimeSlot.class)
public class TimeSlotController {
}
