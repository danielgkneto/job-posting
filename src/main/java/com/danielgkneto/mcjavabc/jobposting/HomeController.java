package com.danielgkneto.mcjavabc.jobposting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String jobList(Model model){
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @RequestMapping("/myjobs")
    public String userJobList(Model model){
        model.addAttribute("jobs", jobRepository.findAll());
        return "myjobs";
    }

    @GetMapping("/add")
    public String addJob(Model model){
        model.addAttribute("job", new Job());
        return "jobform";
    }

    @PostMapping("/processjob")
    public String saveJob(@ModelAttribute Job job, Principal principal /*, @RequestParam(name = "postedDate")
            String postedDate */){
/*
        String pattern = "yyyy-MM-dd";
        System.out.println("before - " + postedDate);
        try {
//            String formattedDate = date.substring(1);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date realDate = simpleDateFormat.parse(postedDate);
            System.out.println("after - " + realDate);
            job.setPostedDate(realDate);
        }
        catch (java.text.ParseException e){
            e.printStackTrace();
        }
*/

        job.setUser(userRepository.findByUsername(principal.getName()));
        jobRepository.save(job);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showJob(@PathVariable("id") long id, Model model){
        model.addAttribute("job", jobRepository.findById(id).get());
        return "showjob";
    }

    @RequestMapping("/update/{id}")
    public String updateJob(@PathVariable("id") long id, Model model){
        model.addAttribute("job", jobRepository.findById(id).get());
        return "jobform";
    }

    @RequestMapping("/delete/{id}")
    public String delJob(@PathVariable("id") long id){
        jobRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/processsearch")
    public String searchResult(Model model,@RequestParam(name="search") String search) {
        String[] keywords = search.split(" ");
        Set<Job> jobs = new HashSet<Job>();

        for (int i = 0; i < keywords.length; i++) {
            jobs.addAll(jobRepository.findByTitleContainingIgnoreCaseOrCompanyContainingIgnoreCase(keywords[i], keywords[i]));
        }
        model.addAttribute("jobs", jobs);
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "registration";
        }
        else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created.");
        }
        return "redirect:/";
    }

/*    @RequestMapping("/secure")
    public String secure(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }*/

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
