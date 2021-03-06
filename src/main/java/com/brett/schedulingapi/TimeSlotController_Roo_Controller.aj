// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.brett.schedulingapi;

import com.brett.schedulingapi.Activity;
import com.brett.schedulingapi.TimeSlot;
import com.brett.schedulingapi.TimeSlotController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect TimeSlotController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String TimeSlotController.create(@Valid TimeSlot timeSlot, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, timeSlot);
            return "timeslots/create";
        }
        uiModel.asMap().clear();
        timeSlot.persist();
        return "redirect:/timeslots/" + encodeUrlPathSegment(timeSlot.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String TimeSlotController.createForm(Model uiModel) {
        populateEditForm(uiModel, new TimeSlot());
        return "timeslots/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String TimeSlotController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("timeslot", TimeSlot.findTimeSlot(id));
        uiModel.addAttribute("itemId", id);
        return "timeslots/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String TimeSlotController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("timeslots", TimeSlot.findTimeSlotEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) TimeSlot.countTimeSlots() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("timeslots", TimeSlot.findAllTimeSlots(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "timeslots/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String TimeSlotController.update(@Valid TimeSlot timeSlot, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, timeSlot);
            return "timeslots/update";
        }
        uiModel.asMap().clear();
        timeSlot.merge();
        return "redirect:/timeslots/" + encodeUrlPathSegment(timeSlot.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String TimeSlotController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, TimeSlot.findTimeSlot(id));
        return "timeslots/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String TimeSlotController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        TimeSlot timeSlot = TimeSlot.findTimeSlot(id);
        timeSlot.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/timeslots";
    }
    
    void TimeSlotController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("timeSlot_activitytime_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void TimeSlotController.populateEditForm(Model uiModel, TimeSlot timeSlot) {
        uiModel.addAttribute("timeSlot", timeSlot);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("activitys", Activity.findAllActivitys());
    }
    
    String TimeSlotController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
