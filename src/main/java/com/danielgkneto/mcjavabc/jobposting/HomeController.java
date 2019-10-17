package com.danielgkneto.mcjavabc.jobposting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/docs")
    public String docs(Model model){
        return "docs";
    }

    @GetMapping("/add")
    public String addJob(Model model){
        model.addAttribute("job", new Job());
        return "jobform";
    }

    @PostMapping("/processjob")
    public String saveJob(@ModelAttribute Job job /*, @RequestParam(name = "postedDate")
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

        jobRepository.save(job);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showJob(@PathVariable("id") long id, Model model){
        model.addAttribute("job", jobRepository.findById(id).get());
        return "show";
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
/*
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

    @RequestMapping("/secure")
    public String secure(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }

    @RequestMapping("/")
    public String carList(Model model){
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("categories")
    public String categoryList(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

*/
/*    @RequestMapping("/docs")
    public String docs(Model model){
        return "docs";
    }*//*


    @GetMapping("/addcar")
    public String addCar(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }

    @GetMapping("/addcategory")
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    @PostMapping("/processcar")
    public String saveCar(@ModelAttribute Car car, Principal principal){
        car.setUser(userRepository.findByUsername(principal.getName()));
        carRepository.save(car);
        return "redirect:/";
    }

    @PostMapping("/processcategory")
    public String saveCategory(@ModelAttribute Category category){
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @RequestMapping("/detail/{id}")
    public String showCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "showcar";
    }

    @RequestMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") long id){
        carRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/categoryupdate/{id}")
    public String updateCategory(@PathVariable("id") long id, Model model){
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "categoryform";
    }

    @RequestMapping("/categorydelete/{id}")
    public String deleteCategory(@PathVariable("id") long id, Model model){
        try {
            categoryRepository.deleteById(id);
            return "redirect:/categories";
        }
        catch (Exception e) {
            model.addAttribute("error_message", "You can't delete this category because there is at least one car registered under it.");
            model.addAttribute("return_link", "/categories");
            return "error";
        }
    }

    @RequestMapping("/pickcategory/{chosencategory}")
    public String pickCategory(@PathVariable("chosencategory") long chosenCategory, Model model){
        Category c = categoryRepository.findById(chosenCategory).get();
        model.addAttribute("cars", carRepository.findByCategory(c));
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }

    @PostMapping("/processsearch")
    public String searchResult(Model model,@RequestParam(name="search") String search) {
        String[] keywords = search.split(" ");
        Set<Car> cars = new HashSet<Car>();

        for (int i = 0; i < keywords.length; i++) {
            cars.addAll(carRepository.findByManufacturerContainingIgnoreCaseOrModelContainingIgnoreCase(keywords[i], keywords[i]));
        }
        model.addAttribute("cars", cars);
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }

    @GetMapping("/userprofile")
    public String showProfile(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "userprofile";
    }

*/
}
