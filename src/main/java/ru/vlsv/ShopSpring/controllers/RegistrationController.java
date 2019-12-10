package ru.vlsv.ShopSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.vlsv.ShopSpring.entities.User;
import ru.vlsv.ShopSpring.services.UserService;
import ru.vlsv.ShopSpring.tools.SystemUser;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String showMyLoginPage(Model model) {
        model.addAttribute("systemUser", new SystemUser());
        return "registration-form";
    }

    @PostMapping("/process")
    public String processRegistrationForm(@Valid @ModelAttribute("systemUser") SystemUser systemUser, BindingResult bindingResult, Model model) {
        String username = systemUser.getUsername();
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }
        User existing = userService.findByUsername(username);
        if (existing != null) {
            // theSystemUser.setUserName(null);
            model.addAttribute("systemUser", systemUser);
            model.addAttribute("registrationError", "User with current username is already exist");
            return "registration-form";
        }
        userService.save(systemUser);
        return "registration-confirmation";
    }
}
