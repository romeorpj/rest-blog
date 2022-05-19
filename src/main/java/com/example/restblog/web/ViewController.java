package com.example.restblog.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;


@Controller
public class ViewController {

    @RequestMapping({"/", "/about", "/posts", "/login", "/home", "/register","/user"})
    public String showView(){
        return "forward:/index.html";
    }
}