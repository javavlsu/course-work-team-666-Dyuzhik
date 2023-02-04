package com.vlsu.ispi.controllers;

import com.vlsu.ispi.models.*;
import com.vlsu.ispi.models.Record;
import com.vlsu.ispi.services.RecordService;
import com.vlsu.ispi.services.ServiceService;
import com.vlsu.ispi.services.UserServiceImpl;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Controller
@RequestMapping("/record")
public class RecordController {


    private final UserServiceImpl userServiceImpl;
    private final RecordService recordService;
    private final ServiceService serviceService;

    @Autowired
    public RecordController(UserServiceImpl userServiceImpl, RecordService recordService, ServiceService serviceService) {
        this.userServiceImpl = userServiceImpl;
        this.recordService = recordService;
        this.serviceService = serviceService;
    }

//    @GetMapping("/add/{service}")
//    public String create(@ModelAttribute("record") Record record, @PathVariable("service") int service, Model model) {
//        User db_user = userServiceImpl.getCurrentAuthUser();
//        if (db_user != null) {
//            model.addAttribute("barbers", recordService.getFreeBarbers(service));
//            model.addAttribute("service", service);
//            return "record/create";
//        }
//        return "error/not_auth";
//    }

    @GetMapping("/choose/{id}")
    public String chooseDate(@PathVariable("id") int id, Model model) throws ParseException {
        User db_user = userServiceImpl.getCurrentAuthUser();
        if (db_user != null) {
            model.addAttribute("formatter",new SimpleDateFormat("dd.MM.yyyy"));
            model.addAttribute("dates", recordService.getFreeDate(id));
            model.addAttribute("id", id);
            return "record/choose_date";
        }
        return "error/not_auth";
    }

    @GetMapping("/add/{id}")
    public String create(@ModelAttribute("record") Record record,@RequestParam String chDate, Model m, @PathVariable("id") int id) {
        User db_user = userServiceImpl.getCurrentAuthUser();
        if (db_user != null) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                Date date = formatter.parse(chDate);
                m.addAttribute("d",date);
                m.addAttribute("chDate",chDate);
                m.addAttribute("cost",serviceService.findOne(id).getCost());
                Calendar start = Calendar.getInstance();
                m.addAttribute("times",recordService.getFreeTime(start.getTime(),id));
            }catch (ParseException e) {
                e.printStackTrace();
            }

            m.addAttribute("id",id);
            return "record/create";
        }
        return "error/not_access";
    }

    @PostMapping("/add/{id}/{chDate}")
    public String add(@ModelAttribute("record") Record record,
                      Model m, @PathVariable("id") int id, @PathVariable("chDate") String date) {

        User db_user = userServiceImpl.getCurrentAuthUser();
        if (db_user != null) {
            if (record.getBarberID() == -1) return "record/create";
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                Date newDate = formatter.parse(date);
                recordService.save(record, id, newDate);
            }catch (ParseException e) {
                e.printStackTrace();
                return "error/not_auth";
            }

            m.addAttribute("time",record.getTime());
            return "record/excellent";
        }
        return "error/not_access";
    }
}
