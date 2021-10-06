package it.spaceapps21.latitude.covid19.virusvaccines.main;

import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
=======
>>>>>>> a2f4ba672ecd4a587cecb1fd3cb6816e9bd8da41
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
<<<<<<< HEAD
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
=======

import it.spaceapps21.latitude.covid19.virusvaccines.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReccomendationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReccomendationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReccomendationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReccomendationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReccomendationFragment newInstance(String param1, String param2) {
        ReccomendationFragment fragment = new ReccomendationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
>>>>>>> a2f4ba672ecd4a587cecb1fd3cb6816e9bd8da41

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
=======
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
=======
        return inflater.inflate(R.layout.fragment_reccomendation, container, false);
>>>>>>> a2f4ba672ecd4a587cecb1fd3cb6816e9bd8da41
    }
}