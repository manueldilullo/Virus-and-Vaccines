package it.spaceapps21.latitude.covid19.virusvaccines.login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.util.Calendar;

import it.spaceapps21.latitude.covid19.virusvaccines.R;
import it.spaceapps21.latitude.covid19.virusvaccines.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText etUsername;
    private TextView tvBirthdate;
    private Button btPickDate;
    private Button btLogin;

    private DatePickerDialog.OnDateSetListener dateSetListener;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor prefEditor;

    private String dataPickerTAG = "DATE PICK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        tvBirthdate = (TextView) findViewById(R.id.tvBirthdate);

        btPickDate = (Button) findViewById(R.id.btPickDate);
        btPickDate.setOnClickListener(this);

        dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String selectedDate = (new Date(cal.getTimeInMillis())).toString();

                tvBirthdate.setText(selectedDate);
            }
        };

        btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);

        mPreferences = getSharedPreferences("login", MODE_PRIVATE);
        prefEditor = mPreferences.edit();

        boolean logged_in = mPreferences.getBoolean("loggedin", false);
        if(logged_in){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        // If btSignupPickDate is clicked, prompt a DatePickerDialog
        if(v.getId() == R.id.btPickDate){
            Log.d(TAG, "onClick: Pick birthdate button clicked");
            DatePickerFragment datePickerFragment = new DatePickerFragment(this, dateSetListener);
            datePickerFragment.show(getSupportFragmentManager(), dataPickerTAG);
        }

        // If btLogin is clicked it'll start login procedure
        if(v.getId() == R.id.btLogin) {
            Log.d(TAG, "onClick: Sign in button clicked");

            //Extracting username and birthdate from the form
            String username = etUsername.getText().toString().trim();
            String birthdate = tvBirthdate.getText().toString().trim();

            // Saving username and birthdate in the SharedPreferences
            prefEditor.putString("username", username);
            prefEditor.putString("birthdate", birthdate);
            prefEditor.putBoolean("loggedin", true);
            prefEditor.apply();

            // Switching to MainActivity
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}