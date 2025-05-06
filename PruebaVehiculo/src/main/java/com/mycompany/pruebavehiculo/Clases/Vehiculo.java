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
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVehi;

    private String placa;
    private String marca;
    private int estado;

    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Propietario propietario;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<Turno> turnos;

    public Vehiculo() {
    }

    public Vehiculo(String placa, String marca, int estado, Propietario propietario) {
        this.placa = placa;
        this.marca = marca;
        this.estado = estado;
        this.propietario = propietario;
    }

    public int getIdVehi() {
        return idVehi;
    }

    public void setIdVehi(int idVehi) {
        this.idVehi = idVehi;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    
}
