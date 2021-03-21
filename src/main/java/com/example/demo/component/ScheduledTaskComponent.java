package com.example.demo.component;

import com.example.demo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTaskComponent {

    @Value("${mode}")
    private boolean mode;


    private static final Logger log = LoggerFactory.getLogger(ScheduledTaskComponent.class);
    private final TwitterService twitterService;

    public ScheduledTaskComponent(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @Scheduled(fixedDelay = 5000)
    public void postTweet() {
        if (mode) {
            log.info(String.valueOf(twitterService.postStatus(createTweet()).isPresent()));
        }
    }

    private String createTweet() {
        return "Tweet " + new java.util.Date();
    }
}