package com.example.tailor_appquickyess;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link admin_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class admin_home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public admin_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment admin_home.
     */
    // TODO: Rename and change types and number of parameters
    public static admin_home newInstance(String param1, String param2) {
        admin_home fragment = new admin_home();
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
TextView upper,lower;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_home, container, false);
        upper=view.findViewById(R.id.request_to_upper_body);
        lower=view.findViewById(R.id.request_to_lower_body);
        upper.setBackgroundColor(Color.argb(255,251,213,184));

        FragmentTransaction t=getFragmentManager().beginTransaction();
        t.replace(R.id.choosebodyframelayout,new upper_body_part()).commit();

        upper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lower.setBackgroundColor(Color.argb(255,255,255,255));
                upper.setBackgroundColor(Color.argb(255,251,213,184));
                FragmentTransaction t=getFragmentManager().beginTransaction();
                t.replace(R.id.choosebodyframelayout,new upper_body_part()).commit();

            }
        });
        lower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upper.setBackgroundColor(Color.argb(255,255,255,255));
                lower.setBackgroundColor(Color.argb(255,251,213,184));
                FragmentTransaction t=getFragmentManager().beginTransaction();
                t.replace(R.id.choosebodyframelayout,new lower_body_part()).commit();

            }
        });


        return view;
    }
}