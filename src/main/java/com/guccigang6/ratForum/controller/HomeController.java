package com.guccigang6.ratForum.controller;

import com.guccigang6.ratForum.entity.Topic;
import com.guccigang6.ratForum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final TopicService topicService;

    @Autowired
    public HomeController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/home")
    public ModelAndView openHomePage(@RequestParam(name="page", defaultValue = "0", required = false)int page ){
        ModelAndView mv = new ModelAndView();
        Slice<Topic> topics = topicService.getPaginatedTopics(page);
        mv.addObject("topics", topics);
        mv.setViewName("homeView");
        return mv;
    }
}
