package com.guccigang6.ratForum.controller;

import com.guccigang6.ratForum.entity.UserAccount;
import com.guccigang6.ratForum.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserAccountController {

    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/register")
    public ModelAndView openRegistration(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",new UserAccount());
        mv.setViewName("registerView");
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("user") UserAccount userAccount,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        String err;
        try{
            String password = userAccount.getPassword();
            String username = userAccount.getUsername();
            userAccountService.registerUser(userAccount, confirmPassword);
            request.login(username, password);
            mv.setViewName("redirect:/home");
        }catch (Exception e){
            err = e.getMessage();
            mv.addObject("user", new UserAccount());
            mv.addObject("error", err);
            mv.setViewName("registerView");
        }
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView openLogin(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",new UserAccount());
        mv.setViewName("loginView");
        return mv;
    }
}
