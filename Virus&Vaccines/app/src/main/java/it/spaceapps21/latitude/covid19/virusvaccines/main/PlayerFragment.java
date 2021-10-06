package it.spaceapps21.latitude.covid19.virusvaccines.main;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import it.spaceapps21.latitude.covid19.virusvaccines.R;

public class PlayerFragment extends Fragment implements View.OnClickListener{

    private ImageButton ibPlayer;

    private TextView tvSurgicalDuration;
    private TextView tvFfp2Duration;

    private CheckBox cbSurgical;
    private CheckBox cbFfp2;
    private CheckBox cbSanitizer;

    private RadioButton rbFirstDose;
    private RadioButton rbSecondDose;
    private RadioGroup rgVaccine;

    public PlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player, container, false);

        ibPlayer = (ImageButton) view.findViewById(R.id.ibPlayer);

        tvSurgicalDuration = (TextView) view.findViewById(R.id.tvSurgicalDuration);
        tvFfp2Duration = (TextView) view.findViewById(R.id.tvFfp2Duration);
        cbSurgical = (CheckBox) view.findViewById(R.id.cbSurgical);

        cbFfp2 = (CheckBox) view.findViewById(R.id.cbFfp2);
        cbSanitizer = (CheckBox) view.findViewById(R.id.cbSanitizer);

        rgVaccine = (RadioGroup) view.findViewById(R.id.rgVaccine);
        rbFirstDose = (RadioButton) view.findViewById(R.id.rbFIrstDose);
        rbSecondDose = (RadioButton) view.findViewById(R.id.rbSecondDose);

        cbSurgical.setOnClickListener(this);
        cbFfp2.setOnClickListener(this);
        cbSanitizer.setOnClickListener(this);
        rgVaccine.setOnClickListener(this);
        rbFirstDose.setOnClickListener(this);
        rbSecondDose.setOnClickListener(this);

        ibPlayer.setImageResource(R.drawable._5);

        return view;
    }

    private String parseMilliseconds(long milliseconds){
        int seconds = (int) (milliseconds / 1000) % 60 ;
        int minutes = (int) ((milliseconds / (1000*60)) % 60);
        int hours   = (int) ((milliseconds / (1000*60*60)) % 24);

        return hours + ":" + minutes + ":" + seconds;
    }

    @Override
    public void onClick(View v) {

        boolean surgicalChecked = cbSurgical.isChecked();
        boolean ffp2Checked = cbFfp2.isChecked();
        boolean sanitizerChecked = cbSanitizer.isChecked();
        boolean firstDoseChecked = rbFirstDose.isChecked();
        boolean secondDoseChecked = rbSecondDose.isChecked();


        /*
         *  Setting the duration text view when Surgical or ffp2 button are clicked
         * */

        // If CheckBox Surgical was clicked:
        //      if it's checked remove the check from surgical and start countdown
        //      otherwise fill textView with "Duration" string
        if(v.getId() == R.id.cbSurgical) {
            if (cbSurgical.isChecked()) {
                cbFfp2.setChecked(false);
                tvFfp2Duration.setText(getResources().getString(R.string.duration));

                new CountDownTimer(4 * 60 * 60 * 1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        tvSurgicalDuration.setText(parseMilliseconds(millisUntilFinished));
                    }

                    public void onFinish() {
                        tvSurgicalDuration.setText(getResources().getString(R.string.duration));
                    }

                }.start();
            } else {
                tvSurgicalDuration.setText(getResources().getString(R.string.duration));
            }
        }

        // If CheckBox FFP2 was clicked:
        //      if it's checked remove the check from surgical and start countdown
        //      otherwise fill textView with "Duration" string
        if(v.getId() == R.id.cbFfp2){
            if(cbFfp2.isChecked()){
                cbSurgical.setChecked(false);
                tvSurgicalDuration.setText(getResources().getString(R.string.duration));

                new CountDownTimer(8*60*60*1000, 60000) {

                    public void onTick(long millisUntilFinished) {
                        tvFfp2Duration.setText(parseMilliseconds(millisUntilFinished));
                    }

                    public void onFinish() {
                        tvFfp2Duration.setText(getResources().getString(R.string.duration));
                    }

                }.start();
            }else{
                tvFfp2Duration.setText(getResources().getString(R.string.duration));
            }
        }


        /*
        *  Choose the player to show checking the state of the buttons
        * */
        if (surgicalChecked && !(sanitizerChecked || firstDoseChecked || secondDoseChecked)){
            ibPlayer.setImageResource(R.drawable._1);
            return;
        }

        if (surgicalChecked && sanitizerChecked && !(firstDoseChecked || secondDoseChecked)){
            ibPlayer.setImageResource(R.drawable._2);
            return;
        }

        if (surgicalChecked && (firstDoseChecked || secondDoseChecked) && !(sanitizerChecked)){
            ibPlayer.setImageResource(R.drawable._3);
            return;
        }

        if (surgicalChecked && (firstDoseChecked || secondDoseChecked) && sanitizerChecked){
            ibPlayer.setImageResource(R.drawable._4);
            return;
        }

        if(!(surgicalChecked || ffp2Checked || sanitizerChecked || firstDoseChecked || secondDoseChecked)) {
            ibPlayer.setImageResource(R.drawable._5);
            return;
        }

        if ((firstDoseChecked || secondDoseChecked) && ! (surgicalChecked || sanitizerChecked || ffp2Checked)){
            ibPlayer.setImageResource(R.drawable._6);
            return;
        }

        if (sanitizerChecked && !(ffp2Checked || firstDoseChecked || secondDoseChecked)){
            ibPlayer.setImageResource(R.drawable._7);
            return;
        }

        if (ffp2Checked && ! (sanitizerChecked || firstDoseChecked || secondDoseChecked)){
            ibPlayer.setImageResource(R.drawable._8);
        }

        if (ffp2Checked && (firstDoseChecked || secondDoseChecked) && !(sanitizerChecked)){
            ibPlayer.setImageResource(R.drawable._9);
        }

        if (ffp2Checked && sanitizerChecked && !(firstDoseChecked || secondDoseChecked)){
            ibPlayer.setImageResource(R.drawable._10);
        }

        if (ffp2Checked && (firstDoseChecked || secondDoseChecked) && sanitizerChecked){
            ibPlayer.setImageResource(R.drawable._11);
        }
    }
}