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

public class InicioPadreActivity extends AppCompatActivity {

    private TextView tv_Ubicacion1, tv_Hora1,tv_Nombre1,tv_matricula1,tv_Ubicacion2, tv_Hora2,tv_Nombre2,tv_matricula2,tv_Ubicacion3, tv_Hora3,tv_Nombre3,tv_matricula3,tv_Ubicacion4, tv_Hora4,tv_Nombre4,tv_matricula4,tv_Ubicacion5, tv_Hora5,tv_Nombre5,tv_matricula5;
    private Button btn_AvisosP,btn_CalificacionesP1,btn_HistorialP1,btn_CalificacionesP2,btn_HistorialP2,btn_CalificacionesP3,btn_HistorialP3,btn_CalificacionesP4,btn_HistorialP4,btn_CalificacionesP5,btn_HistorialP5;
    private CardView cv_InfoUsuario1,cv_InfoUsuario2,cv_InfoUsuario3,cv_InfoUsuario4,cv_InfoUsuario5;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_padre);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        tv_Ubicacion1= findViewById(R.id.tvUbicacion1);
        tv_Hora1= findViewById(R.id.tvHora1);
        tv_Nombre1= findViewById(R.id.tvNombre1);
        tv_matricula1= findViewById(R.id.tvMatricula1);
        tv_Ubicacion2= findViewById(R.id.tvUbicacion2);
        tv_Hora2= findViewById(R.id.tvHora2);
        tv_Nombre2= findViewById(R.id.tvNombre2);
        tv_matricula2= findViewById(R.id.tvMatricula2);
        tv_Ubicacion3= findViewById(R.id.tvUbicacion3);
        tv_Hora3= findViewById(R.id.tvHora3);
        tv_Nombre3= findViewById(R.id.tvNombre3);
        tv_matricula3= findViewById(R.id.tvMatricula3);
        tv_Ubicacion4= findViewById(R.id.tvUbicacion4);
        tv_Nombre4= findViewById(R.id.tvNombre4);
        tv_Hora4= findViewById(R.id.tvHora4);
        tv_matricula4= findViewById(R.id.tvMatricula4);
        tv_Ubicacion5= findViewById(R.id.tvUbicacion5);
        tv_Nombre5= findViewById(R.id.tvNombre5);
        tv_Hora5= findViewById(R.id.tvHora5);
        tv_matricula5= findViewById(R.id.tvMatricula5);
        cv_InfoUsuario1 = findViewById(R.id.cvInfoUsuario1);
        cv_InfoUsuario2 = findViewById(R.id.cvInfoUsuario2);
        cv_InfoUsuario3 = findViewById(R.id.cvInfoUsuario3);
        cv_InfoUsuario4 = findViewById(R.id.cvInfoUsuario4);
        cv_InfoUsuario5 = findViewById(R.id.cvInfoUsuario5);
        btn_CalificacionesP1 = findViewById(R.id.btnCaliP1);
        btn_HistorialP1 = findViewById(R.id.btnHistorialP1);
        btn_CalificacionesP2 = findViewById(R.id.btnCaliP2);
        btn_HistorialP2 = findViewById(R.id.btnHistorialP2);
        btn_CalificacionesP3 = findViewById(R.id.btnCaliP3);
        btn_HistorialP3 = findViewById(R.id.btnHistorialP3);
        btn_CalificacionesP4 = findViewById(R.id.btnCaliP4);
        btn_HistorialP4 = findViewById(R.id.btnHistorialP4);
        btn_CalificacionesP5 = findViewById(R.id.btnCaliP5);
        btn_HistorialP5 = findViewById(R.id.btnHistorialP5);
        btn_AvisosP = findViewById(R.id.btnAvisosP);

        VerificarDatos();
        botones();
    }

    private void botones() {
        btn_AvisosP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioPadreActivity.this, AvisosActivity.class));
            }
        });
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id==R.id.miCerrarSesion){
            //Una alerta para asegurar que se quiere cerrar sesión
            AlertDialog.Builder alerta = new AlertDialog.Builder(InicioPadreActivity.this);
            alerta.setMessage("Cerrando sesión")
                    .setCancelable(false)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mAuth.signOut();
                            startActivity(new Intent(InicioPadreActivity.this, LoginActivity.class));
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

    private void VerificarDatos() {
        final String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //se obtiene los datos del alumno y se colocan al textview
                final String A1 = dataSnapshot.child("Alumnos").child("A1").getValue().toString();
                final String A2 = dataSnapshot.child("Alumnos").child("A2").getValue().toString();
                final String A3 = dataSnapshot.child("Alumnos").child("A3").getValue().toString();
                final String A4 = dataSnapshot.child("Alumnos").child("A4").getValue().toString();
                final String A5 = dataSnapshot.child("Alumnos").child("A5").getValue().toString();

               if(!A1.equals("vacio")){
                   mDatabase.child("Usuarios").child(A1).addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           //se obtiene los datos del alumno y se colocan al textview
                           String Hora = dataSnapshot.child("Estado").child("Hora").getValue().toString();
                           String Ubicacion  = dataSnapshot.child("Estado").child("Ubicacion").getValue().toString();
                           String Nombres  = dataSnapshot.child("Nombres").getValue().toString();
                           String Apellidos  = dataSnapshot.child("Apellidos").getValue().toString();
                           String Matricula  = dataSnapshot.child("Matricula").getValue().toString();

                           tv_Ubicacion1.setText(Ubicacion);
                           tv_Hora1.setText(Hora);
                           tv_Nombre1.setText(Apellidos + " " + Nombres);
                           tv_matricula1.setText(Matricula);

                           //cambio de color para el fondo del tvUbicacion
                           if(Ubicacion.equals("Dentro del plantel")){
                               tv_Ubicacion1.setBackgroundColor(Color.parseColor("#69F0AE"));
                           }else{
                               tv_Ubicacion1.setBackgroundColor(Color.parseColor("#FF5252"));
                           }

                           btn_CalificacionesP1.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   Intent intent = new Intent(InicioPadreActivity.this, CaliActivity.class);
                                   intent.putExtra("idA", A1);
                                   startActivity(intent);
                               }
                           });
                           btn_HistorialP1.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   Intent intent = new Intent(InicioPadreActivity.this, HistorialActivity.class);
                                   intent.putExtra("idA", A1);
                                   startActivity(intent);
                               }
                           });

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {
                           Toast.makeText(InicioPadreActivity.this, "No se pudo cargar la información", Toast.LENGTH_SHORT).show();
                       }
                   });
               }

               if(!A2.equals("vacio")){
                   cv_InfoUsuario2.setVisibility(View.VISIBLE);
                   mDatabase.child("Usuarios").child(A2).addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           //se obtiene los datos del alumno y se colocan al textview
                           String Hora = dataSnapshot.child("Estado").child("Hora").getValue().toString();
                           String Ubicacion  = dataSnapshot.child("Estado").child("Ubicacion").getValue().toString();
                           String Nombres  = dataSnapshot.child("Nombres").getValue().toString();
                           String Apellidos  = dataSnapshot.child("Apellidos").getValue().toString();
                           String Matricula  = dataSnapshot.child("Matricula").getValue().toString();

                           tv_Ubicacion2.setText(Ubicacion);
                           tv_Hora2.setText(Hora);
                           tv_Nombre2.setText(Apellidos + " " + Nombres);
                           tv_matricula2.setText(Matricula);

                           //cambio de color para el fondo del tvUbicacion
                           if(Ubicacion.equals("Dentro del plantel")){
                               tv_Ubicacion2.setBackgroundColor(Color.parseColor("#69F0AE"));
                           }else{
                               tv_Ubicacion2.setBackgroundColor(Color.parseColor("#FF5252"));
                           }

                           btn_CalificacionesP2.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   Intent intent = new Intent(InicioPadreActivity.this, CaliActivity.class);
                                   intent.putExtra("idA", A2);
                                   startActivity(intent);
                               }
                           });
                           btn_HistorialP2.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   Intent intent = new Intent(InicioPadreActivity.this, HistorialActivity.class);
                                   intent.putExtra("idA", A2);
                                   startActivity(intent);
                               }
                           });

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {
                           Toast.makeText(InicioPadreActivity.this, "No se pudo cargar la información", Toast.LENGTH_SHORT).show();
                       }
                   });
               }

               if(!A3.equals("vacio")){
                   cv_InfoUsuario3.setVisibility(View.VISIBLE);
                   mDatabase.child("Usuarios").child(A3).addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           //se obtiene los datos del alumno y se colocan al textview
                           String Hora = dataSnapshot.child("Estado").child("Hora").getValue().toString();
                           String Ubicacion  = dataSnapshot.child("Estado").child("Ubicacion").getValue().toString();
                           String Nombres  = dataSnapshot.child("Nombres").getValue().toString();
                           String Apellidos  = dataSnapshot.child("Apellidos").getValue().toString();
                           String Matricula  = dataSnapshot.child("Matricula").getValue().toString();

                           tv_Ubicacion3.setText(Ubicacion);
                           tv_Hora3.setText(Hora);
                           tv_Nombre3.setText(Apellidos + " " + Nombres);
                           tv_matricula3.setText(Matricula);

                           //cambio de color para el fondo del tvUbicacion
                           if(Ubicacion.equals("Dentro del plantel")){
                               tv_Ubicacion3.setBackgroundColor(Color.parseColor("#69F0AE"));
                           }else{
                               tv_Ubicacion3.setBackgroundColor(Color.parseColor("#FF5252"));
                           }

                           btn_CalificacionesP3.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   Intent intent = new Intent(InicioPadreActivity.this, CaliActivity.class);
                                   intent.putExtra("idA", A3);
                                   startActivity(intent);
                               }
                           });
                           btn_HistorialP3.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   Intent intent = new Intent(InicioPadreActivity.this,  HistorialActivity.class);
                                   intent.putExtra("idA", A3);
                                   startActivity(intent);
                               }
                           });

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {
                           Toast.makeText(InicioPadreActivity.this, "No se pudo cargar la información", Toast.LENGTH_SHORT).show();
                       }
                   });
               }

               if(!A4.equals("vacio")){
                   cv_InfoUsuario4.setVisibility(View.VISIBLE);
                   mDatabase.child("Usuarios").child(A4).addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           //se obtiene los datos del alumno y se colocan al textview
                           String Hora = dataSnapshot.child("Estado").child("Hora").getValue().toString();
                           String Ubicacion  = dataSnapshot.child("Estado").child("Ubicacion").getValue().toString();
                           String Nombres  = dataSnapshot.child("Nombres").getValue().toString();
                           String Apellidos  = dataSnapshot.child("Apellidos").getValue().toString();
                           String Matricula  = dataSnapshot.child("Matricula").getValue().toString();

                           tv_Ubicacion4.setText(Ubicacion);
                           tv_Hora4.setText(Hora);
                           tv_Nombre4.setText(Apellidos + " " + Nombres);
                           tv_matricula4.setText(Matricula);

                           //cambio de color para el fondo del tvUbicacion
                           if(Ubicacion.equals("Dentro del plantel")){
                               tv_Ubicacion4.setBackgroundColor(Color.parseColor("#69F0AE"));
                           }else{
                               tv_Ubicacion4.setBackgroundColor(Color.parseColor("#FF5252"));
                           }

                           btn_CalificacionesP4.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   Intent intent = new Intent(InicioPadreActivity.this, CaliActivity.class);
                                   intent.putExtra("idA", A4);
                                   startActivity(intent);
                               }
                           });
                           btn_HistorialP4.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   Intent intent = new Intent(InicioPadreActivity.this, HistorialActivity.class);
                                   intent.putExtra("idA", A4);
                                   startActivity(intent);
                               }
                           });

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {
                           Toast.makeText(InicioPadreActivity.this, "No se pudo cargar la información", Toast.LENGTH_SHORT).show();
                       }
                   });
               }

               if(!A5.equals("vacio")){
                   cv_InfoUsuario5.setVisibility(View.VISIBLE);
                   mDatabase.child("Usuarios").child(A5).addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           //se obtiene los datos del alumno y se colocan al textview
                           String Hora = dataSnapshot.child("Estado").child("Hora").getValue().toString();
                           String Ubicacion  = dataSnapshot.child("Estado").child("Ubicacion").getValue().toString();
                           String Nombres  = dataSnapshot.child("Nombres").getValue().toString();
                           String Apellidos  = dataSnapshot.child("Apellidos").getValue().toString();
                           String Matricula  = dataSnapshot.child("Matricula").getValue().toString();

                           tv_Ubicacion5.setText(Ubicacion);
                           tv_Hora5.setText(Hora);
                           tv_Nombre5.setText(Apellidos + " " + Nombres);
                           tv_matricula5.setText(Matricula);

                           //cambio de color para el fondo del tvUbicacion
                           if(Ubicacion.equals("Dentro del plantel")){
                               tv_Ubicacion5.setBackgroundColor(Color.parseColor("#69F0AE"));
                           }else{
                               tv_Ubicacion5.setBackgroundColor(Color.parseColor("#FF5252"));
                           }

                           btn_CalificacionesP5.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   Intent intent = new Intent(InicioPadreActivity.this, CaliActivity.class);
                                   intent.putExtra("idA", A5);
                                   startActivity(intent);
                               }
                           });
                           btn_HistorialP5.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   Intent intent = new Intent(InicioPadreActivity.this, HistorialActivity.class);
                                   intent.putExtra("idA", A5);
                                   startActivity(intent);
                               }
                           });

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {
                           Toast.makeText(InicioPadreActivity.this, "No se pudo cargar la información", Toast.LENGTH_SHORT).show();
                       }
                   });
               }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(InicioPadreActivity.this, "No se pudo cargar la información", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
