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

public class request_to_stiching_lower_body_adapter extends FirestoreRecyclerAdapter<request_to_stiching_class,request_to_stiching_lower_body_adapter.myholder> {
    public request_to_stiching_lower_body_adapter(@NonNull FirestoreRecyclerOptions<request_to_stiching_class> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull request_to_stiching_lower_body_adapter.myholder holder, int position, @NonNull request_to_stiching_class model) {

        holder.cn.setText(model.getFirst_Name()+" "+model.getLast_Name());

        holder.tc.setText(model.getCategory());
        holder.dd.setText(model.getDue_Date());
        Picasso.get().load(model.getImage_Url()).into(holder.img);
        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),extra_details_lower_body.class);
                i.putExtra("First_Name",model.getFirst_Name());
                i.putExtra("Last_Name",model.getLast_Name());
                i.putExtra("Phone",model.getPhone());
                i.putExtra("Email",model.getEmail());
                i.putExtra("Extra_Material",model.getExtra_Material());
                i.putExtra("Due_Date",model.getDue_Date());
                i.putExtra("Current_Date",model.getCurrent_Date());
                i.putExtra("Advance_paid",model.getAdvance_Paid());
                i.putExtra("Total_Price",model.getTotal_Price());
                i.putExtra("Type_Of_Cloth",model.getType_Of_Cloth());
                i.putExtra("Extra_Details",model.getExtra_Details());
                i.putExtra("Front_Rise",model.getFront_Rise());
                i.putExtra("Hip_Length",model.getHip_Length());
                i.putExtra("Inseam",model.getInseam());
                i.putExtra("Knee_Length",model.getKnee_Length());
                i.putExtra("Waist",model.getWaist());
                i.putExtra("Back_Rise",model.getBack_Rise());
                i.putExtra("Total_length",model.getTotal_Length());
                i.putExtra("Leg_Open",model.getLeg_Open());
                i.putExtra("Counter",model.getOrder_No());
                i.putExtra("Category",model.getCategory());
                i.putExtra("Image_Url",model.getImage_Url());
                v.getContext().startActivity(i);


            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),extra_details_lower_body.class);
                i.putExtra("First_Name",model.getFirst_Name());
                i.putExtra("Last_Name",model.getLast_Name());
                i.putExtra("Phone",model.getPhone());
                i.putExtra("Email",model.getEmail());
                i.putExtra("Due_Date",model.getDue_Date());
                i.putExtra("Extra_Material",model.getExtra_Material());
                i.putExtra("Current_Date",model.getCurrent_Date());
                i.putExtra("Advance_paid",model.getAdvance_Paid());
                i.putExtra("Total_Price",model.getTotal_Price());
                i.putExtra("Type_Of_Cloth",model.getType_Of_Cloth());
                i.putExtra("Extra_Details",model.getExtra_Details());
                i.putExtra("Front_Rise",model.getFront_Rise());
                i.putExtra("Hip_Length",model.getHip_Length());
                i.putExtra("Inseam",model.getInseam());
                i.putExtra("Knee_Length",model.getKnee_Length());
                i.putExtra("Waist",model.getWaist());
                i.putExtra("Back_Rise",model.getBack_Rise());
                i.putExtra("Total_length",model.getTotal_Length());
                i.putExtra("Leg_Open",model.getLeg_Open());
                i.putExtra("Counter",model.getOrder_No());
                i.putExtra("Category",model.getCategory());
                i.putExtra("Image_Url",model.getImage_Url());
                v.getContext().startActivity(i);
            }
        });

    }


    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_admin_view_stiching_product,parent,false);
        return new myholder(view);
    }

    class myholder extends RecyclerView.ViewHolder{
        TextView cn,tc,dd;
        ImageView img;
        CardView cd;
        int counter;
        int n=0;
        public myholder(@NonNull View itemview){
            super(itemview);
            cn=itemview.findViewById(R.id.customer_name);
            tc=itemview.findViewById(R.id.type_customer);
            dd=itemview.findViewById(R.id.due_date_customer);
            img=itemview.findViewById(R.id.stiching_image_admin_view_product);
            cd=itemview.findViewById(R.id.singleadminviewstichingproductui);

        }
    }
}

