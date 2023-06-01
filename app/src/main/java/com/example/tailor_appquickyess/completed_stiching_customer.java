package com.example.tailor_appquickyess;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link completed_stiching_customer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class completed_stiching_customer extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public completed_stiching_customer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment completed_stiching_customer.
     */
    // TODO: Rename and change types and number of parameters
    public static completed_stiching_customer newInstance(String param1, String param2) {
        completed_stiching_customer fragment = new completed_stiching_customer();
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
RecyclerView rv;
    customer_view_order_completed_adapter myadapter;
    FirebaseAuth fa=FirebaseAuth.getInstance();
    FirebaseUser fu=fa.getCurrentUser();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_completed_stiching_customer, container, false);
        rv=view.findViewById(R.id.completed_stiching_customer_recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        FirestoreRecyclerOptions<request_to_stiching_class> options= new FirestoreRecyclerOptions.Builder<request_to_stiching_class>()
                .setQuery(FirebaseFirestore.getInstance().collection("Order_Completed").whereEqualTo("Email",fu.getEmail()),request_to_stiching_class.class)
                .build();
        myadapter=new customer_view_order_completed_adapter(options);
        rv.setAdapter(myadapter);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        myadapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        myadapter.stopListening();
    }

}