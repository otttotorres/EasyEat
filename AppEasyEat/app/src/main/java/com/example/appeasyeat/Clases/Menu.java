package com.example.appeasyeat.Clases;

//Esta es la clase se usara en toda la aplicacion...
public class Menu {
    private String id;
    private String nombre;
    private String precio;
    //Constructor de la clase Menu
    public Menu(String id, String nombre, String precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Menu() {
    }
    //Los metodos get y set la clase para poder modicar sus atributos
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
