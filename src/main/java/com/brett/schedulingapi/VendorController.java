package com.brett.schedulingapi;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/vendors")
@Controller
@RooWebScaffold(path = "vendors", formBackingObject = Vendor.class)
public class VendorController {
}
