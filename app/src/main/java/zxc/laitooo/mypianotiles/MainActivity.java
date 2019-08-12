package zxc.laitooo.mypianotiles;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static RecyclerView recyclerView;
    ArrayList<Integer> list;
    ArrayList<Boolean> clicked;
    static TextView textScore;
    static TilesAdapter adapter;
    static int lastIndex;
    static Context context;
    static boolean isPlaying;
    static int gameSpeed,initialSpeed,numTiles;
    static int speedNumber,speedNumberCounter;
    static  int score,highScore;
    static FragmentManager fragmentManager;
    //NestedScrollView nested;
    //Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //nested = (NestedScrollView)findViewById(R.id.nested);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_tiles);
        textScore = (TextView)findViewById(R.id.score);
        textScore.setText("0");
        //start = (Button)findViewById(R.id.start_button);
        context = getApplicationContext();
        fragmentManager = getFragmentManager();
        SharedPreferences preferences = getSharedPreferences("SCORE",MODE_PRIVATE);
        if (preferences.getInt("Score",9999) != 9999){
            highScore = preferences.getInt("Score",0);
        }
        initialSpeed = 2;
        speedNumber = 3;
        numTiles = 50;
        list = new ArrayList<>();
        //list.add(1);list.add(0);list.add(2);list.add(2);list.add(1);list.add(3);list.add(2);list.add(1);list.add(0);list.add(1);list.add(3);list.add(2);list.add(3);list.add(2);
        //list.add(1);list.add(0);list.add(2);list.add(2);list.add(1);list.add(3);list.add(2);list.add(1);list.add(0);list.add(1);list.add(3);list.add(2);list.add(3);list.add(2);

        clicked = new ArrayList<>();
        Random random = new Random();
        for (int i=0;i<numTiles;i++){
            list.add(random.nextInt(4));
            clicked.add(false);
        }

        lastIndex = list.size()-1;

        adapter = new TilesAdapter(list,clicked,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.scrollToPosition(lastIndex+1);
        //nested.scrollTo(nested.getScrollX(),nested.getScrollY()*2);

        /*start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(scrolling);
            }
        });*/
    }

    public static  void startButton(){
        isPlaying = true;
        gameSpeed = initialSpeed;
        speedNumberCounter = 0;
        score = 0;
        handler.post(scrolling);
        recyclerView.addOnScrollListener(listener);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_BUTTON_RELEASE ||
                        event.getAction() == MotionEvent.ACTION_BUTTON_PRESS)
                    return false;
                return true;
            }
        });
    }

    static Handler handler = new Handler();

    Runnable adding = new Runnable() {
        @Override
        public void run() {
            list.add(0,new Random().nextInt(4));
            adapter.notifyDataSetChanged();
            Log.e("ERROR","logged" + String.valueOf(list.size()));
            handler.postDelayed(adding,1000);
        }
    };

    static Runnable scrolling = new Runnable() {
        @Override
        public void run() {
            recyclerView.scrollBy(recyclerView.getScrollX(),recyclerView.getScrollY()-gameSpeed);
            if (isPlaying) {
                handler.postDelayed(scrolling, 10);
            }
        }
    };


    static RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
        int a =0;
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager s = (LinearLayoutManager) recyclerView.getLayoutManager();
            a = s.findLastVisibleItemPosition();
            if (a < lastIndex -1){
                if (!adapter.clicks.get(a+1)) {
                    Log.e("ERROR", "failed");
                    gameLoosed();
                }
                //Toast.makeText(context,"failed",Toast.LENGTH_SHORT).show();
            }
            //Log.e("EREFEFEFEFEF","last visible " + String.valueOf(a));
        }
    };

    public static void gameLoosed() {
        isPlaying = false;
        //Toast.makeText(context,"You Loosed",Toast.LENGTH_LONG).show();
        if (MainActivity.score > MainActivity.highScore){
            ResultDialog dialog = new ResultDialog("New High Score : "  + score);
            dialog.show(fragmentManager, "My Dialog");
            SharedPreferences preferences = context.getSharedPreferences("SCORE",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("Score",MainActivity.score);
            editor.commit();
        }else {
            ResultDialog dialog = new ResultDialog("Your Score : "  + score);
            dialog.show(fragmentManager, "My Dialog");
        }

    }

}
