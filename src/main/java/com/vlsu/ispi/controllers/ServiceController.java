package com.vlsu.ispi.controllers;

import com.vlsu.ispi.models.Role;
import com.vlsu.ispi.services.ServiceService;
import com.vlsu.ispi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private UserService userService;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/index/{num}")
    public String index (@PathVariable int num, Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        com.vlsu.ispi.models.User db_user = userService.findByUsername(username);

        if (db_user != null){
            Set<Role> roles = db_user.getRoles();
            for (Role role: roles)
                if (role.getId() == 3) model.addAttribute("status","admin");

        }
        model.addAttribute("service", serviceService.getPage(num));
        if (serviceService.findAll().size()<=num*9+9)
            model.addAttribute("end","true");
        return "service/index";
    }
}
