package com.example.aise_android_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class LoginActivity extends AppCompatActivity {

    Button btnIngresar;
    EditText et_Email, et_Password;
    TextView tv_Cargando;
    private String Email = "";
    private String Password = "";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    String A;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        A = (preferences.getString("estado", ""));


        btnIngresar = findViewById(R.id.btnIniciarSesion);
        et_Password = findViewById(R.id.etPassword);
        et_Email = findViewById(R.id.etEmail);
        tv_Cargando = findViewById(R.id.tvCargando);

        //Procedimientos para Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        iniciarCuenta();

    }

    private void iniciarCuenta() {
        //Iniciar cuenta con Authentication
            btnIngresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnIngresar.setVisibility(View.GONE);
                    tv_Cargando.setVisibility(View.VISIBLE);
                        Email = et_Email.getText().toString();
                        Password = et_Password.getText().toString();

                        if (!Email.isEmpty() && !Password.isEmpty()) {
                            mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        //Determinar que tipo de usuario es
                                        final String id = mAuth.getCurrentUser().getUid();
                                        mDatabase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {

                                                    String TipoDeUsuario = dataSnapshot.child("TipoDeUsuario").getValue().toString();

                                                    if (TipoDeUsuario.equals("Estudiante")) {
                                                        LoginUserA();
                                                    }
                                                    if (TipoDeUsuario.equals("Padre")) {
                                                        LoginUserP();
                                                    }

                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                Toast.makeText(LoginActivity.this, "No se pudo iniciar sesión", Toast.LENGTH_SHORT).show();
                                                btnIngresar.setVisibility(View.VISIBLE);
                                                tv_Cargando.setVisibility(View.GONE);
                                            }
                                        });

                                    } else {
                                        Toast.makeText(LoginActivity.this, "Error, verifique los datos", Toast.LENGTH_SHORT).show();
                                        btnIngresar.setVisibility(View.VISIBLE);
                                        tv_Cargando.setVisibility(View.GONE);
                                    }
                                }
                            });

                        } else {
                            validacion();
                            btnIngresar.setVisibility(View.VISIBLE);
                            tv_Cargando.setVisibility(View.GONE);
                        }
                }

                private void validacion() {

                    if (Email.equals("")) {
                        et_Email.setError("Campo necesario");
                    }
                    if (Password.equals("")) {
                        et_Password.setError("Campo necesario");
                    }


                }
            });
    }

    //iniciar en caso de ser Alumno
    private void LoginUserA () {

        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("estado", "Estudiante");
                    editor.commit();
                    startActivity(new Intent(LoginActivity.this, InicioAlumnoActivity.class));
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "No se pudo Iniciar sesion", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    //iniciar en caso de ser Padre/Tutor
    private void LoginUserP () {
        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("estado", "Padre");
                    editor.commit();

                    startActivity(new Intent(LoginActivity.this, InicioPadreActivity.class));
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "No se pudo Iniciar sesion", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    //Iniciar en el inicio si ya se tiene abierta la cuenta
    @Override
    protected void onStart(){
        super.onStart();
        if (mAuth.getCurrentUser() != null) {

            if (A.equals("Estudiante")) {
                startActivity(new Intent(LoginActivity.this, InicioAlumnoActivity.class));
                finish();
            }
            if (A.equals("Padre")) {
                startActivity(new Intent(LoginActivity.this, InicioPadreActivity.class));
                finish();
            }
        }
    }


}
