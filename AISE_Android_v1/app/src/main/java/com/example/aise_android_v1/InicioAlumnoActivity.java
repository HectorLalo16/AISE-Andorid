package com.example.aise_android_v1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InicioAlumnoActivity extends AppCompatActivity {
    Button btn_Grupo, btn_Cali, btn_Avisos, btn_Historial,btn_InfoDeCuenta;
    private TextView tv_IniUbicacion, tv_IniHora, tv_IniNombre, tv_IniMatricula;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_alumno);


        btn_Avisos = findViewById(R.id.btnAvisos);
        btn_Cali = findViewById(R.id.btnCali);
        btn_Grupo = findViewById(R.id.btnGrupo);
        btn_Historial = findViewById(R.id.btnHistorial);
        btn_InfoDeCuenta = findViewById(R.id.btnInfoDeCuenta);
        tv_IniUbicacion = findViewById(R.id.tvIniUbicacion);
        tv_IniHora = findViewById(R.id.tvIniHora);
        tv_IniNombre = findViewById(R.id.tvIniNombre);
        tv_IniMatricula = findViewById(R.id.tvIniMatricula);

        //Procedimiento de firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        Botones();
        VerificarDatos();



    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id==R.id.miCerrarSesion){
            //Una alerta para asegurar que se quiere cerrar sesión
            AlertDialog.Builder alerta = new AlertDialog.Builder(InicioAlumnoActivity.this);
            alerta.setMessage("Cerrando sesión")
                    .setCancelable(false)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mAuth.signOut();
                            startActivity(new Intent(InicioAlumnoActivity.this, LoginActivity.class));
                            finish();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog titulo = alerta.create();
            titulo.setTitle("Cerrar sesión");
            titulo.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void Botones() {

        //Botones para cambiar de activity
        btn_Grupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioAlumnoActivity.this, TareasActivity.class));
            }
        });

        btn_Cali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioAlumnoActivity.this, CaliActivity.class));
            }
        });

        btn_Avisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioAlumnoActivity.this, AvisosActivity.class));
            }
        });

        btn_Historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioAlumnoActivity.this, HistorialActivity.class));
            }
        });

        btn_InfoDeCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioAlumnoActivity.this, InfoDeCuentaActivity.class));
            }
        });

    }

    private void VerificarDatos() {
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //se obtiene los datos del alumno y se colocan al textview
                String Hora = dataSnapshot.child("Estado").child("Hora").getValue().toString();
                String Ubicacion  = dataSnapshot.child("Estado").child("Ubicacion").getValue().toString();
                String Nombres  = dataSnapshot.child("Nombres").getValue().toString();
                String Apellidos  = dataSnapshot.child("Apellidos").getValue().toString();
                String Matricula  = dataSnapshot.child("Matricula").getValue().toString();

                tv_IniUbicacion.setText(Ubicacion);
                tv_IniHora.setText(Hora);
                tv_IniNombre.setText(Apellidos + " " + Nombres);
                tv_IniMatricula.setText(Matricula);

                //cambio de color para el fondo del tvUbicacion
                if(Ubicacion.equals("Dentro del plantel")){
                    tv_IniUbicacion.setBackgroundColor(Color.parseColor("#69F0AE"));
                }else{
                    tv_IniUbicacion.setBackgroundColor(Color.parseColor("#FF5252"));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(InicioAlumnoActivity.this, "No se pudo cargar la información", Toast.LENGTH_SHORT).show();
            }
        });



    }

}
