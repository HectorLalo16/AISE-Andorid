package com.example.aise_android_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aise_android_v1.Modelos.AddTareas;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class AgregarTareasActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private EditText et_Materia, et_Tarea, et_Valor, et_FechaEntrega;
    private Button btn_Agregar,btn_Fecha;
    ImageButton ib_RegresarAg;
    private int dia,mes,año;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tareas);
        getSupportActionBar().hide();

        et_Materia = findViewById(R.id.etMateria);
        et_Tarea = findViewById(R.id.etTarea);
        et_Valor = findViewById(R.id.etValor);
        et_FechaEntrega = findViewById(R.id.etFechaEntrega);
        btn_Agregar = findViewById(R.id.btnAgregar);
        btn_Fecha = findViewById(R.id.btnFecha);
        btn_Fecha.setOnClickListener(this);
        ib_RegresarAg = findViewById(R.id.ibRegresarAg);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        botones();

    }

    private void botones() {
        btn_Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubirTarea();
                finish();
                startActivity(new Intent(AgregarTareasActivity.this,TareasActivity.class));

            }
        });
        ib_RegresarAg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(AgregarTareasActivity.this,TareasActivity.class));
            }
        });
    }

    private void SubirTarea() {
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String g = dataSnapshot.child("Grupo").getValue().toString();
                String p = dataSnapshot.child("Plantel").getValue().toString();

                String vid = UUID.randomUUID().toString();
                String vMateria = et_Materia.getText().toString().trim();
                String vTarea = et_Tarea.getText().toString().trim();
                String vValor = et_Valor.getText().toString().trim();
                String vFechaEntrega = et_FechaEntrega.getText().toString().trim();
                String vFechaRegistrada = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

                AddTareas at = new AddTareas();
                at.setId(vid);
                at.setMateria(vMateria);
                at.setValor(vValor);
                at.setTarea(vTarea);
                at.setFechaDeEntrega(vFechaEntrega);
                at.setFechaRegistrada(vFechaRegistrada);
                mDatabase.child(p).child("Grupos").child(g).child("Tareas").child(vid).setValue(at);
                Toast.makeText(AgregarTareasActivity.this, "Hecho", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AgregarTareasActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Obtener Fecha
    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        año=c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String Fecha = dayOfMonth+"/"+(month+1)+"/"+year;
                et_FechaEntrega.setText(String.valueOf(Fecha));
            }
        } ,año,mes,dia);
        datePickerDialog.show();
    }
}
