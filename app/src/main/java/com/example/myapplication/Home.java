package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.Users;
import com.example.myapplication.remember.remember;
import com.example.myapplication.ui.order_history_user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import io.paperdb.Paper;

public class Home extends AppCompatActivity {
    private EditText chakki_atta50,chakki_atta20,suji50,suji20,maida50,maida20;
    private Button placeorder,branded,nonbranded,logout,cart,order_history;
    private ProgressDialog loadingBar;
    private TextView brandrn,numorder;
    private String savecurrentdate,savecurrenttime,orderid,company_name;
    public String brandtype;
    public String maida20br="0",chakki_atta50br="0",chakki_atta20br="0",maida50br="0",suji50br="0",suji20br="0",maida20nbr="0",chakki_atta50nbr="0",chakki_atta20nbr="0",maida50nbr="0",suji50nbr="0",suji20nbr="0";
    public final String gst = Paper.book().read(remember.gst);
    public int sum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        brandtype="Branded";
        order_history=(Button) findViewById(R.id.button8);
        brandrn=(TextView) findViewById(R.id.textView16);
        brandrn.setText("Branded");
        cart=(Button)findViewById(R.id.button6);
        numorder=(TextView) findViewById(R.id.textView21);
        logout=(Button) findViewById(R.id.button3);
        chakki_atta50=(EditText) findViewById(R.id.editText30);
        chakki_atta20=(EditText) findViewById(R.id.editText28);
        maida50=(EditText) findViewById(R.id.editText25);
        maida20=(EditText) findViewById(R.id.editText10);
        suji50=(EditText) findViewById(R.id.editText26);
        suji20=(EditText) findViewById(R.id.editText27);
        placeorder=(Button) findViewById(R.id.button7);
        branded=(Button) findViewById(R.id.button4);
        nonbranded=(Button) findViewById(R.id.button5);
        storecompanyname();
        order_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, order_history_user.class);
                startActivity(intent);

            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(brandtype=="Branded")
                {
                    chakki_atta50br=chakki_atta50.getText().toString().trim();
                    chakki_atta20br = chakki_atta20.getText().toString().trim();
                    maida50br = maida50.getText().toString().trim();
                    maida20br = maida20.getText().toString().trim();
                    suji50br = suji50.getText().toString().trim();
                    suji20br = suji20.getText().toString().trim();
                    if(chakki_atta50br.equals(""))
                    {
                        chakki_atta50br="0";
                    }
                    if(chakki_atta20br.equals(""))
                    {
                        chakki_atta20br="0";
                    }
                    if(maida20br.equals(""))
                    {
                        maida20br="0";
                    }
                    if(maida50br.equals(""))
                    {
                        maida50br="0";
                    }
                    if(suji50br.equals(""))
                    {
                        suji50br="0";
                    }
                    if(suji20br.equals(""))
                    {
                        suji20br="0";
                    }
                    chakki_atta20.setText("");
                    chakki_atta50.setText("");
                    maida50.setText("");
                    maida20.setText("");
                    suji50.setText("");
                    suji20.setText("");
                    sum=sum+Integer.parseInt(chakki_atta20br)+Integer.parseInt(chakki_atta50br)+Integer.parseInt(maida20br)+Integer.parseInt(maida50br)+Integer.parseInt(suji20br)+Integer.parseInt(suji50br);
                    numorder.setText(Integer.toString(sum));
                    Toast.makeText(Home.this, "Added to cart", Toast.LENGTH_SHORT).show();
                }
                if(brandtype=="Non-Branded")
                {
                    if (!(chakki_atta50.getText().toString().trim().equals("")))
                    {
                        chakki_atta50nbr=chakki_atta50.getText().toString().trim();
                    }
                    if(!(chakki_atta20.getText().toString().trim().equals("")))
                    {
                        chakki_atta20nbr = chakki_atta20.getText().toString().trim();
                    }
                    if(!(maida50.getText().toString().trim().equals("")))
                    {
                        maida50nbr = maida50.getText().toString().trim();
                    }
                    if(!(maida20.getText().toString().trim().equals("")))
                    {
                        maida20nbr = maida20.getText().toString().trim();
                    }
                    if(!(suji50.getText().toString().trim().equals("")))
                    {
                        suji50nbr = suji50.getText().toString().trim();
                    }
                    if(!(suji20.getText().toString().trim().equals("")))
                    {
                        suji20nbr = suji20.getText().toString().trim();
                    }
                    sum=sum+Integer.parseInt(chakki_atta20nbr)+Integer.parseInt(chakki_atta50nbr)+Integer.parseInt(maida20nbr)+Integer.parseInt(maida50nbr)+Integer.parseInt(suji20nbr)+Integer.parseInt(suji50nbr);
                    numorder.setText(Integer.toString(sum));

                    chakki_atta20.setText("");
                    chakki_atta50.setText("");
                    maida50.setText("");
                    maida20.setText("");
                    suji50.setText("");
                    suji20.setText("");
                    Toast.makeText(Home.this, "Added to cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
        nonbranded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             brandtype="Non-Branded";
             brandrn.setText("Non-Branded");
            }
        });
        branded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brandtype="Branded";
                brandrn.setText("Branded");
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().delete(remember.gst);
                Paper.book().delete(remember.pass);
                Intent intent = new Intent(Home.this, Main3Activity.class);
                startActivity(intent);
                finish();
            }
        });
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String conform="no";
                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("MM-dd-yyyy");
                savecurrentdate=currentDate.format(calendar.getTime());
                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                savecurrenttime=currentTime.format(calendar.getTime());
                final String gst = Paper.book().read(remember.gst);
                orderid=gst+savecurrentdate+savecurrenttime;
                loadingBar = new ProgressDialog(Home.this);
                loadingBar.setTitle("Processing Your order");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                uploaddata(conform,orderid);

            }
        });
    }
    private void cartview(final String conform, final String orderid)
    {
        Paper.book().write(remember.orderid, orderid);
        Paper.book().write(remember.date, savecurrentdate);
        Paper.book().write(remember.company_name, company_name);
        Paper.book().write(remember.chakki_atta50br,chakki_atta50br);
        Paper.book().write(remember.chakki_atta20br,chakki_atta20br);
        Paper.book().write(remember.maida20br,maida20br);
        Paper.book().write(remember.maida50br,maida50br);
        Paper.book().write(remember.suji20br,suji20br);
        Paper.book().write(remember.suji50br,suji50br);
        Paper.book().write(remember.chakki_atta50nbr,chakki_atta50nbr);
        Paper.book().write(remember.chakki_atta20nbr,chakki_atta20nbr);
        Paper.book().write(remember.maida20nbr,maida20nbr);
        Paper.book().write(remember.maida50nbr,maida50nbr);
        Paper.book().write(remember.suji20nbr,suji20nbr);
        Paper.book().write(remember.suji50nbr,suji50nbr);
    }

    private void uploaddata(final String conform,final String orderid)
    {

        Paper.book().write(remember.orderid, orderid);
        final DatabaseReference RoofRef;
        RoofRef = FirebaseDatabase.getInstance().getReference();
        RoofRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Order").child(orderid).exists()))
                {
                    HashMap<String, Object> orderdata = new HashMap<>();
                    orderdata.put("date",savecurrentdate);
                    orderdata.put("company_name",company_name);
                    orderdata.put("gst_number",gst);
                    orderdata.put("conform",conform);
                    orderdata.put("admin_conform",conform);
                    orderdata.put("orderid", orderid);
                    orderdata.put("chakki_atta50", chakki_atta50br);
                    orderdata.put("chakki_atta20", chakki_atta20br);
                    orderdata.put("maida50", maida50br);
                    orderdata.put("maida20", maida20br);
                    orderdata.put("suji50", suji50br);
                    orderdata.put("suji20", suji20br);
                    orderdata.put("chakki_attan50", chakki_atta50nbr);
                    orderdata.put("chakki_attan20", chakki_atta20nbr);
                    orderdata.put("maidan50", maida50nbr);
                    orderdata.put("maidan20", maida20nbr);
                    orderdata.put("sujin50", suji50nbr);
                    orderdata.put("sujin20", suji20nbr);
                    maida20br="0";
                    chakki_atta50br="0";
                    chakki_atta20br="0";
                    maida50br="0";
                    suji50br="0";
                    suji20br="0";
                    maida20nbr="0";
                    chakki_atta50nbr="0";
                    chakki_atta20nbr="0";
                    maida50nbr="0";
                    suji50nbr="0";
                    suji20nbr="0";
                    RoofRef.child("Order").child(orderid).updateChildren(orderdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(Home.this, "Your order summary", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                sum=0;
                                Intent intent = new Intent(Home.this, conform.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                loadingBar.dismiss();
                                Toast.makeText(Home.this, "Check you Internet connection", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(Home.this, "Order ID error", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void storecompanyname()
    {
        final String gst = Paper.book().read(remember.gst);
        final DatabaseReference RoofRef;
        RoofRef = FirebaseDatabase.getInstance().getReference();
        RoofRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(gst).exists()) {
                    Users users = dataSnapshot.child("Users").child(gst).getValue(Users.class);
                    company_name=users.getCompany_name();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}