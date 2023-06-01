package com.example.tailor_appquickyess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class extra_details_lower_body extends AppCompatActivity {
    String fn,ln,ph,email,dd,cd,ap,tp,toc,ed,f,h,in,kn,wa,ba,tl,lo,counter,category,img,em;
    TextView fn1,ln1,ph1,email1,dd1,cd1,ap1,tp1,toc1,ed1,f1,h1,in1,kn1,wa1,ba1,tl1,lo1,em1;
    Button button;
    FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_details_lower_body);
        Intent i = getIntent();
        fn = i.getStringExtra("First_Name");
        ln = i.getStringExtra("Last_Name");
        ph = i.getStringExtra("Phone");
        email = i.getStringExtra("Email");
        dd = i.getStringExtra("Due_Date");
        cd = i.getStringExtra("Current_Date");
        ap = i.getStringExtra("Advance_paid");
        tp = i.getStringExtra("Total_Price");
        toc = i.getStringExtra("Type_Of_Cloth");
        ed = i.getStringExtra("Extra_Details");
        em=i.getStringExtra("Extra_Material");
        f = i.getStringExtra("Front_Rise");
        h = i.getStringExtra("Hip_Length");
        in = i.getStringExtra("Inseam");
        kn = i.getStringExtra("Knee_Length");
        wa = i.getStringExtra("Waist");
        ba = i.getStringExtra("Back_Rise");
        tl = i.getStringExtra("Total_Length");
        lo = i.getStringExtra("Leg_Open");
        counter = i.getStringExtra("Counter");
        category = i.getStringExtra("Category");
        img = i.getStringExtra("Image_Url");
        fn1 = findViewById(R.id.first_name_lower);
        ln1 = findViewById(R.id.last_name_lower);
        ph1 = findViewById(R.id.phone_no_lower);
        email1 = findViewById(R.id.email_lower);
        dd1 = findViewById(R.id.due_date_lower);
        cd1 = findViewById(R.id.order_date_lower);
        ap1 = findViewById(R.id.advance_given_lower);
        tp1 = findViewById(R.id.total_price_lower);
        toc1 = findViewById(R.id.type_of_cloth_lower);
        ed1 = findViewById(R.id.extradetails_lower);
        em1=findViewById(R.id.extra_material_lower);
        f1 = findViewById(R.id.front_rise_measurement);
        h1 = findViewById(R.id.hip_measurement);
        in1 = findViewById(R.id.inseam_measurement);
        kn1 = findViewById(R.id.knee_measurement);
        ba1 = findViewById(R.id.backraise_measurement);
        lo1 = findViewById(R.id.legopen_length_measurement);
        tl1 = findViewById(R.id.total_length_measurement);
        wa1 = findViewById(R.id.waist_width_measurement);
        button = findViewById(R.id.order_completed_lower);
        em1.setText(em);
        fn1.setText(fn);
        ln1.setText(ln);
        ph1.setText(ph);
        email1.setText(email);
        dd1.setText(dd);
        cd1.setText(cd);
        ap1.setText(ap);
        tp1.setText(tp);
        toc1.setText(toc);
        ed1.setText(ed);
        f1.setText(f);
        ba1.setText(ba);
        h1.setText(h);
        in1.setText(in);
        wa1.setText(wa);
        kn1.setText(kn);
        tl1.setText(tl);
        lo1.setText(lo);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Click On 'Submit' for successfully Completed Order:\n ")
                        .setCancelable(false)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Map<String,String> data=new HashMap<>();
                                data.put("Email",email);
                                data.put("Order_No",counter);
                                data.put("Due_Date",dd);
                                data.put("Current_Date",cd);
                                data.put("Advance_Paid",ap);
                                data.put("Total_Price",tp);
                                data.put("Payment","False");
                                data.put("Type_Of_Cloth",toc);
                                data.put("First_Name",fn);
                                data.put("Last_Name",ln);
                                data.put("Phone",ph);
                                data.put("Category",category);
                                data.put("Image_Url",img);

                                fbfs.collection("Order_Completed").document(email+counter).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            fbfs.collection("Orders").document(email+counter).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                        Toast.makeText(getApplicationContext(),"Done!!",Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(),"Error:"+e,Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }).setNegativeButton("Don't Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}