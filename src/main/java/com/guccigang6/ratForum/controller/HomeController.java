package com.guccigang6.ratForum.controller;

import com.guccigang6.ratForum.security.UserAccountDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/home")
    public @ResponseBody String openHomePage(){
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if(principal instanceof UserAccountDetails){
            return ((UserAccountDetails)principal).getUsername();
        }
        return "home";
    }
}
