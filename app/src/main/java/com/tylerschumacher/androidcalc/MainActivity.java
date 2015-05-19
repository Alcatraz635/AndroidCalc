package com.tylerschumacher.androidcalc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private String total = "0";
    private String nextInt = "";
    private String operator = "";
    private boolean equalHasBeenClicked = false;

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        TextView myTextView = (TextView) findViewById(R.id.textView);
        savedInstanceState.putCharSequence("myText", myTextView.getText());
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textView);
        if(savedInstanceState != null){
            textView.setText(savedInstanceState.getCharSequence("myText"));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onNumberClick(View v){
        if (equalHasBeenClicked){
            nextInt = "";
            operator = "";
            equalHasBeenClicked = false;
            total = "0";
        }
        Button button = (Button) v;
        String bText = (String) button.getText();
        if (operator.compareTo("") > 0 || operator.compareTo("") < 0) {
            if (nextInt.compareTo("") == 0)
            {
                nextInt = bText;
            }
            else{
                nextInt = nextInt + bText;
            }
            TextView myTextView = (TextView) findViewById(R.id.textView);
            myTextView.setText(nextInt);
        }
        else {
            nextInt = "";
            if (total.compareTo("0") == 0) {
                total = bText;
            } else {
                total = total + bText;
            }
            TextView myTextView = (TextView) findViewById(R.id.textView);
            myTextView.setText(total);
        }
    }

    public void onOperatorClick(View v){
        if (equalHasBeenClicked) {
            equalHasBeenClicked = false;
            nextInt = "";
        }
        Button button = (Button) v;
        operator = (String) button.getText();
    }

    public void onEqualClick(View v) throws IllegalStateException{
        float value = 0;
        switch(operator){
            case "+": value = Float.parseFloat(total) + Float.parseFloat(nextInt);
                break;
            case "-": value = Float.parseFloat(total) - Float.parseFloat(nextInt);
                break;
            case "/": value = Float.parseFloat(total) / Float.parseFloat(nextInt);
                break;
            case "*": value = Float.parseFloat(total) * Float.parseFloat(nextInt);
                break;
            default:
                break;
        }

        total = Float.toString(value);
        equalHasBeenClicked = true;

        TextView myTextView = (TextView) findViewById(R.id.textView);
        myTextView.setText(total);
    }

    public void onClearClick(View v) {
        total = "";
        nextInt = "";
        operator = "";
        TextView myTextView = (TextView) findViewById(R.id.textView);
        myTextView.setText(Float.toString(0));
    }
}
