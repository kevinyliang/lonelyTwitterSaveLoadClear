package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by kliang on 1/12/16.
 */
public abstract class Tweet {
    protected Date date;
    protected String message;

    public abstract Boolean checkImportant();


    public Tweet(String message, Date date) {
        this.message = message;
        this.date = date;

    }

    public Tweet(String message) {
        this.message = message;
        this.date=new Date();

    }


    public void setMessage(String message) throws Exception{
        if(message.length()>140){
            throw new TweetTooLongException();
        }
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public String toString(){
        return date.toString() + " | " + message; // override so the toString will display what we want
    }
}
