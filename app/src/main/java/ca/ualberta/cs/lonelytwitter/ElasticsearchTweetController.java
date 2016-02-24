package ca.ualberta.cs.lonelytwitter;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by esports on 2/17/16.
 */
public class ElasticsearchTweetController {
    //Just so it is used to add/get tweet from elasticsearch
    private static JestDroidClient client;

    //TODO: A function that gets tweets
    //static function works with class. so just class.method()
    public static class GetTweetsTask extends AsyncTask<String, Void,ArrayList<Tweet>>{

        @Override
        protected ArrayList<Tweet> doInBackground(String... search_string) {
            verifyClient();


            //start initial array lsit empty.
            ArrayList<Tweet> tweets = new ArrayList<Tweet>();
            //ASSUMES ONLY FIRST SEARCH TERM!!
            Search search = new Search.Builder(search_string[0]).
                                                addIndex("testing").
                                                addType("tweet").build();

            try {
                SearchResult execute = client.execute(search);
                if (execute.isSucceeded()){
                    //return list of things
                    List<NormalTweet> returned_tweets = execute.getSourceAsObjectList(NormalTweet.class);
                    tweets.addAll(returned_tweets);
                }
            } catch (IOException e) {
                Log.i("TODO", "SEARCH PROBLEMS");
            }

            return tweets;
        }
    }
    public static class SearchTask extends AsyncTask<String , Void, ArrayList<Tweet>>{


        @Override
        protected ArrayList<Tweet> doInBackground(String... queryArray) {
            verifyClient();
            String query = "{" +
                    "    \"query\": {" +
                    "        \"match\" :{ \"message\":\"" + queryArray[0]+ "\""+
                    "    }" +
                    "}}";
            ArrayList<Tweet> tweets = new ArrayList<Tweet>();
            Search search = new Search.Builder(query).
                                    addIndex("testing").
                                    addType("tweet").build();
            //start initial array lsit empty.
            try {
                SearchResult execute = client.execute(search);
                if (execute.isSucceeded()){
                    //return list of things
                    List<NormalTweet> returned_tweets = execute.getSourceAsObjectList(NormalTweet.class);
                    tweets.addAll(returned_tweets);
                }
            } catch (IOException e) {
                Log.i("TODO", "SEARCH PROBLEMS");
            }

            return tweets;
        }
    }
        //TODO: A function that adds a tweet
    //change void to other so we can verify in assignmnet
    //used static just so it is easy to use. Not need to make the object everytime
    // Does not work as cannot have multiple thread with UI
/*    public static void addTweet(Tweet tweet){
        verifyClient();
        //Indexing Document for JestDroid
        //Filling in index and type (just like the pdf with elastic search)
        Index index = new Index.Builder(tweet).index("testing").type("tweet").build();
        try {
            DocumentResult result = client.execute(index);
            if (result.isSucceeded()){
                //Set Id for tweet, can find and edit in elastic search
                tweet.setId(result.getId());
            }
            //Can also use get id,get index,get type
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    public static class AddTweetTask extends AsyncTask<NormalTweet,Void, Void>{
        //the ... makes tweets into an array of NormalTweets
        @Override
        protected Void doInBackground(NormalTweet... tweets) {
            verifyClient();
            //Since AsyncTasks work on arrays, we need to work with arrays as well
            for (int i = 0; i < tweets.length; i++){
                NormalTweet tweet = tweets[i];
                Index index = new Index.Builder(tweet).index("testing").type("tweet").build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()){
                        //Set Id for tweet, can find and edit in elastic search
                        tweet.setId(result.getId());
                    }
                    //Can also use get id,get index,get type
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;

        }
    }


    //Helper function
    public static void verifyClient(){
        //verify that "client" exists and if it does not make it.
        //This had to be done the other functions anyway. Just make a helper function.

        if (client == null ){
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient)factory.getObject();
        }
    }
}
