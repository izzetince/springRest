package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by izzetince on 30/10/2016.
 */

@Controller
public class HomeController {
    @RequestMapping(value = {"/","/home"})
    public String getHomePage(){
        return "home";
    }
}
