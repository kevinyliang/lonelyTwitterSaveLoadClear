package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;

	//create list of tweets so can save and load this
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	//CREATE ADAPTER
	private ArrayAdapter<Tweet> adapter;
	/** Called when the activity is first created. */
	@Override
	// called only once
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); //super refers to the parents, calls its onCreate
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
		Button clearButton = (Button)findViewById(R.id.clear);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) { // called when save button is clicked created on create
											// not created again
				setResult(RESULT_OK);
				String text = bodyText.getText().toString(); // bodyText is the element found by findviewById
															// It also gets the text and converts it to string
				Tweet latestTweet = new NormalTweet(text);
				importantTweet latestImportantTweet= new importantTweet(text);
				//latestTweet.setMessage(latestTweet.getMessage()+"!"); will prompt saying error not handled and not catch
					//wrap in try/catch
				// Will now populate the list
				tweets.add(latestTweet);
				adapter.notifyDataSetChanged();
				saveInFile();
				/*finish();*/

			}
		});
		clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				tweets.clear();
				adapter.notifyDataSetChanged();
				FileOutputStream fos = null;
				try {
					fos = openFileOutput(FILENAME,
                            Context.MODE_PRIVATE);
					Gson gson = new Gson(); // use this to parse list into Json file
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
					gson.toJson(tweets,out);
					out.flush();
					fos.close();
				} catch (FileNotFoundException e) {
					throw new RuntimeException();
				} catch (IOException e) {
					throw new RuntimeException();
				}

				oldTweetsList.setAdapter(adapter);
			}
		});
	}
	// called many times (when activity is stopped not destroyed)
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		adapter = new ArrayAdapter<Tweet>(this,R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			Gson gson = new Gson();
			Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType(); // tells what type
																				// gson will parse into
			//site it "Took from URL on DATE
			tweets = gson.fromJson(in,listType);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			tweets = new ArrayList<Tweet>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE); // output stream that can write to this file
											//MODE_APPEND adds to the end of file
											// since we have entire list to write, use MODE_PRIVATE OR 0
			Gson gson = new Gson(); // use this to parse list into Json file
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
			gson.toJson(tweets,out);
			out.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();// stops the app right away and can look at log
										//printing stack trace and keep running is not recommended
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

}