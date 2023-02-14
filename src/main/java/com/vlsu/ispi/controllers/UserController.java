package com.vlsu.ispi.controllers;

import com.vlsu.ispi.classes.ArrayCheckRoles;
import com.vlsu.ispi.models.Role;

import java.util.List;

import com.vlsu.ispi.models.User;
import com.vlsu.ispi.repositories.RoleRepository;
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

import javax.validation.Valid;
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
    private RoleRepository roleRepository;

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

        return "redirect:/service/index/0";
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
    public String profile(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();

        com.vlsu.ispi.models.User db_user = userService.findByUsername(username);
        if (db_user != null) {
            Set<Role> roles = db_user.getRoles();
            for (Role role : roles) {
                if (role.getId() == 3) model.addAttribute("status", "admin");
                if ((role.getId() == 2)||(role.getId() == 4)) model.addAttribute("barber","true");
            }
            model.addAttribute("user", db_user);
            return "user/profile";
        } else return "error/not_auth";
    }

    @GetMapping("/barbers/{num}")
    public String barbers(@PathVariable int num, Model model) {
        model.addAttribute("barbers", userServiceImpl.getBarberPage(num));
        if (userServiceImpl.findAll().size() <= num * 9 + 9)
            model.addAttribute("end", "true");
        return "barbers/index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("users", userServiceImpl.findAll());
        model.addAttribute("checkRoles", new ArrayCheckRoles());
        return "user/index";
    }

    @PostMapping("/modifyRoles")
    public String modifyRoles(@ModelAttribute("checkRoles") ArrayCheckRoles checkRoles, Model model) {
        userServiceImpl.modifyRoles(checkRoles.getAddRoles(),"add");
        userServiceImpl.modifyRoles(checkRoles.getDelRoles(),"del");
        return "redirect: /index";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        userServiceImpl.delete(id);
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        com.vlsu.ispi.models.User db_user = userService.findByUsername(username);
        if (db_user != null) {
            model.addAttribute("users",userServiceImpl.findAll());
            return "user/index";
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("user") User user, Model model) {
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);

        return "user/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()){return "user/create";}
        userServiceImpl.save(user);
        return "redirect:/index";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImpl.findOne(id));
        return "user/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()){
            return "user/edit";
        }
        userServiceImpl.update(id, user);
        return "redirect:/profile";
    }

    @GetMapping("/updatePassword/{id}")
    public String updatePassword(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImpl.findOne(id));
        return "user/editPassword";
    }

    @PostMapping("/updatePassword/{id}")
    public String updatePassword(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                                 @PathVariable("id") int id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        com.vlsu.ispi.models.User db_user = userService.findByUsername(username);
        userValidator.pass_validate(user, bindingResult, db_user);
        if (bindingResult.hasErrors()){
            return "user/editPassword";
        }
        String pass = user.getPassword();
        userServiceImpl.updatePassword(id, user);
        securityService.autoLogin(db_user.getUsername(), pass);
        return "redirect:/profile";
    }

    @GetMapping("/updateUsername/{id}")
    public String updateUsername(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImpl.findOne(id));
        return "user/editUsername";
    }

    @PostMapping("/updateUsername/{id}")
    public String updateUsername(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                                 @PathVariable("id") int id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        com.vlsu.ispi.models.User db_user = userService.findByUsername(username);
        userValidator.up_validate(user, bindingResult, db_user);
        if (bindingResult.hasErrors()){
            return "user/editUsername";
        }
        String pass = user.getPassword();
        userServiceImpl.updateUsername(id, user);
        securityService.autoLogin(user.getUsername(), pass);
        return "redirect:/profile";
    }

}
