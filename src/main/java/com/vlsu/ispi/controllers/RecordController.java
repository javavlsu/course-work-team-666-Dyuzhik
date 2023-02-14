package com.vlsu.ispi.controllers;

import com.vlsu.ispi.models.*;
import com.vlsu.ispi.models.Record;
import com.vlsu.ispi.services.RecordService;
import com.vlsu.ispi.services.ServiceService;
import com.vlsu.ispi.services.UserServiceImpl;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
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

    @GetMapping("/choose/{service_id}")
    public String chooseDate(@PathVariable("service_id") int id, Model model) throws ParseException {
        User db_user = userServiceImpl.getCurrentAuthUser();
        if (db_user != null) {
            model.addAttribute("formatter", new SimpleDateFormat("dd.MM.yyyy"));
            model.addAttribute("dates", recordService.getFreeDate(id));
            model.addAttribute("service_id", id);
            return "record/choose_date";
        }
        return "error/not_auth";
    }

    @GetMapping("/add/{service_id}")
    public String create(@ModelAttribute("record") Record record, @RequestParam String chDate, Model m, @PathVariable("service_id") int id) {
        User db_user = userServiceImpl.getCurrentAuthUser();
        if (db_user != null) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                Date date = formatter.parse(chDate);

                m.addAttribute("d", date);
                m.addAttribute("chDate", chDate);
                m.addAttribute("cost", serviceService.findOne(id).getCost());
                Calendar start = Calendar.getInstance();
                m.addAttribute("times", recordService.getFreeTime(date, id));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            m.addAttribute("service_id", id);
            return "record/create";
        }
        return "error/not_access";
    }

    @PostMapping("/add/{service_id}/{chDate}")
    public String add(@ModelAttribute("record") Record record,
                      Model m, @PathVariable("service_id") int serviceId, @PathVariable("chDate") String date) {

        User db_user = userServiceImpl.getCurrentAuthUser();
        if (db_user != null) {
            if (record.getBarberID() == -1) return "record/create";
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                Date newDate = formatter.parse(date);
                recordService.save(record, serviceId, newDate);
            } catch (ParseException e) {
                e.printStackTrace();
                return "error/not_auth";
            }

            m.addAttribute("time", record.getTime());
            return "record/excellent";
        }
        return "error/not_access";
    }

    @GetMapping("/index")
    public String index(Model model) throws ParseException {
        User db_user = userServiceImpl.getCurrentAuthUser();
        if (db_user != null) {
            model.addAttribute("records", recordService.getAll(db_user, "client"));
            model.addAttribute("status", "client");
            return "record/index";
        }
        return "error/not_auth";
    }

    @PostMapping("/delete/{st}/{id}")
    public String delete(@PathVariable("st") String status, @PathVariable("id") int id, Model model) {
        User user = userServiceImpl.getCurrentAuthUser();
        if (user != null) {
            recordService.delete(id);
            if (status.equals("client"))
                return "redirect:/record/index";
            return "redirect:/record/forBarbers";
        }
        return "error/not_auth";
    }

    @GetMapping("/forBarbers")
    public String indexBarber(Model model) throws ParseException {
        User user = userServiceImpl.getCurrentAuthUser();
        if (user != null) {
            for (Role role : user.getRoles()) {
                if ((role.getId() == 2) || (role.getId() == 4)) {
                    model.addAttribute("status", "barber");
                    model.addAttribute("records", recordService.getAll(user, "barber"));
                    return "record/index";
                }
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @GetMapping("/waiting")
    public String wait(Model model) throws ParseException {
        User user = userServiceImpl.getCurrentAuthUser();
        if (user != null) {
            for (Role role : user.getRoles()) {
                if ((role.getId() == 2) || (role.getId() == 4)) {
                    model.addAttribute("records", recordService.getAll(user, "wait"));
                    model.addAttribute("status", "wait");
                    return "record/index";
                }
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @PostMapping("/sub/{id}")
    public String submit(@PathVariable("id") int id) {
        User user = userServiceImpl.getCurrentAuthUser();
        if (user != null) {
            for (Role role : user.getRoles()) {
                if ((role.getId() == 2) || (role.getId() == 4)) {
                    recordService.submit(id);
                    return "redirect:/record/forBarbers";
                }
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @GetMapping("/archive/{status}")
    public String archive(@PathVariable("status") String status, Model model) throws ParseException {
        User user = userServiceImpl.getCurrentAuthUser();
        if (user != null) {
            for (Role role : user.getRoles()) {
                if ((((role.getId() == 2) || (role.getId() == 4)) && (status.equals("ar_barber"))) || (status.equals("ar_client"))) {
                    model.addAttribute("records", recordService.getAll(user, status));
                    model.addAttribute("status", status);
                    return "record/index";
                }
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }
}
