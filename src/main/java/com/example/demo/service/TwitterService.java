package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import java.util.Optional;

@Service
public class TwitterService {

    @Value("${twitter.consumerKey}")
    private String twitterConsumerKey;

    @Value("${twitter.consumerSecret}")
    private String twitterConsumerSecret;

    @Value("${twitter.accessToken}")
    private String twitterAccessToken;

    @Value("${twitter.accessTokenSecret}")
    private String twitterAccessTokenSecret;


    public TwitterService() {
    }

    public Optional<String> postStatus(String message) {
        try {
            ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
            configurationBuilder.setTweetModeExtended(true);
            configurationBuilder.setDebugEnabled(true)
                    .setOAuthConsumerKey(twitterConsumerKey)
                    .setOAuthConsumerSecret(twitterConsumerSecret)
                    .setOAuthAccessToken(twitterAccessToken)
                    .setOAuthAccessTokenSecret(twitterAccessTokenSecret);
            TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
            Twitter twitter = twitterFactory.getInstance();
            Status status = twitter.updateStatus(message);
            return Optional.of(String.valueOf(status.getId()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}