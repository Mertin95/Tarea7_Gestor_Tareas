package com.carba.tarea7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdaptadorTareas extends RecyclerView.Adapter<AdaptadorTareas.TareaViewHolder> {


    private final OnTareaClickListener clickListener;
    private final OnTareaLongClickListener longClickListener;
    private final List<Tarea> tareas;

    public AdaptadorTareas(List<Tarea> listaTareas, OnTareaClickListener clickListener, OnTareaLongClickListener longClickListener) {
        this.tareas = listaTareas;
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TareaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        Tarea tarea = tareas.get(position);
        holder.bind(tarea);


        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onTareaClick(tarea, position);
            }
        });


        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onTareaLongClick(tarea, position);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }


    public interface OnTareaClickListener {
        void onTareaClick(Tarea tarea, int position);
    }


    public interface OnTareaLongClickListener {
        void onTareaLongClick(Tarea tarea, int position);
    }

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNombreTarea;
        private final TextView tvDescripcionTarea;
        private final TextView tvFechaEntrega;
        private final TextView tvHoraEntrega;
        private final TextView tvEstadoTarea;

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreTarea = itemView.findViewById(R.id.tv_nombre_tarea);
            tvDescripcionTarea = itemView.findViewById(R.id.tv_descripcion_tarea);
            tvFechaEntrega = itemView.findViewById(R.id.tv_fecha_entrega);
            tvHoraEntrega = itemView.findViewById(R.id.tv_hora_entrega);
            tvEstadoTarea = itemView.findViewById(R.id.tv_estado_tarea);
        }

        public void bind(Tarea tarea) {
            tvNombreTarea.setText(tarea.getNombre());
            tvDescripcionTarea.setText(tarea.getDescripcion());
            tvFechaEntrega.setText(tarea.getFechaEntrega());
            tvHoraEntrega.setText(tarea.getHoraEntrega());
            tvEstadoTarea.setText(tarea.isEstado() ? "Completado" : "Pendiente");
            tvEstadoTarea.setTextColor(tarea.isEstado() ?
                    itemView.getResources().getColor(android.R.color.holo_green_dark) :
                    itemView.getResources().getColor(android.R.color.holo_red_dark));
        }
    }
}
