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

import com.example.aise_android_v1.adapter.AdapterAvisos;
import com.example.aise_android_v1.tarjetas.Avisos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AvisosActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase, aDatabase;
    ArrayList<Avisos> list;
    RecyclerView rv_Avisos;
    AdapterAvisos adapter;
    ImageButton ib_RegresarAv;
    LinearLayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        aDatabase = FirebaseDatabase.getInstance().getReference();
        ib_RegresarAv = findViewById(R.id.ibRegresarAv);

        VerAvisos();
        Botones();

    }

    private void Botones() {
        ib_RegresarAv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AvisosActivity.super.onBackPressed();
            }
        });
    }

    private void VerAvisos() {

        rv_Avisos = findViewById(R.id.rvAvisos);
        lm = new LinearLayoutManager(this);
        rv_Avisos.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterAvisos(list);
        rv_Avisos.setAdapter(adapter);


        //obtiene el plantel al que corresponde el alumno
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String plantel = dataSnapshot.child("Plantel").getValue().toString();

                //Obtiene los avisos del plantel correspondiente
                aDatabase.child(plantel).child("Avisos").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                Avisos av = snapshot.getValue(Avisos.class);
                                list.add(av);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(AvisosActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();

            }
        });



    }


}
