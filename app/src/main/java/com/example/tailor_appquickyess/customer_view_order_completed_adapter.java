package com.example.tailor_appquickyess;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class customer_view_order_completed_adapter extends FirestoreRecyclerAdapter<request_to_stiching_class,customer_view_order_completed_adapter.myholder> {
    public customer_view_order_completed_adapter(@NonNull FirestoreRecyclerOptions<request_to_stiching_class> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myholder holder, int position, @NonNull request_to_stiching_class model) {
        holder.dc.setText(model.getDue_Date());
        holder.tp.setText(model.getCategory());
        holder.od.setText(model.getCurrent_Date());
        holder.total.setText(model.getTotal_Price());
        Picasso.get().load(model.getImage_Url()).into(holder.img);


holder.print.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(model.getPayment().equals("True")){
            Intent i=new Intent(v.getContext(),payment_recipt.class);
            i.putExtra("cloth",model.getCategory());
            i.putExtra("email",model.getEmail());
            i.putExtra("due_date",model.getDue_Date());
            i.putExtra("order_date",model.getCurrent_Date());
            i.putExtra("total",model.getTotal_Price());
            v.getContext().startActivity(i);
        }
        else{
            Toast.makeText(v.getContext(),"Please Clear Your Payment!!",Toast.LENGTH_LONG).show();
        }
    }
});

    }


    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_customer_view_order_completed_entities,parent,false);
        return new myholder(view);
    }

    class myholder extends RecyclerView.ViewHolder{
        TextView dc,tp,od,total,print;
        ImageView img;
        public myholder(@NonNull View itemview){
            super(itemview);
            dc=itemview.findViewById(R.id.due_date_customer_view_complete);
            tp=itemview.findViewById(R.id.type_customer_view_complete);
            od=itemview.findViewById(R.id.order_date_customer_view_complete);
            img=itemview.findViewById(R.id.stiching_image_customer_view_complete);
            total=itemview.findViewById(R.id.total_price_customer_view_complete);
            print=itemview.findViewById(R.id.print);

        }
    }
}

