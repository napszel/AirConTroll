package com.napszel.AirControll;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Pipes extends Activity {
    private TextView counterText;
    private ImageView playField;
    private ImageView troll;

    private int counter = 0;

    private View.OnClickListener trollTapListener;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        InitializeApp();
    }

    private void InitializeApp() {
        counterText = (TextView) findViewById(R.id.counter);
        playField = (ImageView) findViewById(R.id.playField);
        troll = (ImageView) findViewById(R.id.troll);

        // Define and attach listeners
        trollTapListener = new View.OnClickListener()  {
            public void onClick(View v) {
                TapDroid();
            }
        };
        troll.setOnClickListener(trollTapListener);
    }

    private void TapDroid() {
        counter++;
        String temp = String.format("%d", counter);
        counterText.setText(String.format("%s", temp));
    }
}
