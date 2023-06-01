package com.example.tailor_appquickyess;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_click#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_click extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home_click() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home_click.
     */
    // TODO: Rename and change types and number of parameters
    public static home_click newInstance(String param1, String param2) {
        home_click fragment = new home_click();
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
    }
    TextView under,completed;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_click, container, false);
        under = view.findViewById(R.id.request_to_under_stiching);
        completed = view.findViewById(R.id.request_to_completed_Stiching);
        under.setBackgroundColor(Color.argb(255, 251, 213, 184));

        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.replace(R.id.customer_stichingframelayout, new under_stiching_customer()).commit();

        under.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completed.setBackgroundColor(Color.argb(255, 255, 255, 255));
                under.setBackgroundColor(Color.argb(255, 251, 213, 184));
                FragmentTransaction t = getFragmentManager().beginTransaction();
                t.replace(R.id.customer_stichingframelayout, new under_stiching_customer()).commit();

            }
        });
        completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                under.setBackgroundColor(Color.argb(255, 255, 255, 255));
                completed.setBackgroundColor(Color.argb(255, 251, 213, 184));
                FragmentTransaction t = getFragmentManager().beginTransaction();
                t.replace(R.id.customer_stichingframelayout, new completed_stiching_customer()).commit();

            }
        });

return view;
    }
}