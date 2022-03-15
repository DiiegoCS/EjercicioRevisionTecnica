package com.diegocampos.ejerciciorevisiontecnica;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.diegocampos.ejerciciorevisiontecnica.databinding.FragmentFragmento1Binding;
import com.diegocampos.ejerciciorevisiontecnica.databinding.FragmentFragmento2Binding;


public class Fragmento2 extends Fragment {

    private FragmentFragmento2Binding x2;

    String documentacion2,alin2, direcc2, freno2, llanta2, suspen2, kitSeg2, cinturon2, luz2, puerta2,
            vidrio2, tubEsc2, gas2;// verImagen1, imagen2, imagen3, imagen4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        x2 = FragmentFragmento2Binding.inflate(inflater, container, false);
        View v = x2.getRoot();

        x2.btnBuscarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar();
            }
        });


        return v;
    }

    public void buscar(){
        ConexionBD conexion = new ConexionBD(getContext(), "administracion", null, 1);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        String codigo = x2.txtBuscarRegistro.getText().toString();

        Cursor fila = bd.rawQuery("SELECT * FROM registros WHERE codigoIngreso='"+codigo+"'",null);

        if(fila.moveToFirst()){
            x2.txtMostrarCodigo.setText("Codigo de Ingreso: "+ fila.getString(0));
            x2.txtMostrarFecha.setText("Fecha Ingreso: "+ fila.getString(1));
            x2.txtMostrarHora.setText("Hora Ingreso: "+fila.getString(2));
            x2.txtMostrarPatente.setText("Patente del Vehiculo:"+fila.getString(3));
            documentacion2 = fila.getString(4);
            if(documentacion2.equals("aprobado")){
                x2.btnDocuSi2.setChecked(true);
            }
            else if(documentacion2.equals("rechazado")){
                x2.btnDocuNo2.setChecked(true);
            }
            alin2 = fila.getString(5);
            if(alin2.equals("aprobado")){
                x2.btnVerAlineacion1.setChecked(true);
            }
            else if(alin2.equals("rechazado")){
                x2.btnVerAlineacion2.setChecked(true);
            }
            direcc2 = fila.getString(6);
            if(direcc2.equals("aprobado")){
                x2.btnVerDireccion1.setChecked(true);
            }
            else if(direcc2.equals("rechazado")){
                x2.btnVerDireccion2.setChecked(true);
            }
            freno2 = fila.getString(7);
            if(freno2.equals("aprobado")){
                x2.btnVerFrenos1.setChecked(true);
            }
            else if(freno2.equals("rechazado")){
                x2.btnVerFrenos2.setChecked(true);
            }
            llanta2 = fila.getString(8);
            if(llanta2.equals("aprobado")){
                x2.btnVerLlantas1.setChecked(true);
            }
            else if(llanta2.equals("rechazado")){
                x2.btnVerLlantas2.setChecked(true);
            }
            suspen2 = fila.getString(9);
            if(suspen2.equals("aprobado")){
                x2.btnVerSuspension1.setChecked(true);
            }
            else if(suspen2.equals("rechazado")){
                x2.btnVerSuspension2.setChecked(true);
            }
            kitSeg2 = fila.getString(10);
            if(kitSeg2.equals("aprobado")){
                x2.btnVerKit1.setChecked(true);
            }
            else if(kitSeg2.equals("rechazado")){
                x2.btnVerKit2.setChecked(true);
            }
            cinturon2 = fila.getString(11);
            if(cinturon2.equals("aprobado")){
                x2.btnVerCinturones1.setChecked(true);
            }
            else if(cinturon2.equals("rechazado")){
                x2.btnVerCinturones2.setChecked(true);
            }
            luz2 = fila.getString(12);
            if(luz2.equals("aprobado")){
                x2.btnVerLuces1.setChecked(true);
            }
            else if(luz2.equals("rechazado")){
                x2.btnVerLuces2.setChecked(true);
            }
            puerta2 = fila.getString(13);
            if(puerta2.equals("aprobado")){
                x2.btnVerPuertas1.setChecked(true);
            }
            else if(puerta2.equals("rechazado")){
                x2.btnVerPuertas2.setChecked(true);
            }
            vidrio2 = fila.getString(14);
            if(vidrio2.equals("aprobado")){
                x2.btnVerVidrios1.setChecked(true);
            }
            else if(vidrio2.equals("rechazado")){
                x2.btnVerVidrios2.setChecked(true);
            }
            tubEsc2 = fila.getString(15);
            if(tubEsc2.equals("aprobado")){
                x2.btnVerTubo1.setChecked(true);
            }
            else if(tubEsc2.equals("rechazado")){
                x2.btnVerTubo2.setChecked(true);
            }
            gas2 = fila.getString(16);
            if(gas2.equals("aprobado")){
                x2.btnVerGases1.setChecked(true);
            }
            else if(gas2.equals("rechazado")){
                x2.btnVerGases2.setChecked(true);
            }
            x2.txtVerComentarios.setText(fila.getString(17));


            bd.close();
            Toast.makeText(getContext(), "Producto encontrado", Toast.LENGTH_SHORT).show();
        }
        else{
            bd.close();
            Toast.makeText(getContext(), "No se encuentra el producto", Toast.LENGTH_SHORT).show();
            x2.txtMostrarFecha.setText("");
            x2.txtMostrarHora.setText("");
            x2.txtMostrarPatente.setText("");
        }
    }
}