package com.carba.tarea7;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class FragmentoDialogo extends DialogFragment {
    private Spinner spinner;
    private EditText editTextNombreTarea;
    private EditText editTextDescripcion;
    private EditText editTextDate;
    private EditText editTextTime;
    private Button btnCancelar;
    private Button btnGuardar;
    private OnTareaSavedListener listener;
    private Tarea tarea;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.agregar, null);


        spinner = view.findViewById(R.id.asignaturas);
        editTextNombreTarea = view.findViewById(R.id.nombreTarea);
        editTextDescripcion = view.findViewById(R.id.descripcionTarea);
        editTextDate = view.findViewById(R.id.fechaTarea);
        editTextTime = view.findViewById(R.id.horaTarea);
        btnCancelar = view.findViewById(R.id.btnCancelar);
        btnGuardar = view.findViewById(R.id.btnGuardar);


        if (tarea != null) {
            editTextNombreTarea.setText(tarea.getNombre());
            editTextDescripcion.setText(tarea.getDescripcion());
            editTextDate.setText(tarea.getFechaEntrega());
            editTextTime.setText(tarea.getHoraEntrega());

        }


        editTextDate.setOnClickListener(v -> showDatePickerDialog());


        editTextTime.setOnClickListener(v -> showTimePickerDialog());


        builder.setView(view)
                .setCancelable(true);


        btnCancelar.setOnClickListener(v -> dismiss());


        btnGuardar.setOnClickListener(v -> saveTarea());

        return builder.create();
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(
                getContext(),
                (view, year, month, dayOfMonth) -> {
                    String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                    editTextDate.setText(date);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(getContext(), (view, hourOfDay, minute) -> {
            String time = String.format("%02d:%02d", hourOfDay, minute);
            editTextTime.setText(time);
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }

    public void saveTarea() {
        String nombre = editTextNombreTarea.getText().toString();
        String descripcion = editTextDescripcion.getText().toString();
        String fecha = editTextDate.getText().toString();
        String hora = editTextTime.getText().toString();
        String asignatura = spinner.getSelectedItem().toString();

        if (nombre.isEmpty() || descripcion.isEmpty() || fecha.isEmpty() || hora.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (tarea == null) {

            tarea = new Tarea(nombre, descripcion, fecha, hora, asignatura, false);
            if (listener != null) {
                listener.onTareaSaved(tarea, false);
            }
        } else {

            tarea.setNombre(nombre);
            tarea.setDescripcion(descripcion);
            tarea.setFechaEntrega(fecha);
            tarea.setHoraEntrega(hora);
            tarea.setNombreAsignatura(asignatura);
            if (listener != null) {
                listener.onTareaSaved(tarea, true);
            }
        }


        editTextNombreTarea.setText("");
        editTextDescripcion.setText("");
        editTextDate.setText("");
        editTextTime.setText("");
        spinner.setSelection(0);

        dismiss();
    }

    public void setOnTareaSavedListener(OnTareaSavedListener listener) {
        this.listener = listener;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public interface OnTareaSavedListener {
        void onTareaSaved(Tarea tarea, boolean isEdit);
    }
}
