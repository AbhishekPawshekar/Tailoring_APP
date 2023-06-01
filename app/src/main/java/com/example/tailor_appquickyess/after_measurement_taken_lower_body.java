package com.example.tailor_appquickyess;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class after_measurement_taken_lower_body extends AppCompatActivity {
    public static final int PICK_IMAGE_REQUEST=1;
    TextInputLayout email,advance_paid,total_peice,type_of_cloth,extra_details,extra_material;
    String front,back,inseam,knee,waist,total_length,leg_open,hip,type,counter,downloadurl1;
    ImageView img1;
    int ordercounter;
    EditText due_date,current_date;
    private int mYear, mMonth, mDay;
    Button b1,currentdate1,duedate;
    Uri imagefile;
    FirebaseFirestore fb=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_measurement_taken_lower_body);
        Intent i=getIntent();
        front=i.getStringExtra("Front_Rise");
        hip=i.getStringExtra("Hip_Length");
        inseam=i.getStringExtra("Inseam_Length");
        knee=i.getStringExtra("Knee_Length");
        waist=i.getStringExtra("Waist");
        back=i.getStringExtra("Back_Rise");
        total_length=i.getStringExtra("Total_Length");
        leg_open=i.getStringExtra("Leg_Open");
        type=i.getStringExtra("Type_Of_cloth");
currentdate1=findViewById(R.id.selectcurrentbutton_lower_body);
duedate=findViewById(R.id.selectdatebutton_lower_body);
        email=findViewById(R.id.customer_email_lower_body);
        due_date=findViewById(R.id.due_date_lower_body);
        current_date=findViewById(R.id.date_of_order_lower_body);
        advance_paid=findViewById(R.id.advance_given_lower_body);
        total_peice=findViewById(R.id.Total_price_lower_body);
        type_of_cloth=findViewById(R.id.type_lower_body);
        extra_details=findViewById(R.id.extradetails_lower_body);
        extra_material=findViewById(R.id.extra_material_lower_body);
        img1=findViewById(R.id.img1_lower_body);
        b1=findViewById(R.id.submit_lower_body);

        duedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(after_measurement_taken_lower_body.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                due_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        currentdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(after_measurement_taken_lower_body.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                current_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,PICK_IMAGE_REQUEST);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1=email.getEditText().getText().toString().toLowerCase().trim();

                fb.collection("Customer").document(email1).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            String email1=email.getEditText().getText().toString().toLowerCase().trim();
                            DocumentReference drproduct=fb.collection("Customer").document(email1);
                            drproduct.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    counter= (String) task.getResult().get("Order_No");
                                    ordercounter=Integer.parseInt(counter);
                                    getreducemoreimagesize(imagefile,counter);
                                }
                            });
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Email Address Not Present In Record",Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error:" + e, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imagefile=data.getData();
            img1.setImageURI(imagefile);
        }
    }
    public void getreducemoreimagesize(Uri imageuri, String location){
//code to reduce size and add to firebase storage
        String email1=email.getEditText().getText().toString().toLowerCase().trim();

        StorageReference riversRef = FirebaseStorage.getInstance().getReference().child(email1+location);
        Bitmap fullsizebitmap=null;

        try {
            fullsizebitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), imageuri);
        }catch (IOException e)
        {e.printStackTrace(); }

        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        fullsizebitmap.compress(Bitmap.CompressFormat.JPEG,25,bos);
        byte[] bitmapdata=bos.toByteArray();
        UploadTask uploadTask=riversRef.putBytes(bitmapdata);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(),"Image Uploaded Successfully!!!",Toast.LENGTH_SHORT).show();
                setdata();
            }
        });

    }
    public void setdata(){
        String email1=email.getEditText().getText().toString().toLowerCase().trim();
        StorageReference riverRef1 = FirebaseStorage.getInstance().getReference().child(email1+counter);
        riverRef1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            public void onSuccess(Uri uri) {
                downloadurl1 = uri.toString();
                String email1=email.getEditText().getText().toString().toLowerCase().trim();
                fb.collection("Customer").document(email1).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            String first_name = (String) task.getResult().get("First_Name");
                            String last_name = (String) task.getResult().get("Last_Name");
                            String phone = (String) task.getResult().get("Phone");
                            String email1=email.getEditText().getText().toString().toLowerCase().trim();
                            String due_date1 = due_date.getText().toString();
                            String current_date1 = current_date.getText().toString();
                            String advance_paid1 = advance_paid.getEditText().getText().toString().trim();
                            String total_price = total_peice.getEditText().getText().toString().trim();
                            String type_of_cloth1 = type_of_cloth.getEditText().getText().toString();
                            String extra_details1 = extra_details.getEditText().getText().toString();
                            Map<String, String> data = new HashMap<>();
                            data.put("First_Name",first_name);
                            data.put("Last_Name",last_name);
                            data.put("Phone",phone);
                            data.put("Image_Url", downloadurl1);
                            data.put("Extra_Material",extra_material.getEditText().getText().toString());
                            data.put("Email", email1);
                            data.put("Order_No", counter);
                            data.put("Due_Date", due_date1);
                            data.put("Current_Date", current_date1);
                            data.put("Advance_Paid", advance_paid1);
                            data.put("Total_Price", total_price);
                            data.put("Type_Of_Cloth", type_of_cloth1);
                            data.put("Extra_Details", extra_details1);
                            data.put("Front_Rise", front);
                            data.put("Hip_Length", hip);
                            data.put("Inseam", inseam);
                            data.put("Knee_Length", knee);
                            data.put("Waist", waist);
                            data.put("Back_Rise", back);
                            data.put("Total_Length", total_length);
                            data.put("Leg_Open", leg_open);
                            data.put("Category", type);
                            data.put("Body_Part", "Lower_Body");
                            fb.collection("Orders").document(email1 + counter).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String email1 = email.getEditText().getText().toString().toLowerCase().trim();
                                    DocumentReference dr1 = fb.collection("Customer").document(email1);
                                    String setcounter = String.valueOf(ordercounter + 1);
                                    dr1.update("Order_No", setcounter).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getApplicationContext(), "Successfully Applied!!!!", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(), adminview.class);
                                            startActivity(i);
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Error:" + e, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}