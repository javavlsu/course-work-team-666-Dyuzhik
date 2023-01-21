package com.vlsu.ispi.controllers;

import com.vlsu.ispi.models.Role;
import java.util.logging.Logger;
import com.vlsu.ispi.models.User;
import com.vlsu.ispi.services.SecurityService;
import com.vlsu.ispi.services.UserService;
import com.vlsu.ispi.services.UserServiceImpl;
import com.vlsu.ispi.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return  "redirect:/service/index/0";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();

        com.vlsu.ispi.models.User db_user = userService.findByUsername(username);
        if (db_user !=null){
            Set<Role> roles = db_user.getRoles();
            for (Role role: roles){
                if (role.getId() == 3) model.addAttribute("status","admin");
            }
            model.addAttribute("user",db_user);
            return "user/profile";
        }
        else return "error/not_auth";
    }

    @GetMapping("/barbers/{num}")
    public String barbers(@PathVariable int num, Model model){
        model.addAttribute("barbers",userServiceImpl.findAllBarbers(num));
        if (userServiceImpl.findAll().size()<=num*9+9)
            model.addAttribute("end","true");
        return "barbers/index";
    }

}
