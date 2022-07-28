package com.example.pranav.tictactoe;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageButton cir, cro;
    ConstraintLayout choose;
    int[] arr={2,2,2,2,2,2,2,2,2};
    int active = 0;
    int[][] win = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean game = true;
    public void click(View view){
        ImageView counter = (ImageView)view;
        Button play= (Button)findViewById(R.id.play);
        TextView winner = (TextView)findViewById(R.id.winner);
        LinearLayout playLayout = (LinearLayout)findViewById(R.id.playAgain);

        int temp = Integer.parseInt(counter.getTag().toString());

       if(arr[temp]==2 && game==true) {

           arr[temp]=active;
           counter.setTranslationX(-1000f);
           if (active == 0) {
               counter.setImageResource(R.drawable.cross);
               active = 1;
           } else {
               counter.setImageResource(R.drawable.circle);
               active = 0;
           }
           counter.animate().translationXBy(1000f).rotationBy(360f);
       }

           for (int[] winPosition : win) {
               if (arr[winPosition[0]] == arr[winPosition[1]] &&
                       arr[winPosition[1]] == arr[winPosition[2]] &&
                       arr[winPosition[0]] != 2) {
                   game = false;
                   String w = "Circle";
                   if (arr[winPosition[0]] == 0) {
                       w = "Cross";
                   }
                   winner.setText(w + " has won!");
                   playLayout.setVisibility(view.VISIBLE);
               }else{
                   boolean over = true;
                   for (int counterState : arr){
                       if(counterState == 2) over=false;
                   }

                   if(over){
                       winner.setText("It's a tie!");
                       playLayout.setVisibility(view.VISIBLE);

                   }
               }
           }
       }

    public void play(View view){
        GridLayout grid = (GridLayout)findViewById(R.id.grid);
        LinearLayout playLayout = (LinearLayout)findViewById(R.id.playAgain);
        playLayout.setVisibility(view.INVISIBLE);
        grid.setVisibility(View.INVISIBLE);
        choose.setVisibility(View.VISIBLE);
        for(int i=0; i<arr.length; i++ ){
            arr[i]=2;
        }
        for(int i=0; i<grid.getChildCount();i++){
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }
        game=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cir = (ImageButton) findViewById(R.id.cir);
        cro = (ImageButton) findViewById(R.id.cro);
        choose = (ConstraintLayout)findViewById(R.id.choose);
        final GridLayout grid = (GridLayout)findViewById(R.id.grid);


        cir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                active = 1;
                choose.setVisibility(View.INVISIBLE);
                grid.setVisibility(View.VISIBLE);

            }
        });

        cro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                active = 0;
                choose.setVisibility(View.INVISIBLE);
                grid.setVisibility(View.VISIBLE);

            }
        });

    }
}
