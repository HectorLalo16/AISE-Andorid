package com.example.aise_android_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoDeCuentaActivity extends AppCompatActivity {

    private ImageButton ib_Regresar;
    private TextView tv_InfoPlantel, tv_InfoNombre, tv_InfoCorreo, tv_InfoMatricula, tv_InfoId,tv_InfoGrupo,tv_InfoAlumno;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_de_cuenta);
        getSupportActionBar().hide();

        tv_InfoPlantel = findViewById(R.id.tvInfoPlantel);
        tv_InfoNombre = findViewById(R.id.tvInfoNombre);
        tv_InfoCorreo = findViewById(R.id.tvInfoCorreo);
        tv_InfoMatricula = findViewById(R.id.tvInfoMatricula);
        tv_InfoId = findViewById(R.id.tvInfoId);
        tv_InfoGrupo = findViewById(R.id.tvInfoGrupo);
        tv_InfoAlumno = findViewById(R.id.tvInfoAlumno);
        ib_Regresar = findViewById(R.id.ibRegresarInfo);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        botones();
        ObtenerDatos();

    }

    private void ObtenerDatos() {

        final String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //se obtiene los datos del alumno y se colocan al textview
                String Plantel = dataSnapshot.child("Plantel").getValue().toString();
                String Nombres  = dataSnapshot.child("Nombres").getValue().toString();
                String Apellidos  = dataSnapshot.child("Apellidos").getValue().toString();
                String Correo = dataSnapshot.child("Correo").getValue().toString();
                String Matricula  = dataSnapshot.child("Matricula").getValue().toString();
                String Grupo = dataSnapshot.child("Grupo").getValue().toString();
                String TipoAlumno = dataSnapshot.child("TipoDeAlumno").getValue().toString();

                tv_InfoPlantel.setText("Plantel: "+Plantel);
                tv_InfoNombre.setText("Nombre: "+Nombres +" "+Apellidos);
                tv_InfoCorreo.setText("Correo: "+Correo);
                tv_InfoMatricula.setText("Matricula: "+Matricula);
                tv_InfoId.setText("Id: "+id);
                tv_InfoGrupo.setText("Grupo: "+Grupo);
                tv_InfoAlumno.setText(TipoAlumno);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(InfoDeCuentaActivity.this, "No se pudo cargar la información", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void botones() {
        ib_Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoDeCuentaActivity.super.onBackPressed();
            }
        });
    }
}
