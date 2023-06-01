package com.example.tailor_appquickyess;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class view_completed_orders_admin_adapter extends FirestoreRecyclerAdapter<request_to_stiching_class,view_completed_orders_admin_adapter.myholder> {
    public view_completed_orders_admin_adapter(@NonNull FirestoreRecyclerOptions<request_to_stiching_class> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myholder holder, int position, @NonNull request_to_stiching_class model) {
        if(model.getPayment().equals("True")){
            holder.payment.setChecked(true);
        }
        FirebaseFirestore fbfs=FirebaseFirestore.getInstance();

        holder.payment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    fbfs.collection("Order_Completed").document(model.getEmail()+model.getOrder_No()).update("Payment","True").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Log.d("Payment:","Done");
                            }
                        }
                    });
                }else {
                    holder.payment.setSelected(false);
                }
            }
        });
        holder.phone.setText(model.getPhone());
        holder.fullname.setText(model.getFirst_Name()+" "+model.getLast_Name());
        holder.email.setText(model.getEmail());
        holder.category.setText(model.getCategory());
        holder.type_of_cloth.setText(model.getType_Of_Cloth());
        holder.stiching_price.setText(model.getTotal_Price());
        holder.advance_given.setText(model.getAdvance_Paid());
        holder.order_date.setText(model.getCurrent_Date());
        holder.due_date.setText(model.getDue_Date());
        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                builder.setMessage("Delete Order Of :\n "+model.getFirst_Name()+" "+model.getLast_Name())
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
                                fbfs.collection("Order_Completed").document(model.getEmail()+model.getOrder_No()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            StorageReference storageReference= FirebaseStorage.getInstance().getReference().child(model.getEmail()+model.getOrder_No());
                                            storageReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(v.getContext()," "+model.getFirst_Name()+" "+model.getLast_Name()+" Order Deleted Successfully!!!",Toast.LENGTH_SHORT).show();
                                                }
                                            });
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_orders_admin_entities,parent,false);
        return new myholder(view);
    }

    class myholder extends RecyclerView.ViewHolder{
        TextView fullname,email,phone,category,type_of_cloth,stiching_price,advance_given,order_date,due_date;
        CheckBox payment;
        CardView cd;
        public myholder(@NonNull View itemview){
            super(itemview);
            payment=itemview.findViewById(R.id.payment_don_or_not);
            fullname=itemview.findViewById(R.id.entered_full_name_order);
            email=itemview.findViewById(R.id.entered_emailid_order);
            phone=itemview.findViewById(R.id.entered_phoneno_order);
            category=itemview.findViewById(R.id.entered_category_order);
            type_of_cloth=itemview.findViewById(R.id.entered_type_of_cloth_order);
            stiching_price=itemview.findViewById(R.id.entered_stiching_price_order);
            advance_given =itemview.findViewById(R.id.entered_advance_given_order);
            order_date=itemview.findViewById(R.id.entered_order_date_order);
            due_date=itemview.findViewById(R.id.entered_due_date_order);
            cd=itemview.findViewById(R.id.single_view_orders_admin_entitiesui);

        }
    }
}

