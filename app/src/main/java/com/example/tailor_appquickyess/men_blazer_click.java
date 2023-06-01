package com.example.tailor_appquickyess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class men_blazer_click extends AppCompatActivity {
    TextInputLayout neck,shoulder,chest,cuff_circumference,waist,bicep_width,total_length,seat,sleevelength;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men_blazer_click);
        neck=findViewById(R.id.neck_men_blazer);
        shoulder=findViewById(R.id.shoulder_men_blazer);
        chest=findViewById(R.id.chest_men_blazer);
        cuff_circumference=findViewById(R.id.cuff_circumference_length_men_blazer);
        waist=findViewById(R.id.waist_men_blazer);
        bicep_width=findViewById(R.id.bicep_men_blazer);
        total_length=findViewById(R.id.Total_length_men_blazer);
        seat=findViewById(R.id.seat_men_blazer);
        sleevelength=findViewById(R.id.sleeve_length_men_blazer);
        next=findViewById(R.id.next_button_blazer);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String neck1=neck.getEditText().getText().toString().trim();
                String shoulder1=shoulder.getEditText().getText().toString().trim();
                String chest1=chest.getEditText().getText().toString().trim();
                String cuff_circumference1=cuff_circumference.getEditText().getText().toString().trim();
                String waist1=waist.getEditText().getText().toString().trim();
                String bicep_width1=bicep_width.getEditText().getText().toString().trim();
                String total_length1=total_length.getEditText().getText().toString().trim();
                String seat1=seat.getEditText().getText().toString().trim();
                String sleevelength1=sleevelength.getEditText().getText().toString().trim();
                Intent i=new Intent(getApplicationContext(),after_measurement_taken.class);
                i.putExtra("Neck",neck1);
                i.putExtra("Shoulder",shoulder1);
                i.putExtra("Chest",chest1);
                i.putExtra("Cuff_Circumference",cuff_circumference1);
                i.putExtra("Waist",waist1);
                i.putExtra("Bottom","0");
                i.putExtra("Total_Length",total_length1);
                i.putExtra("Seat",seat1);
                i.putExtra("Sleeve",sleevelength1);
                i.putExtra("Type_Of_cloth","Men_Blazer");
                i.putExtra("Bicep_Width",bicep_width1);
                startActivity(i);
            }
        });
    }
}