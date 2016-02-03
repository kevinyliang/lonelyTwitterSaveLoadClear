package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by kliang on 1/12/16.
 * This is the regular tweet class. It has a few default getters/setters
 */
public class NormalTweet extends Tweet implements Tweetable {
    public NormalTweet(String message, Date date) {
        super(message, date);
    }

    public NormalTweet(String message) {
        super(message);
    }
    public Boolean checkImportant(){
        return Boolean.FALSE;
    }
    public String getMessage(){
        return this.message;
    }
    public Date getDate(){
        return this.date;
    }
}
