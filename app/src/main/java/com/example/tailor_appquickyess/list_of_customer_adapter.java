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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class list_of_customer_adapter extends FirestoreRecyclerAdapter<list_of_customer_class,list_of_customer_adapter.myholder> {
    public list_of_customer_adapter(@NonNull FirestoreRecyclerOptions<list_of_customer_class> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myholder holder, int position, @NonNull list_of_customer_class model) {
        String both=model.getFirst_Name()+" "+model.getLast_Name();
        holder.fn.setText(both.substring(0, 1).toUpperCase() + both.substring(1));
        holder.pn.setText(model.getPhone());
        holder.em.setText(model.getEmail());
        holder.un.setText(model.getUser_Name());
        holder.pw.setText(model.getPassword());
        holder.ge.setText(model.getGender());
        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                builder.setMessage("Delete Records Of :\n "+model.getFirst_Name()+" "+model.getLast_Name())
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseFirestore fbfs=FirebaseFirestore.getInstance();

                                fbfs.collection("Customer").document(model.getEmail()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(v.getContext()," "+model.getFirst_Name()+" "+model.getLast_Name()+" Customer Deleted",Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(v.getContext(),"No Such User",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });

    }


    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_of_customer_entitise,parent,false);
        return new myholder(view);
    }

    class myholder extends RecyclerView.ViewHolder{
        TextView fn,pn,em,un,pw,ge;
        CardView cd;
        int counter;
        int n=0;
        public myholder(@NonNull View itemview){
            super(itemview);
            fn=itemview.findViewById(R.id.entered_full_name);
            pn=itemview.findViewById(R.id.entered_phoneno);
            em=itemview.findViewById(R.id.entered_emailid);
            un=itemview.findViewById(R.id.entered_username);
            pw=itemview.findViewById(R.id.entered_password);
            ge=itemview.findViewById(R.id.entered_gender);
            cd=itemview.findViewById(R.id.singlelist_of_customer);

        }
    }
}
