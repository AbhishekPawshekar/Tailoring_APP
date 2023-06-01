package com.example.tailor_appquickyess;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class feedback_adapter extends FirestoreRecyclerAdapter<feedback_class,feedback_adapter.myholder> {
    public feedback_adapter(@NonNull FirestoreRecyclerOptions<feedback_class> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myholder holder, int position, @NonNull feedback_class model) {
        holder.comments.setText(model.getComment());
        holder.email.setText(model.getEmail());

    }


    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_feedback_entities,parent,false);
        return new myholder(view);
    }

    class myholder extends RecyclerView.ViewHolder{
        TextView comments,email;

        public myholder(@NonNull View itemview){
            super(itemview);
            comments=itemview.findViewById(R.id.comments_feedback);
            email=itemview.findViewById(R.id.email_feedback);


        }
    }
}
