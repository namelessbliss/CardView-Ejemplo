package com.app.nb.cardview.RecyclerView_CardView.model;

public class Pelicula {
    private String nombre;
    private int image;

    public Pelicula() {
    }

    public Pelicula(String nombre, int image) {
        this.nombre = nombre;
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
