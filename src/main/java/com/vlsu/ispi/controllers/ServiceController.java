package com.vlsu.ispi.controllers;

import com.vlsu.ispi.models.Role;
import com.vlsu.ispi.models.Service;
import com.vlsu.ispi.models.User;
import com.vlsu.ispi.services.ServiceService;
import com.vlsu.ispi.services.UserService;
import com.vlsu.ispi.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/index/{num}")
    public String index(@PathVariable int num, Model model) {
        model.addAttribute("service", serviceService.getPage(num));
        if (serviceService.findAll().size() <= num * 9 + 9)
            model.addAttribute("end", "true");
        return "service/index";
    }

    @GetMapping("/admin/{num}")
    public String admin(@PathVariable int num, Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        com.vlsu.ispi.models.User db_user = userService.findByUsername(username);
        if (db_user != null) {
            Set<Role> roles = db_user.getRoles();
            for (Role role : roles)
                if (role.getId() == 3) model.addAttribute("status", "admin");

        }
        model.addAttribute("service", serviceService.getPage(num));
        if (serviceService.findAll().size() <= num * 9 + 9)
            model.addAttribute("end", "true");
        return "service/index";
    }

    @GetMapping("/add")
    public String create(@ModelAttribute("service") Service service) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        com.vlsu.ispi.models.User db_user = userService.findByUsername(username);
        if (db_user != null) {
            Set<Role> roles = db_user.getRoles();
            for (Role role : roles)
                if (role.getId() == 3) return "service/create";

        }
        return "error/not_access";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("service") @Valid Service service, BindingResult bindingResult) {
        User db_user = userServiceImpl.getCurrentAuthUser();
        if (db_user != null) {
            Set<Role> roles = db_user.getRoles();
            for (Role role : roles)
                if (role.getId() == 3) {
                    if (bindingResult.hasErrors())
                        return "service/create";
                    if (bindingResult.hasErrors()) {
                        return "service/create";
                    }
                    serviceService.save(service);
                    return "redirect:/service/admin/0";
                }

        }
        return "error/not_access";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        User db_user = userServiceImpl.getCurrentAuthUser();
        if (db_user != null) {
            Set<Role> roles = db_user.getRoles();
            for (Role role : roles)
                if (role.getId() == 3) {
                    model.addAttribute("service", serviceService.findOne(id));
                    return "service/edit";
                }

        }
        return "error/not_access";

    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("service") @Valid Service service, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "service/edit";
        serviceService.update(id, service);
        return "redirect:/service/admin/0";

    }

    @PostMapping("/delete/{service_id}")
    public String delete(@PathVariable int service_id) {
        User db_user = userServiceImpl.getCurrentAuthUser();
        if (db_user != null) {
            Set<Role> roles = db_user.getRoles();
            for (Role role : roles)
                if (role.getId() == 3) {
                    serviceService.delete(service_id);
                    return "redirect:/service/admin/0";
                }
        }
        return "error/not_access";

    }
}
