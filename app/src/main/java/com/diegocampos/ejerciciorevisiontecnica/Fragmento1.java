package com.diegocampos.ejerciciorevisiontecnica;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.diegocampos.ejerciciorevisiontecnica.databinding.ActivityMainBinding;
import com.diegocampos.ejerciciorevisiontecnica.databinding.FragmentFragmento1Binding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fragmento1 extends Fragment {

    private FragmentFragmento1Binding x;

    private static final int REQUEST_PERMISSION_CAMERA = 100; //detectar la respuesta del usuario si es OK
    private static final int TAKE_PICTURE = 101; //detecta si se tomo la foto con la camara del celular

    private static final int REQUEST_PERMISSION_WRITE_STORAGE = 200; //detectar la respuesta del usuario si es ok

    Bitmap bitmap;

    int dia, mes, anio, hora, minuto;
    String docu, alin, direcc, freno, llanta, suspen, kitSeg, cinturon, luz, puerta, vidrio, tubEsc, gas,
            imagen1, imagen2, imagen3, imagen4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        x = FragmentFragmento1Binding.inflate(inflater, container, false);
        View v = x.getRoot();

        x.btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarFecha();
            }
        });
        x.btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarHora();
            }
        });

        x.btnGuardarFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });

        x.btnDocuSi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnDocuSi:
                        if (checked)
                            docu= "aprobado";
                        break;
                }
            }
        });

        x.btnDocuNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnDocuNo:
                        if (checked)
                            docu= "rechazado";
                        break;
                }
            }
        });

        x.btnDireccion1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnDireccion1:
                        if (checked)
                            direcc= "aprobado";
                        break;
                }
            }
        });

        x.btnDireccion2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnDireccion2:
                        if (checked)
                            direcc= "rechazado";
                        break;
                }
            }
        });

        x.btnFrenos1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnFrenos1:
                        if (checked)
                            freno= "aprobado";
                        break;
                }
            }
        });

        x.btnFrenos2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnFrenos2:
                        if (checked)
                            freno= "rechazado";
                        break;
                }
            }
        });

        x.btnLlantas1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnLlantas1:
                        if (checked)
                            llanta= "aprobado";
                        break;
                }
            }
        });

        x.btnLlantas2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnLlantas2:
                        if (checked)
                            llanta= "rechazado";
                        break;
                }
            }
        });

        x.btnSuspension1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnSuspension1:
                        if (checked)
                            suspen= "aprobado";
                        break;
                }
            }
        });

        x.btnSuspension2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnSuspension2:
                        if (checked)
                            suspen= "rechazado";
                        break;
                }
            }
        });

        x.btnAlineacion1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnAlineacion1:
                        if (checked)
                            alin= "aprobado";
                        break;
                }
            }
        });

        x.btnAlineacion2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnAlineacion2:
                        if (checked)
                            alin= "rechazado";
                        break;
                }
            }
        });

        x.btnKit1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnKit1:
                        if (checked)
                            kitSeg= "aprobado";
                        break;
                }
            }
        });

        x.btnKit2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnKit2:
                        if (checked)
                            kitSeg= "rechazado";
                        break;
                }
            }
        });

        x.btnCinturones1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnCinturones1:
                        if (checked)
                            cinturon= "aprobado";
                        break;
                }
            }
        });

        x.btnCinturones2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnCinturones2:
                        if (checked)
                            cinturon= "rechazado";
                        break;
                }
            }
        });

        x.btnLuces1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnLuces1:
                        if (checked)
                            luz= "aprobado";
                        break;
                }
            }
        });

        x.btnLuces2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnLuces2:
                        if (checked)
                            luz= "rechazado";
                        break;
                }
            }
        });

        x.btnPuertas1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnPuertas1:
                        if (checked)
                            puerta= "aprobado";
                        break;
                }
            }
        });

        x.btnPuertas2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnPuertas2:
                        if (checked)
                            puerta= "rechazado";
                        break;
                }
            }
        });

        x.btnVidrios1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnVidrios1:
                        if (checked)
                            vidrio= "aprobado";
                        break;
                }
            }
        });

        x.btnVidrios2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnVidrios2:
                        if (checked)
                            vidrio= "rechazado";
                        break;
                }
            }
        });

        x.btnTubo1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnTubo1:
                        if (checked)
                            tubEsc= "aprobado";
                        break;
                }
            }
        });

        x.btnTubo2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnTubo2:
                        if (checked)
                            tubEsc= "rechazado";
                        break;
                }
            }
        });

        x.btnGases1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnGases1:
                        if (checked)
                            gas= "aprobado";
                        break;
                }
            }
        });

        x.btnGases2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = ((RadioButton) compoundButton).isChecked();
                switch(compoundButton.getId()) {
                    case R.id.btnGases2:
                        if (checked)
                            gas= "rechazado";
                        break;
                }
            }
        });

        x.btnTomarFotoRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    permisosCamara();
                }
        });

        x.btnGuardarFotoRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisosAlmacenamiento();
            }
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        x = null;
    }

    public void mostrarFecha(){
        Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        anio = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog( getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                x.txtFecha.setText(i2 + "-" + (i1+1) + "-" + i);
            }
        }, anio, mes, dia);

        datePickerDialog.show();
    }

    public void mostrarHora(){
        Calendar c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minuto = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                x.txtHora.setText(i + ":" + i1);
            }
        }, hora, minuto, true);

        timePickerDialog.show();
    }

    public void guardar() {
        ConexionBD conexion = new ConexionBD(getContext(), "administracion", null, 1);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        String codigoIngreso = x.txtCodIngreso.getText().toString();
        String fechaIngreso = x.txtFecha.getText().toString();
        String horaIngreso = x.txtHora.getText().toString();
        String patente = x.txtPatente.getText().toString();
        String documentacion = docu;
        String direccion = direcc;
        String frenos = freno;
        String llantas = llanta;
        String suspension = suspen;
        String alineacion = alin;
        String kitSeguridad = kitSeg;
        String cinturones = cinturon;
        String luces = luz;
        String puertas = puerta;
        String vidrios = vidrio;
        String tuboEscape = tubEsc;
        String gases = gas;
        String observaciones = x.txtComentarios.getText().toString();
        String imagenRev1 = imagen1;
        String imagenRev2 = imagen2;
        String imagenRev3 = imagen3;
        String imagenRev4 = imagen4;

        ContentValues registro =new ContentValues();
        registro.put("codigoIngreso", codigoIngreso);
        registro.put("fechaIngreso", fechaIngreso);
        registro.put("horaIngreso", horaIngreso);
        registro.put("patente", patente);
        registro.put("documentacion", documentacion);
        registro.put("alineacion", alineacion);
        registro.put("direccion", direccion);
        registro.put("frenos", frenos);
        registro.put("llantas", llantas);
        registro.put("suspension", suspension);
        registro.put("kitSeguridad", kitSeguridad);
        registro.put("cinturones", cinturones);
        registro.put("luces", luces);
        registro.put("puertas", puertas);
        registro.put("vidrios", vidrios);
        registro.put("tuboEscape", tuboEscape);
        registro.put("gases", gases);
        registro.put("observaciones", observaciones);
        registro.put(("imagenRev1"), imagenRev1);
        registro.put(("imagenRev2"), imagenRev2);
        registro.put(("imagenRev3"), imagenRev3);
        registro.put(("imagenRev4"), imagenRev4);


        bd.insert("registros", null, registro);
        bd.close();

        Toast.makeText(getContext(), "Registrado correctamente", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == TAKE_PICTURE){
            if (resultCode == Activity.RESULT_OK && data != null){
                bitmap = (Bitmap) data.getExtras().get("data");
                x.imagenRevision.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CAMERA){
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                tomarFoto();
            } //puede ir un else indicando que no se aceptaron los permisos
        }
        else if(requestCode == REQUEST_PERMISSION_WRITE_STORAGE){
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                guardarFoto();
            } //puede ir un else indicando que no se aceptaron los permisos
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void permisosCamara(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){ //android marshmallow (Permiso en tiempo de ejecución)
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                tomarFoto();
            }
            else{ //api > 28 (Q)
                ActivityCompat.requestPermissions(
                        getActivity(),
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_PERMISSION_CAMERA
                );
            }
        }
        else{ //permiso en tiempo de descarga
            tomarFoto();
        }
    }

    public void permisosAlmacenamiento(){
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){ //Apis mas antiguas < 28
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    guardarFoto();
                }
                else{
                    //api > 28 (Q)
                    ActivityCompat.requestPermissions(
                            getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_PERMISSION_WRITE_STORAGE
                    );
                }
            }
            else{
                guardarFoto();
            }
        }
        else{
            guardarFoto();
        }
    }


    public void tomarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null){
            startActivityForResult(intent, TAKE_PICTURE);
        }
    }
    public void guardarFoto(){ //Android Q y posteriores
        OutputStream outputStream = null;
        File file = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){ //versiones recientes
            ContentResolver resolver = getContext().getContentResolver();//para manejar los values
            ContentValues values = new ContentValues(); //metadatos de imagenes tipo, render, etc

            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
            String tiempo = formatter.format(new java.util.Date());

            String filename = System.currentTimeMillis() + " " + tiempo;

            values.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp");
            values.put(MediaStore.Images.Media.IS_PENDING, 1); //1 la imagen se esta procesando

            Uri collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY); //construir una ruta en android
            Uri imageUri = resolver.insert(collection, values); //insertando en memoria la ruta anterior


            try {
                outputStream = resolver.openOutputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if(imageUri != null){
                if(imagen1==null){
                    imagen1= imageUri.toString();
                }
                else if (imagen2==null){
                    imagen2 = imageUri.toString();
                }
                else if(imagen3==null){
                    imagen3= imageUri.toString();
                }
                else if (imagen4==null){
                    imagen4= imageUri.toString();
                }
            }

            values.clear();
            values.put(MediaStore.Images.Media.IS_PENDING, 0);
            resolver.update(imageUri, values, null, null);
        }
        else{ //Apis mas antiguas < 28
            String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
            String tiempo = formatter.format(new Date());

            String filename = System.currentTimeMillis() + " " + tiempo + ".jpg"; //nombre del archivo

            file = new File(imageDir, filename);

            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(file != null){
            if(imagen1==null){
                imagen1= file.toString();
            }
            else if (imagen2==null){
                imagen2 = file.toString();
            }
            else if(imagen3==null){
                imagen3= file.toString();
            }
            else if (imagen4==null){
                imagen4= file.toString();
            }
        }

        boolean saved = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream); //para la calidad y compresión del archivo
        if (saved){
            Toast.makeText(getContext(), "Imagen guardada", Toast.LENGTH_SHORT).show();
        }

        if (outputStream != null){
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (file != null){
            MediaScannerConnection.scanFile(getContext(), new String[]{file.toString()}, null, null);
        }

    }


}