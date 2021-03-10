package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Main3Activity;
import com.example.myapplication.Model.Order;
import com.example.myapplication.R;
import com.example.myapplication.conform;
import com.example.myapplication.remember.remember;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.paperdb.Paper;

public class order_history_user extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_user);
        listView = (ListView) findViewById(R.id.listview);
        final ArrayList<String> list=new ArrayList<>();
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.company,list);
        listView.setAdapter(adapter);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Order");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    final Order o =snapshot.getValue(Order.class);
                    final String gst = Paper.book().read(remember.gst);
                    String info=o.getDate()+"    "+o.getAdmin_conform();
                    if(o.getGst_number().equals(gst))
                    {
                        list.add(info);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
