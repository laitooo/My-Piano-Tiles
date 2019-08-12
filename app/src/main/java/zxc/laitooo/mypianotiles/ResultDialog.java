package zxc.laitooo.mypianotiles;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Laitooo San on 05/06/2019.
 */

public class ResultDialog extends DialogFragment {

    String Text;

    public ResultDialog() {
    }

    @SuppressLint("ValidFragment")
    public ResultDialog(String text) {
        Text = text;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialoge_result, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);

        TextView score = (TextView)v.findViewById(R.id.text_result);
        Button playAgain = (Button)v.findViewById(R.id.button_result);

        setCancelable(false);

        score.setText(Text);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                getActivity().finish();
                getActivity().startActivity(new Intent(getActivity(),MainActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        return builder.create();
    }
}
