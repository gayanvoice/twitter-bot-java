package com.example.demo.controller;

import com.example.demo.service.TwitterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path="/")
public class MainController {

    @Value("${url}")
    private String url;

    private final TwitterService twitterService;

    public MainController(TwitterService twitterService) { this.twitterService = twitterService; }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping(path="/createTweet")
    public @ResponseBody String createTweet(HttpServletResponse response,
                                             @CookieValue(value = "disable", defaultValue = "false") String disable) {
        if(disable.equals("false")){
            if(twitterService.postStatus(tweet()).isPresent()){
                response.addCookie(new Cookie("disable", "true"));
                return "Tweet added. Go to " + url;
            } else {
                return "Error creating the Tweet";
            }
        } else {
            return "Tweet is already created " + url;
        }
    }

    public String tweet() {
        return "Tweet " + new java.util.Date();
    }
}