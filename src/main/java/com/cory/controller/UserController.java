package com.cory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import com.cory.model.User;
import com.cory.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/add")
    public String addUserSubmit(@ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "add-user"; // This will return to the form if there are any validation errors.
        }
        userRepository.save(user);
        return "redirect:/users/";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable int id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id"));
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/edit/{id}")
    public String editUserSubmit(@PathVariable int id, @ModelAttribute User user) {
        user.setId(id);
        userRepository.save(user);
        return "redirect:/";
    }
  
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "redirect:/";
    }
}