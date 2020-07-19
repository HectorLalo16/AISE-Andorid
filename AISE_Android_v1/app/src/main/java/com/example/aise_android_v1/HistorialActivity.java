package com.example.aise_android_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aise_android_v1.adapter.AdapterHistorial;
import com.example.aise_android_v1.tarjetas.Avisos;
import com.example.aise_android_v1.tarjetas.Historial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistorialActivity extends AppCompatActivity {

    ImageButton ib_RegresarHi;
    ArrayList<Historial> list;
    RecyclerView rv_Historial;
    AdapterHistorial adapter;
    LinearLayoutManager lm;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        getSupportActionBar().hide();

        ib_RegresarHi = findViewById(R.id.ibRegresarHi);

        String idA = getIntent().getStringExtra("idA");
        if (idA != null) {
            ObtenerHistorialFBPadres(idA);
        }else{
            ObtenerHistorial();
        }

        Botones();

    }

    private void ObtenerHistorialFBPadres(String idA) {

        mAuth = FirebaseAuth.getInstance();
        String id = idA;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(id).child("Historial");
        rv_Historial = findViewById(R.id.rvHistorial);
        lm = new LinearLayoutManager(this);
        rv_Historial.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterHistorial(list);
        rv_Historial.setAdapter(adapter);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Historial ht = snapshot.getValue(Historial.class);
                        list.add(ht);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HistorialActivity.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void Botones() {

        ib_RegresarHi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               HistorialActivity.super.onBackPressed();
            }
        });

    }

    private void ObtenerHistorial() {
        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(id).child("Historial");
        rv_Historial = findViewById(R.id.rvHistorial);
        lm = new LinearLayoutManager(this);
        rv_Historial.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterHistorial(list);
        rv_Historial.setAdapter(adapter);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Historial ht = snapshot.getValue(Historial.class);
                        list.add(ht);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HistorialActivity.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            }
        });

    }

}

