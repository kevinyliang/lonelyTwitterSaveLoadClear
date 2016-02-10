package ca.ualberta.cs.lonelytwitter;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.TextView;

/**
 * Created by sajediba on 2/8/16.
 */
public class IntentReaderActivityTest extends ActivityInstrumentationTestCase2{

    public IntentReaderActivityTest() {
        super(IntentReaderActivity.class);
    }

    //
    //
    public void testSendText(){
        Intent intent = new Intent();
        intent.putExtra(IntentReaderActivity.TEXT_TO_TRANSFORM_KEY, "message 1");

        //What does setActivityIntent(intent) do?
        setActivityIntent(intent);
        IntentReaderActivity ira = (IntentReaderActivity)getActivity();

        assertEquals("IntentReaderActivyt should get the text from intent",
                    "message 1", ira.getText());
    }
        //What is the difference ira.get vs textView.getText
    public void testDisplayText(){
        Intent intent = new Intent();
        intent.putExtra(IntentReaderActivity.TEXT_TO_TRANSFORM_KEY, "message 2");

        setActivityIntent(intent);
        IntentReaderActivity ira = (IntentReaderActivity)getActivity();
        TextView textView=(TextView)ira.findViewById(R.id.intentText);
        assertEquals("The text should be displayed!","message 2",textView.getText().toString());
    }
    public void testDOubleText(){
        Intent intent = new Intent();
        intent.putExtra(IntentReaderActivity.TEXT_TO_TRANSFORM_KEY, "message 3");
        intent.putExtra(IntentReaderActivity.MODE_OF_TRANSFORM_KEY, IntentReaderActivity.DOUBLE);

        setActivityIntent(intent);
        IntentReaderActivity ira = (IntentReaderActivity)getActivity();
        assertEquals("The text should be reapeted twice!", "message 3message 3", ira.getText());
    }
    //
    //

    //TODO: Add your code here ...
//-------------------------------------------------------------------------------
    public void testReverseText(){
        Intent intent = new Intent();
        intent.putExtra(IntentReaderActivity.TEXT_TO_TRANSFORM_KEY, "message 4");
        intent.putExtra(IntentReaderActivity.MODE_OF_TRANSFORM_KEY, IntentReaderActivity.REVERSE);

        setActivityIntent(intent);
        IntentReaderActivity ira = (IntentReaderActivity)getActivity();
        assertEquals("This text should be reversed","4 egassem",ira.getText());
    }

    public void testDefualtText(){
        Intent intent = new Intent();
        intent.putExtra(IntentReaderActivity.MODE_OF_TRANSFORM_KEY, IntentReaderActivity.REVERSE);

        setActivityIntent(intent);
        IntentReaderActivity ira = (IntentReaderActivity)getActivity();
        assertEquals("This should be the default texts","default text",ira.getText());
    }

    public void testVisibleView(){
        Intent intent = new Intent();
        intent.putExtra(IntentReaderActivity.MODE_OF_TRANSFORM_KEY, IntentReaderActivity.REVERSE);

        setActivityIntent(intent);
        IntentReaderActivity ira = (IntentReaderActivity)getActivity();
        //First parameter is the origin, this is the root view. This is the Relative Layout in
        // the xml file. The view I am testing is the Edit text view, which is the second parameter
        ViewAsserts.assertOnScreen(ira.getWindow().getDecorView(),ira.findViewById(R.id.intentText));

    }
//-------------------------------------------------------------------------------
}
