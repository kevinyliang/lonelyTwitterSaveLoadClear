package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by kliang on 1/26/16.
 * class that contains all the tweets in a list
 */
// Note since have test class, if any change here, needs to change in the test class as well
public class TweetList {
    private ArrayList<Tweet> tweets =new ArrayList<Tweet>();

    /**
     * This adds tweets to the list, but it also checks for duplicate tweets
     * @param tweet the tweets to be added.
     * @throws IllegalArgumentException when adding duplicates
     */
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

    /**
     * This will sort the tweet list and will make it in chronological order
     * @return Return the list sorted by dates in chronological order
     */
    public ArrayList<Tweet> getTweet(){
        Collections.sort(tweets, new Comparator<Tweet>()  {
            public int compare(Tweet tweet1, Tweet tweet2) {
                Date date1 = tweet1.getDate();
                Date date2 = tweet2.getDate();
               return date1.compareTo(date2);
            }
        });
        return tweets;
    }
    public void delete(Tweet tweet) {
        tweets.remove(tweet);
    }
    public int getCount(){
        return tweets.size();
    }


}
