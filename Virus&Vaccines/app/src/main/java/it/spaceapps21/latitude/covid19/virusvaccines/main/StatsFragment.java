package it.spaceapps21.latitude.covid19.virusvaccines.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import it.spaceapps21.latitude.covid19.virusvaccines.R;

public class StatsFragment extends Fragment {

    private static final String TAG = StatsFragment.class.getSimpleName();

    private TextView tvCountryName;

    private TextView tvTotalCases;
    private TextView tvDailyCases;
    private TextView tvDifferenceCases;

    private TextView tvTotalTests;
    private TextView tvDailyTests;
    private TextView tvDifferenceTests;

    private TextView tvTotalDeceases;
    private TextView tvDailyDeceases;
    private TextView tvDifferenceDeceases;


    public StatsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        String countryCode = Locale.getDefault().getISO3Country();
        Log.d(TAG, "onCreate: " + countryCode);

        tvCountryName = (TextView) view.findViewById(R.id.tvCountryName);
        String countryName = (new Locale("", countryCode)).getDisplayCountry();
        tvCountryName.setText(countryName);

        tvTotalCases = (TextView) view.findViewById(R.id.tvTotalCases);
        tvDailyCases = (TextView) view.findViewById(R.id.tvDailyCases);
        tvDifferenceCases = (TextView) view.findViewById(R.id.tvDifferenceCases);

        tvTotalTests = (TextView) view.findViewById(R.id.tvTotalTests);
        tvDailyTests = (TextView) view.findViewById(R.id.tvDailyTests);
        tvDifferenceTests = (TextView) view.findViewById(R.id.tvDifferenceTests);

        tvTotalDeceases = (TextView) view.findViewById(R.id.tvTotalDeceases);
        tvDailyDeceases = (TextView) view.findViewById(R.id.tvDailyDeceases);
        tvDifferenceDeceases = (TextView) view.findViewById(R.id.tvDifferenceDeceases);

        getData(countryCode, countryName);

        return view;
    }

    private void getData(String countryCode, String countryName){
        Log.d(TAG, "getData: Method called");

        String url = getResources().getString(R.string.api_sixmonth_url);
        url += countryCode;

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        // Request a string response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                parseData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d(TAG, "onErrorResponse: " + volleyError.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("x-rapidapi-host", getResources().getString(R.string.rapidapi_vaccovid));
                headers.put("x-rapidapi-key", getResources().getString(R.string.rapidapi_key));
                return headers;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return new HashMap<String, String>();
            }
        };

        Log.d(TAG, "getData: " + jsonArrayRequest);
        // Add the request to the RequestQueue
        requestQueue.add(jsonArrayRequest);
    }

    private void parseData(JSONArray response){
        Log.d(TAG, "parseData: " + response.toString());

        try {
            JSONObject todayValues = response.getJSONObject(0);
            JSONObject yesterdayValues = response.getJSONObject(1);

            String totalCases = todayValues.getString("total_cases");
            String newCases = todayValues.getString("new_cases");
            String differenceCases = String.valueOf(todayValues.getInt("new_cases") - yesterdayValues.getInt("new_cases"));

            String totalTests = todayValues.getString("total_tests");
            String newTests = todayValues.getString("new_tests");
            String differenceTests = String.valueOf(todayValues.getInt("new_tests") - yesterdayValues.getInt("new_tests"));

            String totalDeceases = todayValues.getString("total_deaths");
            String newDeceases = todayValues.getString("new_deaths");
            String differenceDeceases = String.valueOf(todayValues.getInt("new_deaths") - yesterdayValues.getInt("new_deaths"));

            tvTotalCases.setText(totalCases);
            tvDailyCases.setText(newCases);
            tvDifferenceCases.setText(differenceCases);

            tvTotalTests.setText(totalTests);
            tvDailyTests.setText(newTests);
            tvDifferenceTests.setText(differenceTests);

            tvTotalDeceases.setText(totalDeceases);
            tvDailyDeceases.setText(newDeceases);
            tvDifferenceDeceases.setText(differenceDeceases);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}