package com.example.tailor_appquickyess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class female_skirt_click extends AppCompatActivity {
    TextInputLayout hip_length,waist,total_length,bottom_width;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female_skirt_click);
        waist=findViewById(R.id.waist_width_female_skirt);
        bottom_width=findViewById(R.id.bottom_width_female_skirt);
        total_length=findViewById(R.id.Total_length_female_skirt);
        next=findViewById(R.id.next_button_female_skirt);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String waist1=waist.getEditText().getText().toString().trim();
                String total_length1=total_length.getEditText().getText().toString().trim();
                String bottom1=bottom_width.getEditText().getText().toString().trim();
                Intent i=new Intent(getApplicationContext(),after_measurement_taken_lower_body.class);
                i.putExtra("Front_Rise","0");
                i.putExtra("Hip_Length","0");
                i.putExtra("Inseam_Length","0");
                i.putExtra("Knee_Length", "0");
                i.putExtra("Waist",waist1);
                i.putExtra("Back_Rise","0");
                i.putExtra("Total_Length",total_length1);
                i.putExtra("Leg_Open","0");
                i.putExtra("Type_Of_cloth","Female_Pant");
                i.putExtra("Bottom_Width",bottom1);
                startActivity(i);
            }
        });
    }
}