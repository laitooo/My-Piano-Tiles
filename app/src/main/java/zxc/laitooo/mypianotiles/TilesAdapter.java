package zxc.laitooo.mypianotiles;

import android.content.Context;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Laitooo San on 03/06/2019.
 */

public class TilesAdapter extends RecyclerView.Adapter<TilesHolder> {

    ArrayList<Integer> list;
    ArrayList<Boolean> clicks;
    Context c;
    int clickPosition;

    public TilesAdapter(ArrayList<Integer> list,ArrayList<Boolean> Clicks, Context c) {
        this.list = list;
        this.c = c;
        this.clicks = Clicks;
        clickPosition = 0;
    }

    @Override
    public TilesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
        return new TilesHolder(v);
    }

    @Override
    public void onBindViewHolder(TilesHolder holder, final int position) {
        if (list.size() > 0  && position == list.size()){
            holder.start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.startButton();
                }
            });
        }else {

            switch (list.get(position)) {
                case 0:
                    holder.t1.setBackgroundColor(ContextCompat.getColor(c, R.color.blue));
                    holder.t2.setBackgroundColor(ContextCompat.getColor(c, R.color.black));
                    holder.t3.setBackgroundColor(ContextCompat.getColor(c, R.color.black));
                    holder.t4.setBackgroundColor(ContextCompat.getColor(c, R.color.black));
                    holder.t1.setOnClickListener(new RightClickListener(position));
                    holder.t2.setOnClickListener(new WrongClickListener(position));
                    holder.t3.setOnClickListener(new WrongClickListener(position));
                    holder.t4.setOnClickListener(new WrongClickListener(position));
                    break;
                case 1:
                    holder.t1.setBackgroundColor(ContextCompat.getColor(c, R.color.black));
                    holder.t2.setBackgroundColor(ContextCompat.getColor(c, R.color.blue));
                    holder.t3.setBackgroundColor(ContextCompat.getColor(c, R.color.black));
                    holder.t4.setBackgroundColor(ContextCompat.getColor(c, R.color.black));
                    holder.t1.setOnClickListener(new WrongClickListener(position));
                    holder.t2.setOnClickListener(new RightClickListener(position));
                    holder.t3.setOnClickListener(new WrongClickListener(position));
                    holder.t4.setOnClickListener(new WrongClickListener(position));
                    break;
                case 2:
                    holder.t1.setBackgroundColor(ContextCompat.getColor(c, R.color.black));
                    holder.t2.setBackgroundColor(ContextCompat.getColor(c, R.color.black));
                    holder.t3.setBackgroundColor(ContextCompat.getColor(c, R.color.blue));
                    holder.t4.setBackgroundColor(ContextCompat.getColor(c, R.color.black));
                    holder.t1.setOnClickListener(new WrongClickListener(position));
                    holder.t2.setOnClickListener(new WrongClickListener(position));
                    holder.t3.setOnClickListener(new RightClickListener(position));
                    holder.t4.setOnClickListener(new WrongClickListener(position));
                    break;
                case 3:
                    holder.t1.setBackgroundColor(ContextCompat.getColor(c, R.color.black));
                    holder.t2.setBackgroundColor(ContextCompat.getColor(c, R.color.black));
                    holder.t3.setBackgroundColor(ContextCompat.getColor(c, R.color.black));
                    holder.t4.setBackgroundColor(ContextCompat.getColor(c, R.color.blue));
                    holder.t1.setOnClickListener(new WrongClickListener(position));
                    holder.t2.setOnClickListener(new WrongClickListener(position));
                    holder.t3.setOnClickListener(new WrongClickListener(position));
                    holder.t4.setOnClickListener(new RightClickListener(position));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.size() > 0 && position == list.size())
            return R.layout.bottom_layout;
        else
            return R.layout.tiles;
    }

    public class RightClickListener implements View.OnClickListener{

        int position;

        public RightClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if(MainActivity.isPlaying) {
                if (position == list.size() - 1) {
                    //Toast.makeText(c,"right " + position,Toast.LENGTH_SHORT).show();
                    v.setBackgroundColor(ContextCompat.getColor(c, R.color.green));
                    clicks.set(position, true);
                } else {
                    if (clicks.get(position + 1)) {
                        v.setBackgroundColor(ContextCompat.getColor(c, R.color.green));
                        clicks.set(position, true);
                    }
                }
                MainActivity.speedNumberCounter += 1;
                if (MainActivity.speedNumberCounter % MainActivity.speedNumber == 0)
                    MainActivity.gameSpeed += 1;

                MainActivity.score += 1;
                MainActivity.textScore.setText(String.valueOf(MainActivity.score));
            }
        }
    }

    public class WrongClickListener implements View.OnClickListener{

        int position;

        public WrongClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (MainActivity.isPlaying) {
                v.setBackgroundColor(ContextCompat.getColor(c, R.color.red));
                position += 0;
                MainActivity.gameLoosed();
            }
        }
    }
}
