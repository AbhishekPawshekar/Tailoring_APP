package com.example.tailor_appquickyess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class female_wedding_dress extends AppCompatActivity {
    TextInputLayout neck,shoulder,chest,cuff_circumference,waist,bottom_width,total_length,seat,sleevelength;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female_wedding_dress);
        neck=findViewById(R.id.neck_female_weddingdress);
        shoulder=findViewById(R.id.shoulder_female_weddingdress);
        chest=findViewById(R.id.chest_female_weddingdress);
        cuff_circumference=findViewById(R.id.cuff_circumference_length_female_weddingdress);
        waist=findViewById(R.id.waist_female_weddingdress);
        total_length=findViewById(R.id.total_length_female_weddingdress);
        seat=findViewById(R.id.seat_female_weddingdress);
        sleevelength=findViewById(R.id.sleeve_length_female_weddingdress);
        next=findViewById(R.id.next_button_female_weddingdress);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String neck1=neck.getEditText().getText().toString().trim();
                String shoulder1=shoulder.getEditText().getText().toString().trim();
                String chest1=chest.getEditText().getText().toString().trim();
                String cuff_circumference1=cuff_circumference.getEditText().getText().toString().trim();
                String waist1=waist.getEditText().getText().toString().trim();
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
                i.putExtra("Bicep_Width","0");
                i.putExtra("Type_Of_cloth","Female_Wedding_Dress");
                startActivity(i);
            }
        });
    }
}