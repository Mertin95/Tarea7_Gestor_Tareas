package com.carba.tarea7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Editar extends BottomSheetDialogFragment {

    private OnBottomSheetOptionSelectedListener listener;
    private Tarea tarea;

    public interface OnBottomSheetOptionSelectedListener {
        void onEditSelected(Tarea tarea);
        void onDeleteSelected(Tarea tarea);
        void onCompleteSelected(Tarea tarea);
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public void setOnOptionSelectedListener(OnBottomSheetOptionSelectedListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editartareas, container, false);


        LinearLayout opcionEditar = view.findViewById(R.id.opcion_editar);
        LinearLayout opcionEliminar = view.findViewById(R.id.opcion_eliminar);
        LinearLayout opcionCompletar = view.findViewById(R.id.opcion_completar);


        opcionEditar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditSelected(tarea);
            }
            dismiss();
        });

        opcionEliminar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteSelected(tarea);
            }
            dismiss();
        });

        opcionCompletar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCompleteSelected(tarea);
            }
            dismiss();
        });

        return view;
    }
}
