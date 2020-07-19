package com.example.aise_android_v1.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aise_android_v1.R;
import com.example.aise_android_v1.tarjetas.Historial;

import java.util.List;

public class AdapterHistorial extends RecyclerView.Adapter<AdapterHistorial.ViewHolderHistorial> {

    List<Historial> HistorialList;

    public AdapterHistorial (List<Historial> historialList){
        HistorialList = historialList;
    }

    @NonNull
    @Override
    public ViewHolderHistorial onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View h = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_historial,parent,false);
        ViewHolderHistorial holder = new ViewHolderHistorial(h);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHistorial holder, int position) {

        Historial ht = HistorialList.get(position);

        holder.tv_Fecha.setText(ht.getFecha());
        holder.tv_Hora.setText(ht.getHora());
        holder.tv_Ubicacion.setText(ht.getUbicacion());

    }

    @Override
    public int getItemCount() {
        return HistorialList.size();
    }

    public class ViewHolderHistorial extends RecyclerView.ViewHolder {

        TextView tv_Ubicacion, tv_Hora, tv_Fecha;

        public ViewHolderHistorial(@NonNull View itemView) {
            super(itemView);
            tv_Fecha = itemView.findViewById(R.id.tvHFecha);
            tv_Hora = itemView.findViewById(R.id.tvHHora);
            tv_Ubicacion = itemView.findViewById(R.id.tvHUbicacion);
        }
    }
}
