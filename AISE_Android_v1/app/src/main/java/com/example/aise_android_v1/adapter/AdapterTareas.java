package com.example.aise_android_v1.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aise_android_v1.R;
import com.example.aise_android_v1.tarjetas.Tareas;

import java.util.List;

public class AdapterTareas extends RecyclerView.Adapter<AdapterTareas.ViewHolderTareas>
                implements View.OnClickListener {

    List<Tareas> TareasList;
    private View.OnClickListener listener;

    public AdapterTareas (List<Tareas> tareasList){TareasList = tareasList;}

    @NonNull
    @Override
    public ViewHolderTareas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tareas,parent,false);
        v.setOnClickListener(this);
        ViewHolderTareas holder = new ViewHolderTareas(v);



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTareas holder, int position) {

        Tareas tr = TareasList.get(position);

        holder.tv_Tarea.setText(tr.getTarea());
        holder.tv_Materia.setText(tr.getMateria());
        holder.tv_Valor.setText(tr.getValor());
        holder.tv_FechaRe.setText(tr.getFechaRegistrada());
        holder.tv_FechaEn.setText(tr.getFechaDeEntrega());

    }

    @Override
    public int getItemCount() {
        return TareasList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {

        if(listener != null){
            listener.onClick(v);
        }

    }

    public class ViewHolderTareas extends RecyclerView.ViewHolder {

        TextView tv_Tarea, tv_Materia, tv_Valor, tv_FechaRe, tv_FechaEn;

        public ViewHolderTareas(@NonNull View itemView) {
            super(itemView);

            tv_Tarea = itemView.findViewById(R.id.tvTarea);
            tv_Materia = itemView.findViewById(R.id.tvMateria);
            tv_Valor = itemView.findViewById(R.id.tvValor);
            tv_FechaRe = itemView.findViewById(R.id.tvFechaRe);
            tv_FechaEn = itemView.findViewById(R.id.tvFechaEn);


        }
    }
}
