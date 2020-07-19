package com.example.aise_android_v1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aise_android_v1.R;
import com.example.aise_android_v1.tarjetas.Calificaciones;

import java.util.List;

public class AdapterCalificaciones extends RecyclerView.Adapter<AdapterCalificaciones.ViewHolderCali>{

    List<Calificaciones> CaliList;

    public AdapterCalificaciones(List<Calificaciones> caliList) {
        CaliList = caliList;
    }

    @NonNull
    @Override
    public ViewHolderCali onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_calificaciones,parent,false);
        ViewHolderCali holder = new ViewHolderCali(v);



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCali holder, int position) {

        Calificaciones av = CaliList.get(position);

        holder.tv_Periodo.setText(av.getPeriodo());
        holder.tv_Materia.setText(av.getMateria());
        holder.tv_Resultado.setText(av.getResultado());
        holder.tv_Fecha.setText(av.getFecha());

    }

    @Override
    public int getItemCount() {
        return CaliList.size();
    }

    public class ViewHolderCali extends RecyclerView.ViewHolder {

        TextView tv_Periodo, tv_Materia, tv_Resultado, tv_Fecha;

        public ViewHolderCali(@NonNull View itemView) {
            super(itemView);

            tv_Periodo = itemView.findViewById(R.id.tvPeriodo);
            tv_Materia = itemView.findViewById(R.id.tvMateria);
            tv_Resultado = itemView.findViewById(R.id.tvResultado);
            tv_Fecha = itemView.findViewById(R.id.tvCFecha);

        }
    }
}
