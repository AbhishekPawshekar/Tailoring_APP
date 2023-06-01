package com.example.tailor_appquickyess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.annotation.KeepName;
import com.google.android.material.textfield.TextInputLayout;

public class men_formal_pant_click extends AppCompatActivity {
    TextInputLayout front_rise,hip_length,inseam_length,waist,knee_length,total_length,backraise,legopen;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men_formal_pant_click);
        front_rise=findViewById(R.id.front_rise_men_formal_pant);
        waist=findViewById(R.id.waist_width_men_formal_pant);
        hip_length=findViewById(R.id.hip_length_men_formal_pant);
        inseam_length=findViewById(R.id.inseamlength_men_formal_pant);
        knee_length=findViewById(R.id.knee_length_men_formal_pant);
        backraise=findViewById(R.id.backraise_men_formal_pant);
        total_length=findViewById(R.id.Total_length_men_formal_pant);
        legopen=findViewById(R.id.legopenmen_formal_pant);
        next=findViewById(R.id.next_button_formal_pant);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String frontrise1=front_rise.getEditText().getText().toString().trim();
                String waist1=waist.getEditText().getText().toString().trim();
                String hip1=hip_length.getEditText().getText().toString().trim();
                String inseam1=inseam_length.getEditText().getText().toString().trim();
                String knee1=knee_length.getEditText().getText().toString().trim();
                String backraise1=backraise.getEditText().getText().toString().trim();
                String total_length1=total_length.getEditText().getText().toString().trim();
                String legopen1=legopen.getEditText().getText().toString().trim();
                Intent i=new Intent(getApplicationContext(),after_measurement_taken_lower_body.class);
                i.putExtra("Front_Rise",frontrise1);
                i.putExtra("Hip_Length",hip1);
                i.putExtra("Inseam_Length",inseam1);
                i.putExtra("Knee_Length", knee1);
                i.putExtra("Waist",waist1);
                i.putExtra("Back_Rise",backraise1);
                i.putExtra("Total_Length",total_length1);
                i.putExtra("Leg_Open",legopen1);
                i.putExtra("Type_Of_cloth","Men_Formal_Pant");
                startActivity(i);
            }
        });
    }
}