package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by kliang on 1/12/16.
 */
public class importantTweet extends Tweet implements Tweetable{
    @Override
    public Boolean checkImportant() {
        return Boolean.TRUE;
    }

    public importantTweet(String message, Date date) {
        super(message, date); // supers calls the constructor for Tweet.

    }

    public importantTweet(String message) {
        super(message);

    }

    public String getMessage(){
        return "!IMPORTANT!" +this.message;
    }
    public Date getDate(){
        return this.date;
    }
}
