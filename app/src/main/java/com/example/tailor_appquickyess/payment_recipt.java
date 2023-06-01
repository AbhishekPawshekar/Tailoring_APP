package com.example.tailor_appquickyess;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class payment_recipt extends AppCompatActivity {
WebView webView;
Button button;
FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
String fn,ln,ph,cl,em,dd,od,tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_recipt);
        webView=findViewById(R.id.webview);
        button=findViewById(R.id.printrecipt);
        Intent i=getIntent();
        cl=i.getStringExtra("cloth");
        em=i.getStringExtra("email");
        dd=i.getStringExtra("due_date");
        od=i.getStringExtra("order_date");
        tt=i.getStringExtra("total");
        fbfs.collection("Customer").document(em).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    fn=(String)task.getResult().get("First_Name");
                     ln=(String) task.getResult().get("Last_Name");
                    ph=(String)task.getResult().get("Phone");
                    String html="<html>\n" +
                            "<header>\n" +
                            "<style>\n" +
                            "table, th, td {\n" +
                            "  border: 1px solid black;\n" +
                            "}\n" +
                            "table.center {\n" +
                            "  margin-left: auto;\n" +
                            "  margin-right: auto;\n" +
                            "}\n" +
                            "th, td {\n" +
                            "  padding: 10px;\n" +
                            "}\n" +
                            "</style>\n" +
                            "</header>\n" +
                            "<body>\n" +
                            "\n" +
                            "<h2 style=\"font-family:Times New Roman\"><center><br>TAILORING APPLICATION</center></h2>\n" +
                            "<br>\n" +
                            "<div style=\"float:left;\"><h4>Email-id:-jspms@gmail.com</h4></div>\n" +
                            "<div style=\"float:right ;height:80;width:300\"><h5>Address:-Kumar Pebble Park Rd, Satar Nagar, Hadapsar, Autadwadi Handewadi, Maharashtra 411028</h5></div><br><br><br><br>\n" +
                            "<hr/><br><br><br><br><br>\n" +
                            "<center>\n" +
                            "<table class=\"center\">\n" +
                            "  <tr>\n" +
                            "    <th>First Name</th>\n" +
                            "    <th>Last_Name</th>\n" +
                            "    <th>Stiched Cloth</th>\n" +
                            "    <th>Phone</th>\n" +
                            "    <th>Email-Id</th>\n" +
                            "    <th>Order Date</th>\n" +
                            "    <th>Due Date</th>\n" +
                            "   <th>Total Amount</th>\n" +
                            "    <th>Payment</th>\n" +
                            "  \n" +
                            "  </tr>\n" +
                            "  <tr>\n" +
                            "    <td>"+fn+"</td>\n" +
                            "    <td>"+ln+"</td>\n" +
                            " <td>"+cl+"</td>\n" +
                            "     <td>"+ph+"</td>\n" +
                            "<td>"+em+"</td>\n" +
                            "<td>"+od+"</td>\n" +
                            "<td>"+dd+"</td>\n" +
                            "<td>"+tt+"</td>\n" +
                            "<td>Done</td>\n" +
                            "  </tr>\n" +
                            "</table>\n" +
                            "</center>\n" +
                            "\n" +
                            "</body>\n" +
                            "</html>";
                    webView.loadDataWithBaseURL(null,html,"text/html","utf-8",null);
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Context context=payment_recipt.this;
                PrintManager printManager=(PrintManager) payment_recipt.this.getSystemService(context.PRINT_SERVICE);
                PrintDocumentAdapter adapter=null;
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
adapter=webView.createPrintDocumentAdapter();
                }
                String JobName=getString(R.string.app_name)+"Document";
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
                    PrintJob printJob=printManager.print(JobName,adapter,new PrintAttributes.Builder().build());
                }
            }
        });
    }
}