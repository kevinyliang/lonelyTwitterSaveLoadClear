package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by kliang on 1/12/16.
 * Interface used by all tweets
 */
public interface Tweetable {
    public String getMessage();
    public Date getDate();
}
