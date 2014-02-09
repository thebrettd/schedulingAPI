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

@RequestMapping("/vendors")
@Controller
@RooWebScaffold(path = "vendors", formBackingObject = Vendor.class)
public class VendorController {

    @RequestMapping(value="/addVendor", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    public String addSchedule(@RequestBody AddVendor vendor) throws ParseException {

        Vendor v = new Vendor();
        v.setName(vendor.getName());

        v.persist();

        return "Added vendor " + vendor.getName();
    }

}
