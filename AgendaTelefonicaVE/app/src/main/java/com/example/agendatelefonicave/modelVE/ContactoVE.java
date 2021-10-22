package com.example.agendatelefonicave.modelVE;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "contactos")
public class ContactoVE implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id;
    String nombre;
    String numero;
    String propietario; // el nombre almanecado con shared prefences del proietario (UserName)

    //constructor vacio
    public ContactoVE() {
    }

    //constructor
    public ContactoVE(int id, String nombre, String numero, String propietario) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.propietario = propietario;
    }

    //Getters & Setters correspondientes
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

}
