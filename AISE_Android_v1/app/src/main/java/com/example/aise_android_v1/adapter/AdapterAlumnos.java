package com.example.aise_android_v1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aise_android_v1.R;
import com.example.aise_android_v1.tarjetas.Alumno;

import java.util.List;

public class AdapterAlumnos extends RecyclerView.Adapter<AdapterAlumnos.ViewHolderAlumnos> {

    List<Alumno> AlumnoList;

    public AdapterAlumnos(List<Alumno> alumnoList) {
        AlumnoList = alumnoList;
    }

    @NonNull
    @Override
    public ViewHolderAlumnos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_alumnos,parent,false);
        ViewHolderAlumnos holder = new ViewHolderAlumnos(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAlumnos holder, int position) {

        Alumno av = AlumnoList.get(position);
        String n = av.getNombre();
        String a = av.getApellidos();
        String nombre = n + " " + a;

        holder.tv_Nombre.setText(nombre);
        holder.tv_Ubicacion.setText(av.getUbicacion());
        holder.tv_Hora.setText(av.getHora());
        holder.tv_Matricula.setText(av.getMatricula());

    }

    @Override
    public int getItemCount() {
        return AlumnoList.size();
    }

    public class ViewHolderAlumnos extends RecyclerView.ViewHolder {
        TextView tv_Nombre,tv_Apellidos,tv_Hora,tv_Ubicacion,tv_Matricula;
        public ViewHolderAlumnos(@NonNull View itemView) {
            super(itemView);
            tv_Nombre = itemView.findViewById(R.id.tvRowNombre);
            tv_Ubicacion = itemView.findViewById(R.id.tvRowUbicacion);
            tv_Hora = itemView.findViewById(R.id.tvRowHora);
            tv_Matricula = itemView.findViewById(R.id.tvRowMatricula);
        }
    }
}
