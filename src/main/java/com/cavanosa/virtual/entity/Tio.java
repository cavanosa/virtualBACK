package com.cavanosa.virtual.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Tio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(unique = true)
    private String nombre;
    @NotNull
    @Column(unique = true)
    private String email;

    public Tio() {
    }

    public Tio(@NotNull String nombre, @NotNull String email) {
        this.nombre = nombre;
        this.email = email;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
