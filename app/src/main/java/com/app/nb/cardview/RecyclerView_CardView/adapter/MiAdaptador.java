package com.app.nb.cardview.RecyclerView_CardView.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.nb.cardview.R;
import com.app.nb.cardview.RecyclerView_CardView.model.Pelicula;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {

    private List<Pelicula> peliculas;
    private int layout;
    private OnItemClickListener itemClickListener;

    private Context context;

    public MiAdaptador(List<Pelicula> peliculas, int layout, OnItemClickListener listener) {
        this.peliculas = peliculas;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        context = viewGroup.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(peliculas.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Elementos de la UI a rellenar
        public TextView textViewNombre;
        public ImageView imageViewPoster;

        public ViewHolder(@NonNull View view) {
            super(view);
            textViewNombre = (TextView) itemView.findViewById(R.id.textViewTitulo);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
        }

        public void bind(final Pelicula pelicula, final OnItemClickListener listener) {
            //Procesamos los datos a renderizar
            //this.texto.setText(pelicula);
            textViewNombre.setText(pelicula.getNombre());
            Picasso.get().load(pelicula.getImage()).fit().into(imageViewPoster);
            //imageViewPoster.setImageResource(pelicula.getImage());

            //Por cada elemento del recycler view, definimos un click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(pelicula, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Pelicula pelicula, int posicion);
    }
}
