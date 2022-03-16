package com.diegocampos.ejerciciorevisiontecnica;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.diegocampos.ejerciciorevisiontecnica.databinding.FragmentFragmento1Binding;
import com.diegocampos.ejerciciorevisiontecnica.databinding.FragmentFragmento2Binding;
import com.diegocampos.ejerciciorevisiontecnica.databinding.FragmentFragmento3Binding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Fragmento3 extends Fragment {

    private FragmentFragmento3Binding x3;

    private static final int REQUEST_PERMISSION_CAMERA = 100; //detectar la respuesta del usuario si es OK
    private static final int TAKE_PICTURE = 101; //detecta si se tomo la foto con la camara del celular

    private static final int REQUEST_PERMISSION_WRITE_STORAGE = 200; //detectar la respuesta del usuario si es ok

    Bitmap bitmap;
    int dia, mes, anio, hora, minuto;

    String docu3, direc3,frenos3, llantas3, suspen3, alin3, kitSeg3,
            cinturon3, luces3, puertas3, vidrios3, tuboEsc3, gases3,
            imagenMod1, imagenMod2, imagenMod3, imagenMod4;;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        x3 = FragmentFragmento3Binding.inflate(inflater, container, false);
        View v = x3.getRoot();

        x3.btnEditarFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar();
            }
        });

        x3.btnFecha3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarFecha();
            }
        });

        x3.btnHora3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarHora();
            }
        });
        x3.btnTomarFotoModRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisosCamara();
            }
        });
        x3.btnModFotoRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisosAlmacenamiento();
            }
        });

        return v;
    }

    public void editar(){
        ConexionBD conexion = new ConexionBD(getContext(), "administracion", null, 1);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        String codigoIngreso = x3.txtCodIngreso3.getText().toString();
        String fechaIngreso = x3.txtFecha3.getText().toString();
        String horaIngreso = x3.txtHora3.getText().toString();
        String patente = x3.txtPatente3.getText().toString();
        if (x3.btnDocuSi3.isChecked()){
            docu3 = "aprobado";
        }
        else if (x3.btnDocuNo3.isChecked()){
            docu3 = "rechazado";
        }
        if (x3.btnModDireccion1.isChecked()){
            direc3 = "aprobado";
        }
        else if (x3.btnModDireccion2.isChecked()){
            direc3= "rechazado";
        }

        if (x3.btnModFrenos1.isChecked()){
            frenos3 = "aprobado";
        }
        else if (x3.btnModFrenos2.isChecked()){
            frenos3= "rechazado";
        }

        if (x3.btnModLlantas1.isChecked()){
            llantas3 = "aprobado";
        }
        else if (x3.btnModLlantas2.isChecked()){
            llantas3= "rechazado";
        }

        if (x3.btnModSuspension1.isChecked()){
            suspen3 = "aprobado";
        }
        else if (x3.btnModSuspension2.isChecked()){
            suspen3= "rechazado";
        }

        if (x3.btnModAlineacion1.isChecked()){
            alin3 = "aprobado";
        }
        else if (x3.btnModAlineacion2.isChecked()){
            alin3= "rechazado";
        }

        if (x3.btnModKit1.isChecked()){
            kitSeg3 = "aprobado";
        }
        else if (x3.btnModKit2.isChecked()){
            kitSeg3= "rechazado";
        }
        if (x3.btnModCinturones1.isChecked()){
            cinturon3 = "aprobado";
        }
        else if (x3.btnModCinturones2.isChecked()){
            cinturon3= "rechazado";
        }
        if (x3.btnModLuces1.isChecked()){
            luces3 = "aprobado";
        }
        else if (x3.btnModLuces2.isChecked()){
            luces3= "rechazado";
        }

        if (x3.btnModPuertas1.isChecked()){
            puertas3 = "aprobado";
        }
        else if (x3.btnModPuertas2.isChecked()){
            puertas3= "rechazado";
        }

        if (x3.btnModVidrios1.isChecked()){
            vidrios3 = "aprobado";
        }
        else if (x3.btnModVidrios2.isChecked()){
            vidrios3= "rechazado";
        }

        if (x3.btnModTubo1.isChecked()){
            tuboEsc3 = "aprobado";
        }
        else if (x3.btnModTubo2.isChecked()){
            tuboEsc3= "rechazado";
        }

        if (x3.btnModGases1.isChecked()){
            gases3 = "aprobado";
        }
        else if (x3.btnModGases2.isChecked()){
            gases3= "rechazado";
        }

        String observaciones = x3.txtModComentarios.getText().toString();
        String imagenRev1 = imagenMod1;
        String imagenRev2 = imagenMod2;
        String imagenRev3 = imagenMod3;
        String imagenRev4 = imagenMod4;



        ContentValues registro = new ContentValues();
        registro.put("codigoIngreso", codigoIngreso);
        registro.put("fechaIngreso", fechaIngreso);
        registro.put("horaIngreso", horaIngreso);
        registro.put("patente", patente);
        registro.put("documentacion", docu3);
        registro.put("direccion", direc3);
        registro.put("frenos", frenos3);
        registro.put("llantas", llantas3);
        registro.put("suspension", suspen3);
        registro.put("alineacion", alin3);
        registro.put("kitseguridad", kitSeg3);
        registro.put("cinturones", cinturon3);
        registro.put("luces", luces3);
        registro.put("puertas", puertas3);
        registro.put("vidrios", vidrios3);
        registro.put("tuboEscape", tuboEsc3);
        registro.put("gases", gases3);
        registro.put("observaciones", observaciones);
        registro.put(("imagenRev1"), imagenRev1);
        registro.put(("imagenRev2"), imagenRev2);
        registro.put(("imagenRev3"), imagenRev3);
        registro.put(("imagenRev4"), imagenRev4);

        int a=bd.update("registros", registro, "codigoIngreso='"+codigoIngreso+"'", null);

        if (a>0){
            Toast.makeText(getContext(), "Modificado correctamente", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getContext(), "No se pudo modificar", Toast.LENGTH_SHORT).show();
        }
        bd.close();

    }
    public void mostrarFecha(){
        Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        anio = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog( getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                x3.txtFecha3.setText(i2 + "-" + (i1+1) + "-" + i);
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
                x3.txtHora3.setText(i + ":" + i1);
            }
        }, hora, minuto, true);

        timePickerDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == TAKE_PICTURE){
            if (resultCode == Activity.RESULT_OK && data != null){
                bitmap = (Bitmap) data.getExtras().get("data");
                x3.imagenModRevision.setImageBitmap(bitmap);
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
                if(imagenMod1==null){
                    imagenMod1= imageUri.toString();
                }
                else if (imagenMod2==null){
                    imagenMod2 = imageUri.toString();
                }
                else if(imagenMod3==null){
                    imagenMod3= imageUri.toString();
                }
                else if (imagenMod4==null){
                    imagenMod4= imageUri.toString();
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
            if(imagenMod1==null){
                imagenMod1= file.toString();
            }
            else if (imagenMod2==null){
                imagenMod2 = file.toString();
            }
            else if(imagenMod3==null){
                imagenMod3= file.toString();
            }
            else if (imagenMod4==null){
                imagenMod4= file.toString();
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