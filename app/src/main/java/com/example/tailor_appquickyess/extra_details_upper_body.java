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

public class extra_details_upper_body extends AppCompatActivity {
String fn,ln,ph,email,dd,cd,ap,tp,toc,ed,n,s,c,cu,wa,bw,tl,sl,sll,bi,counter,category,img,em;
TextView fn1,ln1,ph1,email1,dd1,cd1,ap1,tp1,toc1,ed1,n1,s1,c1,cu1,wa1,bw1,tl1,sl1,sll1,bi1,em1;
Button button;
FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_details_upper_body);
        Intent i=getIntent();
        fn=i.getStringExtra("First_Name");
        ln=i.getStringExtra("Last_Name");
        ph=i.getStringExtra("Phone");
       email= i.getStringExtra("Email");
        dd=i.getStringExtra("Due_Date");
        cd=i.getStringExtra("Current_Date");
        ap=i.getStringExtra("Advance_paid");
        tp=i.getStringExtra("Total_Price");
        toc=i.getStringExtra("Type_Of_Cloth");
        ed=i.getStringExtra("Extra_Details");
        em=i.getStringExtra("Extra_Material");
        n=i.getStringExtra("Neck");
        s=i.getStringExtra("Shoulder");
        c=i.getStringExtra("Chest");
        cu=i.getStringExtra("Cuff");
        wa=i.getStringExtra("Waist");
        bw=i.getStringExtra("Bottom_width");
        tl=i.getStringExtra("Total_length");
        sl=i.getStringExtra("Seat_length");
        sll=i.getStringExtra("Sleeve_length");
        bi=i.getStringExtra("Bicep");
        counter=i.getStringExtra("Counter");
     category=i.getStringExtra("Category");
     img=i.getStringExtra("Image_Url");

        fn1=findViewById(R.id.first_name_upper_body);
        ln1=findViewById(R.id.last_name_upper_body);
        ph1=findViewById(R.id.phone_no_upper_body);
        email1=findViewById(R.id.email_upper_body);
        dd1=findViewById(R.id.due_date_upper_body);
        cd1=findViewById(R.id.order_date_upper_body);
        ap1=findViewById(R.id.advance_given_upper_body);
        tp1=findViewById(R.id.total_price_upper_body);
        toc1=findViewById(R.id.type_of_cloth_upper_body);
        ed1=findViewById(R.id.extradetails_upper_body);
        em1=findViewById(R.id.extra_material_upper);
        n1=findViewById(R.id.neck_measurement);
        s1=findViewById(R.id.shoulder_measurement);
        c1=findViewById(R.id.chest_measurement);
        cu1=findViewById(R.id.cuff_measurement);
        wa1=findViewById(R.id.waist_measurement);
        bw1=findViewById(R.id.bottom_width_measurement);
        tl1=findViewById(R.id.total_length_measurement);
        sl1=findViewById(R.id.seat_length_measurement);
        sll1=findViewById(R.id.sleeve_length_measurement);
        bi1=findViewById(R.id.bicep_measurement);
        button=findViewById(R.id.order_completed);
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
        n1.setText(n);
        s1.setText(s);
        c1.setText(c);
        cu1.setText(cu);
        wa1.setText(wa);
        bw1.setText(bw);
        tl1.setText(tl);
        sl1.setText(sl);
        sll1.setText(sll);
        bi1.setText(bi);


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
                             data.put("Type_Of_Cloth",toc);
                             data.put("First_Name",fn);
                             data.put("Last_Name",ln);
                             data.put("Payment","False");
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