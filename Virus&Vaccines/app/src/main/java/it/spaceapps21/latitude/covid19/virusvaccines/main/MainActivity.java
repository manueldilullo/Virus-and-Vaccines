package it.spaceapps21.latitude.covid19.virusvaccines.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import it.spaceapps21.latitude.covid19.virusvaccines.FragmentChangeListener;
import it.spaceapps21.latitude.covid19.virusvaccines.R;

public class MainActivity extends AppCompatActivity implements FragmentChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private SharedPreferences mPreferences;
    private String username;
    private String birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getSharedPreferences("login", MODE_PRIVATE);
        username = mPreferences.getString("username", "");
        birthdate = mPreferences.getString("birthdate", "");

        Toolbar main_toolbar = findViewById(R.id.tbMainToolbar);
        main_toolbar.setTitleTextColor(getColor(R.color.white));
        setSupportActionBar(main_toolbar);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){

                    case R.id.player:
                        switchFragment(new PlayerFragment(), getString(R.string.fragment_player_title));
                        return true;
                    case R.id.recommendations:
                        switchFragment(new ReccomendationFragment(), getString(R.string.fragment_reccomendation_title));
                        return true;
                    case R.id.stats:
                        switchFragment(new StatsFragment(), getString(R.string.fragment_stats_title));
                        return true;
                }
                return false;
            }
        });
        switchFragment(new PlayerFragment(), getResources().getString(R.string.fragment_player_title));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_notifications) {
            Intent i = new Intent(this, ReccomendationsActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_search) {
            Intent i = new Intent(this, SearchVideogameActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
     */

    /**
     *
     * @param fragment Fragment.
     * @param title
     *
     * This implementation switches the current fragment without destroying it
     * and without add a new fragment more than one time
     */
    @Override
    public void switchFragment(Fragment fragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.executePendingTransactions();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment activeFragment = getVisibleFragment();
        if(activeFragment != null){
            fragmentTransaction.hide(activeFragment);
        }

        if(fragment.isAdded())
            fragmentTransaction.show(fragment);
        else
            fragmentTransaction.add(R.id.flMainFragment, fragment);

        fragmentTransaction.commit();

        if(title != null)
            getSupportActionBar().setTitle(title);
    }

    public Fragment getVisibleFragment(){
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }
}