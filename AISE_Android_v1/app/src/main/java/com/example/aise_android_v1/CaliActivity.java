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

import com.example.aise_android_v1.adapter.AdapterCalificaciones;
import com.example.aise_android_v1.tarjetas.Calificaciones;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CaliActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    ArrayList<Calificaciones> list;
    RecyclerView rv_Cali;
    AdapterCalificaciones adapter;
    LinearLayoutManager lm;
    ImageButton ib_RegresarCa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cali);
        getSupportActionBar().hide();

        ib_RegresarCa = findViewById(R.id.ibRegresarCa);


        String idA = getIntent().getStringExtra("idA");
        if (idA != null) {
                ObtenerValoresFBPadres(idA);
            }else{
                ObtenerValoresFB();
            }

        Botones();

    }

    private void ObtenerValoresFBPadres(String idA) {
        mAuth = FirebaseAuth.getInstance();
        String id = idA;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(id).child("Cali");
        rv_Cali = findViewById(R.id.rvCalificaciones);
        lm = new LinearLayoutManager(this);
        rv_Cali.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterCalificaciones(list);
        rv_Cali.setAdapter(adapter);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Calificaciones cl = snapshot.getValue(Calificaciones.class);
                        list.add(cl);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CaliActivity.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Botones() {
        ib_RegresarCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CaliActivity.super.onBackPressed();
            }
        });
    }

    private void ObtenerValoresFB() {

        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(id).child("Cali");
        rv_Cali = findViewById(R.id.rvCalificaciones);
        lm = new LinearLayoutManager(this);
        rv_Cali.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterCalificaciones(list);
        rv_Cali.setAdapter(adapter);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Calificaciones cl = snapshot.getValue(Calificaciones.class);
                        list.add(cl);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CaliActivity.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            }
        });

    }

}