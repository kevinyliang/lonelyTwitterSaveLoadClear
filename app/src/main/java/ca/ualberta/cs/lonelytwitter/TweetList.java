package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by kliang on 1/26/16.
 * class that contains all the tweets in a list
 */
// Note since have test class, if any change here, needs to change in the test class as well
public class TweetList {
    private ArrayList<Tweet> tweets =new ArrayList<Tweet>();

    public void add(Tweet tweet){

        if(tweets.contains(tweet)){
            throw new IllegalArgumentException("added duplicate tweets");
        }
        else {
            tweets.add(tweet);
        }
    }

    public boolean hasTweet(Tweet tweet){

        return tweets.contains(tweet);
    }

    public ArrayList<Tweet> getTweet(){
        return tweets;
    }
    public void delete(Tweet tweet) {
        tweets.remove(tweet);
    }
    public int getCount(){
        return tweets.size();
    }

}
