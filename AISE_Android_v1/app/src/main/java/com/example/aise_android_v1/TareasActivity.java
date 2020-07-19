package com.example.aise_android_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aise_android_v1.adapter.AdapterTareas;
import com.example.aise_android_v1.tarjetas.Tareas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TareasActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase, pDatabase;
    ArrayList<Tareas> list;
    RecyclerView rv_Tareas;
    AdapterTareas adapter;
    LinearLayoutManager lm;
    private String grupo = "";
    private String plantel = "";
    Button btn_AgregarTarea;
    String TipoDeAlumno = "";
    ImageButton ib_RegresarTa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);
        getSupportActionBar().hide();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        pDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        btn_AgregarTarea = findViewById(R.id.btnAgregarTareas);
        ib_RegresarTa = findViewById(R.id.ibRegresarTa);

        VerificarTipoDeAlumno();
        ObtenerValoresFB();
        BotonRegresar();

    }

    private void BotonRegresar() {
        ib_RegresarTa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               TareasActivity.super.onBackPressed();
            }
        });
    }

    private void ObtenerValoresFB() {

        //Procesos para RecyclerView
        rv_Tareas = findViewById(R.id.rvTareas);
        lm = new LinearLayoutManager(this);
        rv_Tareas.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterTareas(list);
        rv_Tareas.setAdapter(adapter);

        //se obtiene el plantel y el grupo para despues completar la direccion y encotrar las tareas pendiendes para agrenarlas al recyclerview
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String g = dataSnapshot.child("Grupo").getValue().toString();
                String p = dataSnapshot.child("Plantel").getValue().toString();
                grupo = g;
                plantel = p;

                //genera las cardview en el recyclerview con datos de firebase
                pDatabase = FirebaseDatabase.getInstance().getReference().child(plantel).child("Grupos").child(grupo).child("Tareas");
                pDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                Tareas tr = snapshot.getValue(Tareas.class);
                                list.add(tr);

                                //Si es encargado tambien puede eliminar las tareas de la lista
                                if(TipoDeAlumno.equals("Encargado")){
                                    adapter.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            final String SeleccionBorrar = list.get(rv_Tareas.getChildAdapterPosition(v)).getId();
                                            //Una alerta para asegurar que se quiere eliminar
                                            AlertDialog.Builder alerta = new AlertDialog.Builder(TareasActivity.this);
                                            alerta.setMessage("¿Desea eliminar la tarea pendiente?")
                                                    .setCancelable(false)
                                                    .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            mDatabase.child(plantel).child("Grupos").child(grupo).child("Tareas").child(SeleccionBorrar).removeValue();
                                                            Toast.makeText(TareasActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                                                            Refrescar();
                                                        }
                                                    })
                                                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                            AlertDialog titulo = alerta.create();
                                            titulo.setTitle("Borrar tarea");
                                            titulo.show();

                                        }
                                    });
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(TareasActivity.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TareasActivity.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void Refrescar() {
        finish();
        overridePendingTransition(0,0);
        startActivity(new Intent(this, TareasActivity.class));
        overridePendingTransition(0,0);
    }

    private void VerificarTipoDeAlumno() {

        //verifica si es encargado y tiene acceso a agregar tareas
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TipoDeAlumno = dataSnapshot.child("TipoDeAlumno").getValue().toString();

                if(TipoDeAlumno.equals("Encargado")){
                    btn_AgregarTarea.setVisibility(View.VISIBLE);
                    btn_AgregarTarea.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                            startActivity(new Intent(TareasActivity.this,AgregarTareasActivity.class));
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TareasActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
            }
        });


    }

}