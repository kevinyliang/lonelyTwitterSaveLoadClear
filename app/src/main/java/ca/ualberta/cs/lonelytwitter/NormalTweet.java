package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by kliang on 1/12/16.
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
