package it.spaceapps21.latitude.covid19.virusvaccines.main;

import android.os.Bundle;
<<<<<<< HEAD
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
=======
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
>>>>>>> a2f4ba672ecd4a587cecb1fd3cb6816e9bd8da41

import androidx.fragment.app.Fragment;

import it.spaceapps21.latitude.covid19.virusvaccines.R;

<<<<<<< HEAD
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
=======
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
>>>>>>> a2f4ba672ecd4a587cecb1fd3cb6816e9bd8da41

    public PlayerFragment() {
        // Required empty public constructor
    }

<<<<<<< HEAD
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
=======
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerFragment newInstance(String param1, String param2) {
        PlayerFragment fragment = new PlayerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
>>>>>>> a2f4ba672ecd4a587cecb1fd3cb6816e9bd8da41
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
<<<<<<< HEAD
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
=======
        return inflater.inflate(R.layout.fragment_player, container, false);
>>>>>>> a2f4ba672ecd4a587cecb1fd3cb6816e9bd8da41
    }
}