package com.example.tailor_appquickyess;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.squareup.picasso.Picasso;

public class request_to_stiching_upper_body_adapter extends FirestoreRecyclerAdapter<request_to_stiching_class,request_to_stiching_upper_body_adapter.myholder> {
    public request_to_stiching_upper_body_adapter(@NonNull FirestoreRecyclerOptions<request_to_stiching_class> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myholder holder, int position, @NonNull request_to_stiching_class model) {
        holder.cn.setText(model.getFirst_Name()+" "+model.getLast_Name());

        holder.tc.setText(model.getCategory());
        holder.dd.setText(model.getDue_Date());
        Picasso.get().load(model.getImage_Url()).into(holder.img);
                holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),extra_details_upper_body.class);
                i.putExtra("First_Name",model.getFirst_Name());
                i.putExtra("Last_Name",model.getLast_Name());
                i.putExtra("Phone",model.getPhone());
                i.putExtra("Extra_Material",model.getExtra_Material());
                i.putExtra("Email",model.getEmail());
                i.putExtra("Due_Date",model.getDue_Date());
                i.putExtra("Current_Date",model.getCurrent_Date());
                i.putExtra("Advance_paid",model.getAdvance_Paid());
                i.putExtra("Total_Price",model.getTotal_Price());
                i.putExtra("Type_Of_Cloth",model.getType_Of_Cloth());
                i.putExtra("Extra_Details",model.getExtra_Details());
                i.putExtra("Neck",model.getNeck());
                i.putExtra("Shoulder",model.getShoulder());
                i.putExtra("Chest",model.getChest());
                i.putExtra("Cuff",model.getCuff());
                i.putExtra("Waist",model.getWaist());
                i.putExtra("Bottom_width",model.getBottom_Width());
                i.putExtra("Total_length",model.getTotal_Length());
                i.putExtra("Seat_length",model.getSeat_Length());
                i.putExtra("Sleeve_length",model.getSleeve_Length());
                i.putExtra("Bicep",model.getBicep());
                i.putExtra("Counter",model.getOrder_No());
                i.putExtra("Category",model.getCategory());
                i.putExtra("Image_Url",model.getImage_Url());
                v.getContext().startActivity(i);


            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),extra_details_upper_body.class);
                i.putExtra("First_Name",model.getFirst_Name());
                i.putExtra("Last_Name",model.getLast_Name());
                i.putExtra("Phone",model.getPhone());
                i.putExtra("Extra_Material",model.getExtra_Material());
                i.putExtra("Email",model.getEmail());
                i.putExtra("Due_Date",model.getDue_Date());
                i.putExtra("Current_Date",model.getCurrent_Date());
                i.putExtra("Advance_paid",model.getAdvance_Paid());
                i.putExtra("Total_Price",model.getTotal_Price());
                i.putExtra("Type_Of_Cloth",model.getType_Of_Cloth());
                i.putExtra("Extra_Details",model.getExtra_Details());
                i.putExtra("Neck",model.getNeck());
                i.putExtra("Shoulder",model.getShoulder());
                i.putExtra("Chest",model.getChest());
                i.putExtra("Cuff",model.getCuff());
                i.putExtra("Waist",model.getWaist());
                i.putExtra("Bottom_width",model.getBottom_Width());
                i.putExtra("Total_length",model.getTotal_Length());
                i.putExtra("Seat_length",model.getSeat_Length());
                i.putExtra("Sleeve_length",model.getSleeve_Length());
                i.putExtra("Bicep",model.getBicep());
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
