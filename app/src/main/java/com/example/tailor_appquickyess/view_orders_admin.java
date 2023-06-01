package com.example.tailor_appquickyess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

public class view_orders_admin extends AppCompatActivity {
    RecyclerView rv;
    view_completed_orders_admin_adapter myadpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders_admin);
        rv=findViewById(R.id.recyclerview_view_Completed_orders_admin);
        rv.setLayoutManager(new LinearLayoutManager(this));
        FirestoreRecyclerOptions<request_to_stiching_class> options= new FirestoreRecyclerOptions.Builder<request_to_stiching_class>()
                .setQuery(FirebaseFirestore.getInstance().collection("Order_Completed"),request_to_stiching_class.class)
                .build();
        myadpter=new view_completed_orders_admin_adapter(options);
        rv.setAdapter(myadpter);
    }
    @Override
    public void onStart() {
        super.onStart();
        myadpter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        myadpter.stopListening();
    }

}