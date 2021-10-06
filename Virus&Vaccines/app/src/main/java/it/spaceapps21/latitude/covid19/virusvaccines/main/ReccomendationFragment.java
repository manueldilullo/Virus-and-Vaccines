package it.spaceapps21.latitude.covid19.virusvaccines.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import it.spaceapps21.latitude.covid19.virusvaccines.R;
import it.spaceapps21.latitude.covid19.virusvaccines.adapter.ReccomendationListAdapter;
import it.spaceapps21.latitude.covid19.virusvaccines.classes.ReccomendationElement;

/**
 * TODO: Write a function that get the Activity dinamically
 *       based on each country policies
 */
public class ReccomendationFragment extends Fragment {

    private static final String TAG = ReccomendationFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private ReccomendationListAdapter reccomendationListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reccomendation, container, false);

        recyclerView = view.findViewById(R.id.rvReccomendations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        reccomendationListAdapter = new ReccomendationListAdapter((MainActivity) getActivity());
        recyclerView.setAdapter(reccomendationListAdapter);

        Log.d(TAG, "onCreateView: fragment created");

        addElements();

        return view;
    }

    private void addElements(){
        reccomendationListAdapter.add(new ReccomendationElement("Ride a bike following the wind", R.drawable.bike));
        reccomendationListAdapter.add(new ReccomendationElement("Go out for a walk in the nature", R.drawable.walk));
        reccomendationListAdapter.add(new ReccomendationElement("Take a relaxing yoga class", R.drawable.yoga));
        reccomendationListAdapter.add(new ReccomendationElement("Meditate and find yourself", R.drawable.meditate));
        reccomendationListAdapter.add(new ReccomendationElement("Draw something special", R.drawable.draw));
        reccomendationListAdapter.add(new ReccomendationElement("Read a beautiful book", R.drawable.read));
    }
}