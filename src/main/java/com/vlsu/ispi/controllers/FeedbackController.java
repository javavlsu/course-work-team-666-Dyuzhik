package com.vlsu.ispi.controllers;

import com.vlsu.ispi.models.Feedback;
import com.vlsu.ispi.models.User;
import com.vlsu.ispi.services.FeedbackService;
import com.vlsu.ispi.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final UserServiceImpl userService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, UserServiceImpl userService) {
        this.feedbackService = feedbackService;
        this.userService = userService;
    }

    @GetMapping("/add/{id}")
    public String create(@ModelAttribute("feedback") Feedback feedback, @PathVariable("id") int id, Model model) {
        User user = userService.getCurrentAuthUser();
        if (user != null) {
            if (feedbackService.isRecord(user.getId(), id)) {
                model.addAttribute("barber_id", id);
                return "feedback/create";
            }
            return "error/not_records";
        }
        return "error/not_auth";
    }

    @PostMapping("/add/{barber_id}")
    public String create(@ModelAttribute("feedback") @Valid Feedback feedback, BindingResult bindingResult, @PathVariable("barber_id") int id, Model model) {
        User user = userService.getCurrentAuthUser();
        if (user != null) {
            if (feedbackService.isRecord(user.getId(), id)) {
                if (bindingResult.hasErrors())
                    return "feedback/create";
                feedbackService.save(user, id, feedback);
                return "redirect:/feedback/index/" + id;
            }
            return "error/not_records";
        }
        return "error/not_auth";
    }

    @GetMapping("/index/{barber_id}")
    public String index(@PathVariable("barber_id") int id, Model model) {
        model.addAttribute("feedbacks", feedbackService.findAll(id));
        User user = userService.getCurrentAuthUser();
        if  (user != null){
            model.addAttribute("client_id", userService.getCurrentAuthUser().getId());
        }
        return "feedback/index";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        User user = userService.getCurrentAuthUser();
        if (user != null) {
            model.addAttribute("feedback", feedbackService.findOne(id));
            return "feedback/update";
        }
        return "error/not_auth";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("feedback") @Valid Feedback feedback, BindingResult bindingResult, @PathVariable("id") int id, Model model) {
        User user = userService.getCurrentAuthUser();
        if (user != null) {
            if (bindingResult.hasErrors())
                return "feedback/update";
            feedbackService.update(feedback,id);
            return "redirect:/feedback/index/" + feedbackService.findBarberByFeedback(id);
        }
        return "error/not_auth";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        User user = userService.getCurrentAuthUser();
        if (user != null) {
            if (!feedbackService.isRecord(user.getId(), id)) {
                int barberId = feedbackService.findBarberByFeedback(id);
                feedbackService.delete(id);

                return "redirect:/feedback/index/" + barberId;
            }
            return "error/not_records";
        }
        return "error/not_auth";
    }

}
