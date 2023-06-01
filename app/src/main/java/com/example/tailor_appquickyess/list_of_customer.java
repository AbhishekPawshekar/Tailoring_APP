package com.example.tailor_appquickyess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

public class list_of_customer extends AppCompatActivity {
    RecyclerView rv;
    list_of_customer_adapter myadpter;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_customer);
        rv=findViewById(R.id.recyclerviewlist_of_customer);
        searchView=findViewById(R.id.searchlist_of_customer);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                startsearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                startsearch(newText);
                return false;
            }
        });

        rv.setLayoutManager(new LinearLayoutManager(this));
        FirestoreRecyclerOptions<list_of_customer_class> options= new FirestoreRecyclerOptions.Builder<list_of_customer_class>()
                .setQuery(FirebaseFirestore.getInstance().collection("Customer"),list_of_customer_class.class)
                .build();
        myadpter=new list_of_customer_adapter(options);
        rv.setAdapter(myadpter);
    }
    private void startsearch(String newText) {
        FirestoreRecyclerOptions<list_of_customer_class> options= new FirestoreRecyclerOptions.Builder<list_of_customer_class>()
                .setQuery(FirebaseFirestore.getInstance().collection("Customer").orderBy("Full_Name").startAt(newText).endAt(newText+"\uf8ff"),list_of_customer_class.class)
                .build();
        myadpter=new list_of_customer_adapter(options);
        myadpter.startListening();
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
