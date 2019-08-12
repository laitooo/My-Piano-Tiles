package zxc.laitooo.mypianotiles;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Laitooo San on 03/06/2019.
 */

public class TilesHolder extends RecyclerView.ViewHolder {

    TextView t1,t2,t3,t4;
    Button start;

    public TilesHolder(View itemView) {
        super(itemView);

        t1 = (TextView)itemView.findViewById(R.id.t1);
        t2 = (TextView)itemView.findViewById(R.id.t2);
        t3 = (TextView)itemView.findViewById(R.id.t3);
        t4 = (TextView)itemView.findViewById(R.id.t4);
        start = (Button)itemView.findViewById(R.id.start_button);
    }
}
