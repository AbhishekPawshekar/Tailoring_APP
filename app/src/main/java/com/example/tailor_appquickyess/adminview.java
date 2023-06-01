package com.example.tailor_appquickyess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class adminview extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView singinfullname,singinuser,singinemail;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    Toolbar toolbar;
    FirebaseFirestore fbs= FirebaseFirestore.getInstance();
    FirebaseAuth fba=FirebaseAuth.getInstance() ;
    FirebaseUser singinuserdata=fba.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminview);
        drawerLayout = findViewById(R.id.drawerlayoutadmin);
        navigationView = findViewById(R.id.nav_viewadmin);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.framedisplayadmin,new admin_home()).commit();


    }

        @Override
        public void onBackPressed() {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Are You Sure You Want To Exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.home:
                startActivity(new Intent(this,adminview.class));
                break;
            case R.id.listofcustomer:
                    startActivity(new Intent(getApplicationContext(),list_of_customer.class));
                break;
            case R.id.view_orders:
                startActivity(new Intent(getApplicationContext(),view_orders_admin.class));
                break;
            case R.id.shirt:
                startActivity(new Intent(getApplicationContext(),men_shirt_click.class));

                break;
            case R.id.blazer:
                startActivity(new Intent(getApplicationContext(),men_blazer_click.class));
                break;
            case R.id.jacket:
                startActivity(new Intent(getApplicationContext(),men_jacket_click.class));
                break;
            case R.id.formalpant:
                startActivity(new Intent(getApplicationContext(),men_formal_pant_click.class));
                break;
            case R.id.jeans:
                startActivity(new Intent(getApplicationContext(),men_jeans_click.class));
                break;
            case R.id.wedding_dress:
                startActivity(new Intent(getApplicationContext(),female_wedding_dress.class));
                break;
            case R.id.dress:
                startActivity(new Intent(getApplicationContext(),female_dress.class));
                break;
            case R.id.shirtforfemale:
                startActivity(new Intent(getApplicationContext(),female_shirt_click.class));
                break;
            case R.id.pantforfemale:
                startActivity(new Intent(getApplicationContext(),female_pant_click.class));
                break;
            case R.id.skirts:
                startActivity(new Intent(getApplicationContext(),female_skirt_click.class));
                break;
            case R.id.feedback:
                startActivity(new Intent(getApplicationContext(),view_feedback.class));
                break;
            case R.id.logout_admin:
                if(singinuserdata!=null)
                {
                    Intent i=new Intent(getApplicationContext(),login_page.class);
                    fba.signOut();
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"already Logout",Toast.LENGTH_SHORT).show();
                }
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }


}