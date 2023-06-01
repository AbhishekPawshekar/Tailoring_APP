package com.example.tailor_appquickyess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class view_feedback extends AppCompatActivity {
RecyclerView rv; 
EditText comments;
Button submit;
String counter;
int commentcounter;
FirebaseFirestore fb=FirebaseFirestore.getInstance();
FirebaseAuth fa=FirebaseAuth.getInstance();
FirebaseUser fu=fa.getCurrentUser();
feedback_adapter myadpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);
        rv=findViewById(R.id.recyclerview_feedback);
        rv.setLayoutManager(new LinearLayoutManager(this));
        comments=findViewById(R.id.feedback_comment);
        submit=findViewById(R.id.add_comment);
        
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fu.getEmail().equals("tailorappquickyes@gmail.com")){
                    DocumentReference dr=fb.collection("admin").document(fu.getEmail());
                    dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            counter = (String) task.getResult().get("Comment_Count");
                            commentcounter = Integer.parseInt(counter);
                            Map<String, String> obj = new HashMap<>();
                            obj.put("Comment", comments.getText().toString());
                            obj.put("Email", fu.getEmail());
                            fb.collection("Feedback").document(fu.getEmail() + counter).set(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        DocumentReference dr1 = fb.collection("admin").document(fu.getEmail());
                                        String setcounter = String.valueOf(commentcounter + 1);
                                        dr1.update("Comment_Count", setcounter).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(getApplicationContext(), "Comment Posted.....", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), view_feedback.class));
                                            }
                                        });
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Error:" + e, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }});
                }
                else{
                DocumentReference dr=fb.collection("Customer").document(fu.getEmail());
                dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        counter = (String) task.getResult().get("Comment_Count");
                        commentcounter = Integer.parseInt(counter);
                        Map<String, String> obj = new HashMap<>();
                        obj.put("Comment", comments.getText().toString());
                        obj.put("Email", fu.getEmail());
                        fb.collection("Feedback").document(fu.getEmail() + counter).set(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    DocumentReference dr1 = fb.collection("Customer").document(fu.getEmail());
                                    String setcounter = String.valueOf(commentcounter + 1);
                                    dr1.update("Comment_Count", setcounter).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getApplicationContext(), "Comment Posted.....", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), view_feedback.class));
                                        }
                                    });
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Error:" + e, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }});
            }}
        });
        
        
        
        
        
        
        FirestoreRecyclerOptions<feedback_class> options= new FirestoreRecyclerOptions.Builder<feedback_class>()
                .setQuery(FirebaseFirestore.getInstance().collection("Feedback"),feedback_class.class)
                .build();
        myadpter=new feedback_adapter(options);
        rv.setAdapter(myadpter);
    }
    @Override
    public void onStart() {
        super.onStart();
        myadpter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        myadpter.stopListening();
    }

}