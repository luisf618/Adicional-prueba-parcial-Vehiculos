/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebavehiculo.Clases;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author lcord
 */

@Entity
public class Propietario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idprop;

    private String cedula;
    private String apellido;
    private String nombre;

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL)
    private List<Vehiculo> vehiculos;

    public Propietario() {
    }

    public Propietario(String cedula, String apellido, String nombre) {
        this.cedula = cedula;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public int getIdprop() {
        return idprop;
    }

    public void setIdprop(int idprop) {
        this.idprop = idprop;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    
}
