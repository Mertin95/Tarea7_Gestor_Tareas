package com.carba.tarea7;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentoDialogo.OnTareaSavedListener {
    private List<Tarea> listaTareas;
    private RecyclerView recyclerView;
    private AdaptadorTareas tareaAdapter;
    private FloatingActionButton btnAgregarTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaTareas = new ArrayList<>();
        tareaAdapter = new AdaptadorTareas(listaTareas, this::showTareaBottomSheet, this::onTareaLongClick);


        recyclerView = findViewById(R.id.listaTareas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(tareaAdapter);


        btnAgregarTarea = findViewById(R.id.agregarTarea);
        btnAgregarTarea.setOnClickListener(v -> showNuevaTareaDialog(null));
    }


    private void showNuevaTareaDialog(Tarea tarea) {
        FragmentoDialogo nuevaTareaDialogoFragment = new FragmentoDialogo();
        if (tarea != null) {

            nuevaTareaDialogoFragment.setTarea(tarea);
        }
        nuevaTareaDialogoFragment.setOnTareaSavedListener(this);
        nuevaTareaDialogoFragment.show(getSupportFragmentManager(), "NuevaTareaDialogo");
    }


    @Override
    public void onTareaSaved(Tarea tarea, boolean isEdit) {
        if (isEdit) {

            int position = listaTareas.indexOf(tarea);
            listaTareas.set(position, tarea);
            tareaAdapter.notifyItemChanged(position);
            Toast.makeText(this, "Tarea actualizada", Toast.LENGTH_SHORT).show();
        } else {

            listaTareas.add(tarea);
            tareaAdapter.notifyItemInserted(listaTareas.size() - 1);
            recyclerView.scrollToPosition(listaTareas.size() - 1);
            Toast.makeText(this, "Tarea guardada", Toast.LENGTH_SHORT).show();
        }
    }


    private void showTareaBottomSheet(Tarea tarea, int position) {
        Editar bottomSheetDialog = new Editar();
        bottomSheetDialog.setTarea(tarea);
        bottomSheetDialog.setOnOptionSelectedListener(new Editar.OnBottomSheetOptionSelectedListener() {
            @Override
            public void onEditSelected(Tarea tarea) {
                showNuevaTareaDialog(tarea);
            }

            @Override
            public void onDeleteSelected(Tarea tarea) {
                showDeleteConfirmationDialog(tarea, position);
            }

            @Override
            public void onCompleteSelected(Tarea tarea) {
                tarea.setEstado(true);
                tareaAdapter.notifyItemChanged(position);
                Toast.makeText(MainActivity.this, "Tarea completada", Toast.LENGTH_SHORT).show();
            }
        });

        bottomSheetDialog.show(getSupportFragmentManager(), "TareaBottomSheet");
    }


    private void showDeleteConfirmationDialog(Tarea tarea, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar tarea")
                .setMessage("¿Estás seguro de que deseas eliminar esta tarea?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    if (position >= 0 && position < listaTareas.size()) {
                        listaTareas.remove(position);
                        tareaAdapter.notifyItemRemoved(position);
                        Toast.makeText(this, "Tarea eliminada", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }


    private void onTareaLongClick(Tarea tarea, int position) {

    }
}
