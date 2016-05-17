package com.napszel.AirControll;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.logging.Logger;

public class Pipes extends Activity {
    private static final Logger LOG = Logger.getLogger(Pipes.class.getName());
    private TextView counterText;
    private GridView grid;
    private ImageView playField;
    private ImageView troll;

    private int counter = 0;

    private View.OnClickListener trollTapListener;

    private int[][] pipeImages = {
            {R.drawable.nullimage, R.drawable.pintop, R.drawable.nullimage, R.drawable.nullimage, R.drawable.pintop, R.drawable.nullimage, R.drawable.nullimage},
            {R.drawable.p0001, R.drawable.p1011, R.drawable.p0011, R.drawable.p0100, R.drawable.p0110, R.drawable.p0111, R.drawable.p0111},
            {R.drawable.p0001, R.drawable.p1011, R.drawable.p0011, R.drawable.p0100, R.drawable.p0110, R.drawable.p0111, R.drawable.p0111},
            {R.drawable.p1010, R.drawable.p1011, R.drawable.p1100, R.drawable.p1110, R.drawable.pcorner, R.drawable.pcross, R.drawable.p0111},
            {R.drawable.p0001, R.drawable.p1011, R.drawable.p0011, R.drawable.p0100, R.drawable.p0110, R.drawable.p0111, R.drawable.p0111},
            {R.drawable.p1010, R.drawable.p1011, R.drawable.p1010, R.drawable.p1010, R.drawable.p1010, R.drawable.p1010, R.drawable.p0111},
            {R.drawable.p0001, R.drawable.p1011, R.drawable.p0011, R.drawable.p0100, R.drawable.p0110, R.drawable.p0111, R.drawable.p0111},
            {R.drawable.nullimage, R.drawable.poutbottom, R.drawable.nullimage, R.drawable.nullimage, R.drawable.poutbottom, R.drawable.nullimage, R.drawable.nullimage}
    };

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
        final Model model = new Model(pipeImages);

        counterText = (TextView) findViewById(R.id.counter);
        playField = (ImageView) findViewById(R.id.playField);
        troll = (ImageView) findViewById(R.id.troll);

        grid = (GridView) findViewById(R.id.gridview);
        grid.setAdapter(new ImageAdapter(this, model));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                model.rotate(position);
            }
        });

        grid.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    return true;
                }
                return false;
            }
        });

    }

}
