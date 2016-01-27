package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;


import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kliang on 1/26/16.
 * Test for the new class
 */

//DIfference between here and unitTest

//ActivityInstrumentationTestCase2 has all basic things, but others as well
    //TestCases are anything that starts with tests, other methods can exist but ignored
    //if not preceeded by test

    //Note Junit with tests are not necessarily run in order so all tests should be self contained
//Great for finding bugs. If found one, write a test for it and then can use it for other things
    //This bug would not be made again
    //Called REGRESSION TESTING
public class TweetListTest extends ActivityInstrumentationTestCase2{
    public TweetListTest(){
        //We make a test with the starting point of app so have access to everything.
        super(LonelyTwitterActivity.class);
    }
    //Here are the tests
    public void testAddTWeet(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Test tweet");

        tweets.add(tweet);
        assertTrue(tweets.hasTweet(tweet));
        try{
            tweets.add(tweet);
        }catch(IllegalArgumentException e){
            assertTrue(Boolean.TRUE);
        }

    }

    public void testHasTweet(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Hello");

        assertFalse(tweets.hasTweet(tweet));

        tweets.add(tweet);
        tweets.hasTweet(tweet);

        assertTrue(tweets.hasTweet(tweet));

    }

    public void testDeleteTweet(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Test tweet");

        tweets.add(tweet);
        tweets.delete(tweet);

        assertFalse(tweets.hasTweet(tweet));

    }
    public void testGetTweet(){
        TweetList tweets = new TweetList();
        ArrayList<Tweet> tweetHere=new ArrayList<Tweet>();
        Date date;
        date = new Date(1000,11,11);
        Date date1;
        date1=new Date(2000,22,22);
        Tweet tweet = new NormalTweet("Test tweet", date);
        Tweet tweet1 = new NormalTweet("new",date1);
        ArrayList<Date> dates = new ArrayList<Date>();

        dates.add(tweet.getDate());
        dates.add(tweet1.getDate());

        tweets.add(tweet);
        tweets.add(tweet1);

        ArrayList<Tweet> returnedList=tweets.getTweet();
        int size=returnedList.size();
        int i =0;
        while(i<size){
            assertEquals(returnedList.get(i).getDate(),dates.get(i));
            i=i+1;
        }
    }

    public void testgetCount(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Test tweet");
        Tweet tweet1 = new NormalTweet("another");
        tweets.add(tweet);
        tweets.add(tweet1);

        int returnedCount=tweets.getCount();

        assertEquals(returnedCount,2);
    }

}
