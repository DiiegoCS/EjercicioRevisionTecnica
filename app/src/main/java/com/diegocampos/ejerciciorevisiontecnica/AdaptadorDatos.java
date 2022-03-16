package com.diegocampos.ejerciciorevisiontecnica;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolder> {

    ArrayList<Bitmap> listFotos;
    Context context;

    public AdaptadorDatos(ArrayList<Bitmap> listFotos, Context context) {
        this.listFotos = listFotos;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorDatos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.items_recycler, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDatos.ViewHolder holder, int position) {

        Bitmap bit = listFotos.get(position);
        holder.image.setImageBitmap(bit);

    }

    @Override
    public int getItemCount() {
        return listFotos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            image=itemView.findViewById(R.id.imRecycler);
        }
    }
}
