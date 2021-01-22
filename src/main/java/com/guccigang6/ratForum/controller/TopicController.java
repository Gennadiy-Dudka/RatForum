package com.guccigang6.ratForum.controller;

import com.guccigang6.ratForum.entity.Comment;
import com.guccigang6.ratForum.entity.Topic;
import com.guccigang6.ratForum.exceptions.PageNotFoundException;
import com.guccigang6.ratForum.service.TopicService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/topic")
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/create")
    public ModelAndView openTopicCreation(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("topic", new Topic());
        mv.setViewName("createTopicView");
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView createTopic(@ModelAttribute("topic") Topic topic){
        ModelAndView mv = new ModelAndView();
        Topic savedTopic = topicService.saveTopic(topic);
        mv.setViewName("redirect:/topic/" + savedTopic.getTopicId());
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView openTopic(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView();
        Topic topic;
        try{
            topic = topicService.getTopic(id);
        }catch (PageNotFoundException e){
            mv.setViewName("redirect:/home");
            return mv;
        }
        mv.addObject("topic", topic);
        Hibernate.initialize(topic.getComments());
        mv.addObject("comments", topic.getComments());
        mv.addObject("comment", new Comment());
        mv.setViewName("topicPageView");
        return mv;
    }

    @PostMapping("/createComment/{topicId}")
    public ModelAndView createComment(@ModelAttribute("comment") Comment comment,
                                      @PathVariable("topicId") Long topicId){
        ModelAndView mv = new ModelAndView();
        topicService.saveComment(comment, topicId);
        mv.setViewName("redirect:/topic/"+topicId);
        return mv;
    }
}