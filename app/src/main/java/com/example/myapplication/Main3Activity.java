package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Model.Users;
import com.example.myapplication.remember.remember;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class Main3Activity extends AppCompatActivity {
    private Button login;
    private EditText password, companyname;
    private ProgressDialog loading;
    private CheckBox checkBox;
    private remember remember;
    private String u="Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        companyname = (EditText) findViewById(R.id.companyname);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        Paper.init(this);
        final String gst = Paper.book().read(remember.gst);
        final String pass = Paper.book().read(remember.pass);
        if (gst != "" && pass != "")
        {
            if (!TextUtils.isEmpty(gst) && !TextUtils.isEmpty(pass))
            {
                loading = new ProgressDialog(Main3Activity.this);
                loading.setTitle("Logging in");
                loading.setMessage("Please wait...");
                loading.setCanceledOnTouchOutside(false);
                loading.show();
                Toast.makeText(Main3Activity.this, "Logged in", Toast.LENGTH_SHORT).show();
                loading.dismiss();
                Intent intent = new Intent(Main3Activity.this, Home.class);
                startActivity(intent);
                finish();

            }
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logintoaccount();
            }
        });
    }

    private void logged(final String gst, final String pass) {
        final DatabaseReference RoofRef;
        RoofRef = FirebaseDatabase.getInstance().getReference();
        RoofRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(u).child(gst).exists()) {
                    Users userdata = dataSnapshot.child(u).child(gst).getValue(Users.class);
                    if (userdata.getGst_number().equals(gst)) {
                        if (userdata.getPassword().equals(pass)) {
                            Toast.makeText(Main3Activity.this, "Logged in", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                            Intent intent = new Intent(Main3Activity.this, Home.class);
                            startActivity(intent);
                            finish();

                        }
                        else {
                            Toast.makeText(Main3Activity.this, "Password is wrong", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    }
                } else {
                    Toast.makeText(Main3Activity.this, "Account do not exists", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void logintoaccount() {
        String gst_number = companyname.getText().toString();
        String pass = password.getText().toString();
        loading = new ProgressDialog(Main3Activity.this);
        if (TextUtils.isEmpty(gst_number)) {
            Toast.makeText(Main3Activity.this, "Please Enter your Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(Main3Activity.this, "Please Enter your GST Number", Toast.LENGTH_SHORT).show();
        } else {
            loading.setTitle("Loading");
            loading.setMessage("Please wait, while we are checking the credentials.");
            loading.setCanceledOnTouchOutside(false);
            loading.show();
            checkpass(gst_number, pass);
        }

    }

    private void checkpass(final String gst_number, final String pass) {
        Paper.book().write(com.example.myapplication.remember.remember.gst, gst_number);
        if (checkBox.isChecked()) {
           Paper.book().write(com.example.myapplication.remember.remember.pass, pass);
        }
        final DatabaseReference RoofRef;
        RoofRef = FirebaseDatabase.getInstance().getReference();
        RoofRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(u).child(gst_number).exists()) {
                    Users userdata = dataSnapshot.child(u).child(gst_number).getValue(Users.class);
                    if (userdata.getGst_number().equals(gst_number)) {
                        if (userdata.getPassword().equals(pass)) {

                            Toast.makeText(Main3Activity.this, "Logged in..", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                            Intent intent = new Intent(Main3Activity.this, Home.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Main3Activity.this, "Password is wrong", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    }
                } else {
                    Toast.makeText(Main3Activity.this, "Account do not exists", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}