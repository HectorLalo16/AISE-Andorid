package com.example.aise_android_v1.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aise_android_v1.R;
import com.example.aise_android_v1.tarjetas.Avisos;

import java.util.List;

public class AdapterAvisos extends RecyclerView.Adapter<AdapterAvisos.ViewHolderAvisos> {

    List<Avisos> AvisosList;

    public AdapterAvisos(List<Avisos> avisosList) {
        AvisosList = avisosList;
    }

    @NonNull
    @Override
    public ViewHolderAvisos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_avisos,parent,false);
        ViewHolderAvisos holder = new ViewHolderAvisos(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAvisos holder, int position) {

        Avisos av = AvisosList.get(position);

        holder.tv_Texto.setText(av.getTexto());
        holder.tv_Fecha.setText(av.getFecha());

    }

    @Override
    public int getItemCount() {
        return AvisosList.size();
    }

    public class ViewHolderAvisos extends RecyclerView.ViewHolder {

        TextView tv_Texto,tv_Fecha;

        public ViewHolderAvisos(@NonNull View itemView) {
            super(itemView);

            tv_Texto = itemView.findViewById(R.id.tvTexto);
            tv_Fecha = itemView.findViewById(R.id.tvFecha);
        }
    }
}
