package com.guccigang6.ratForum.controller;

import com.guccigang6.ratForum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final TopicService topicService;

    @Autowired
    public HomeController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/home")
    public ModelAndView openHomePage(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("topics", topicService.getTopics());
        mv.setViewName("homeView");
        return mv;
    }
}
