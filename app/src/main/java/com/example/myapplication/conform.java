package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.Order;
import com.example.myapplication.Model.Users;
import com.example.myapplication.remember.remember;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class conform extends AppCompatActivity {
    private ProgressDialog loadingBar;
    private TextView order1,order2,order3,order4,order5,order6,companyname,order7,order8,order9,order10,order11,order12;
    private Button conform,decline;
    private Order orderdata;
    private ProgressDialog loading;
    DatabaseReference conformupdate;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        loading = new ProgressDialog(conform.this);
        loading.setTitle("Declining the order");
        loading.setMessage("Please wait");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        final String orderid = Paper.book().read(remember.orderid);
        conformupdate = FirebaseDatabase.getInstance().getReference("Order");
        conformupdate.child(orderid).setValue(null);
        Paper.book().delete(remember.orderid);
        Toast.makeText(conform.this, "Renter your order please", Toast.LENGTH_SHORT).show();
        loading.dismiss();
        Intent intent = new Intent(conform.this, Home.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conform);
        companyname=(TextView) findViewById(R.id.textView5);
        order1=(TextView) findViewById(R.id.textView6);
        order2=(TextView) findViewById(R.id.textView7);
        order3=(TextView) findViewById(R.id.textView8);
        order4=(TextView) findViewById(R.id.textView9);
        order5=(TextView) findViewById(R.id.textView10);
        order6=(TextView) findViewById(R.id.textView11);
        order7=(TextView) findViewById(R.id.textView12);
        order8=(TextView) findViewById(R.id.textView13);
        order9=(TextView) findViewById(R.id.textView14);
        order10=(TextView) findViewById(R.id.textView16);
        order11=(TextView) findViewById(R.id.textView18);
        order12=(TextView) findViewById(R.id.textView19);
        conform=(Button) findViewById(R.id.button);
        decline=(Button) findViewById(R.id.button2);
        Paper.init(this);
        loadingBar = new ProgressDialog(conform.this);
        loadingBar.setTitle("loading...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        generateorder();
        printcompanyname();

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = new ProgressDialog(conform.this);
                loading.setTitle("Declining the order");
                loading.setMessage("Please wait");
                loading.setCanceledOnTouchOutside(false);
                loading.show();
                final String orderid = Paper.book().read(remember.orderid);
                conformupdate = FirebaseDatabase.getInstance().getReference("Order");
                conformupdate.child(orderid).setValue(null);
                Paper.book().delete(remember.orderid);
                Toast.makeText(conform.this, "Renter your order please", Toast.LENGTH_SHORT).show();
                loading.dismiss();
                Intent intent = new Intent(conform.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = new ProgressDialog(conform.this);
                loading.setTitle("Placing order");
                loading.setMessage("Please wait...");
                loading.setCanceledOnTouchOutside(false);
                loading.show();
                final String orderid = Paper.book().read(remember.orderid);
                conformupdate = FirebaseDatabase.getInstance().getReference("Order");
                conformupdate.child(orderid).child("conform").setValue("Yes");
                Paper.book().delete(remember.orderid);
                Toast.makeText(conform.this, "Your order has been Placed", Toast.LENGTH_SHORT).show();
                loading.dismiss();
                Intent intent = new Intent(conform.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void printcompanyname()
    {
        final String gst = Paper.book().read(remember.gst);
        final DatabaseReference RoofRef;
        RoofRef = FirebaseDatabase.getInstance().getReference();
        RoofRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(gst).exists()) {
                    Users users = dataSnapshot.child("Users").child(gst).getValue(Users.class);
                    companyname.setText(users.getCompany_name());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void generateorder()

    {
        final String orderid = Paper.book().read(remember.orderid);
        final DatabaseReference RoofRef;
        RoofRef = FirebaseDatabase.getInstance().getReference();
        RoofRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Order").child(orderid).exists()) {
                    Order orderdata = dataSnapshot.child("Order").child(orderid).getValue(Order.class);
                    if (!(orderdata.getChakki_atta50()==""))
                    {
                        order1.setText("Chakki Atta 50Kg- " + orderdata.getChakki_atta50());
                    }
                    else
                    {
                        order1.setText("Chakki Atta 50Kg-  0" );
                    }
                    if (!(orderdata.getChakki_atta20()==""))
                    {
                        order2.setText("Chakki Atta 20Kg- " + orderdata.getChakki_atta20());
                    }
                    else
                    {
                        order2.setText("Chakki Atta 20Kg-  0");
                    }
                    if (!(orderdata.getMaida50()==""))
                    {
                        order3.setText("Maida 50Kg- " + orderdata.getMaida50());
                    }
                    else
                    {
                        order3.setText("Maida 50Kg-  0");
                    }
                    if (!(orderdata.getMaida20()==""))
                    {
                        order4.setText("Maida 20Kg- " + orderdata.getMaida20());
                    }
                    else
                    {
                        order4.setText("Maida 20Kg-  0");
                    }
                    if (!(orderdata.getSuji50()==""))
                    {
                        order5.setText("Suji 50Kg- " + orderdata.getSuji50());
                    }
                    else
                    {
                        order5.setText("Suji 50Kg-  0");
                    }
                    if (!(orderdata.getSuji20()==""))
                    {
                        order6.setText("Suji 20Kg- " + orderdata.getSuji20());
                    }
                    else
                    {
                        order6.setText("Suji 20Kg-  0");
                    }
                    if (!(orderdata.getChakki_attan50()==""))
                    {
                        order7.setText("Chakki Atta 50Kg- " + orderdata.getChakki_attan50());
                    }
                    else
                    {
                        order7.setText("Chakki Atta 50Kg-  0" );
                    }
                    if (!(orderdata.getChakki_attan20()==""))
                    {
                        order8.setText("Chakki Atta 20Kg- " + orderdata.getChakki_attan20());
                    }
                    else
                    {
                        order8.setText("Chakki Atta 20Kg-  0");
                    }
                    if (!(orderdata.getMaidan50()==""))
                    {
                        order9.setText("Maida 50Kg- " + orderdata.getMaidan50());
                    }
                    else
                    {
                        order9.setText("Maida 50Kg-  0");
                    }
                    if (!(orderdata.getMaidan20()==""))
                    {
                        order10.setText("Maida 20Kg- " + orderdata.getMaidan20());
                    }
                    else
                    {
                        order10.setText("Maida 20Kg-  0");
                    }
                    if (!(orderdata.getSujin50()==""))
                    {
                        order11.setText("Suji 50Kg- " + orderdata.getSujin50());
                    }
                    else
                    {
                        order11.setText("Suji 50Kg-  0");
                    }
                    if (!(orderdata.getSujin20()==""))
                    {
                        order12.setText("Suji 20Kg- " + orderdata.getSujin20());
                    }
                    else
                    {
                        order12.setText("Suji 20Kg-  0");
                    }
                    loadingBar.dismiss();
                }
                else {
                    Toast.makeText(conform.this, "Unable to load the order", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
