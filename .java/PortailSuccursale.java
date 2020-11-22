package com.example.novigrad30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PortailSuccursale extends AppCompatActivity {

    private ListView ServiceListSucc;
    private TextView HeureTravail;
    private Button AjoutService;
    private Button HeureSelect;

    DatabaseReference ServiceDB;
    static List<ServicesHelperClass> ServiceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portail_succursale);

        ServiceList = new ArrayList<>();
        ServiceDB = FirebaseDatabase.getInstance().getReference("SERVICES");

        ServiceListSucc = (ListView)findViewById(R.id.ServiceListSucc);
        HeureTravail = (TextView) findViewById(R.id.HeureDeTravail);
        AjoutService = (Button) findViewById(R.id.ServiceSelect);
        HeureSelect = (Button) findViewById(R.id.HeureDeTravailSelect);


        AjoutService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddServiceSuccursale.class);
                startActivity(intent);
            }
        });

        HeureSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HeureDeTravail.class);
                startActivity(intent);
            }
        });
    }

    protected void onStart() {
        super.onStart();

        ServiceDB.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot serviceSnap : snapshot.getChildren()){
                    ServicesHelperClass service = serviceSnap.getValue(ServicesHelperClass.class);
                    Boolean verify = false;

                    for (short i = 0; i < ServiceList.size(); i++){
                        if (service.getNom() == ServiceList.get(i).getNom())
                            verify = true;
                    }
                    if (!verify)
                        ServiceList.add( service );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );

    }
}