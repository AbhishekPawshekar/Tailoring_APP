package com.example.tailor_appquickyess;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class customer_view_order_adapter extends FirestoreRecyclerAdapter<request_to_stiching_class,customer_view_order_adapter.myholder> {
    public customer_view_order_adapter(@NonNull FirestoreRecyclerOptions<request_to_stiching_class> options) {
        super(options);
    }
    
    @Override
    protected void onBindViewHolder(@NonNull myholder holder, int position, @NonNull request_to_stiching_class model) {
        holder.dc.setText(model.getDue_Date());
        holder.tp.setText(model.getCategory());
        holder.od.setText(model.getCurrent_Date());
        holder.total.setText(model.getTotal_Price());
        holder.advance.setText(model.getAdvance_Paid());
        Picasso.get().load(model.getImage_Url()).into(holder.img);


    }


    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_customer_view_order_entities,parent,false);
        return new myholder(view);
    }

    class myholder extends RecyclerView.ViewHolder{
        TextView dc,tp,od,total,advance;
        ImageView img;
        public myholder(@NonNull View itemview){
            super(itemview);
            dc=itemview.findViewById(R.id.due_date_customer_view);
            tp=itemview.findViewById(R.id.type_customer_view);
            od=itemview.findViewById(R.id.order_date_customer_view);
            img=itemview.findViewById(R.id.stiching_image_customer_view);
            total=itemview.findViewById(R.id.total_price_customer_view);
            advance=itemview.findViewById(R.id.advance_given_customer_view);

        }
    }
}

