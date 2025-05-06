/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebavehiculo;

import javax.swing.*;
import com.mycompany.pruebavehiculo.Clases.Propietario;
import com.mycompany.pruebavehiculo.Clases.Vehiculo;
import com.mycompany.pruebavehiculo.Clases.Turno;
import com.mycompany.pruebavehiculo.Logica.Logica;
import com.mycompany.pruebavehiculo.Persistencia.PropietarioJpaController;
import com.mycompany.pruebavehiculo.Persistencia.VehiculoJpaController;
import com.mycompany.pruebavehiculo.Persistencia.TurnoJpaController;

/**
 *
 * @author lcord
 */
public class Inicio extends JFrame {

    // Campos para los datos de Vehículo y Turno
    private JTextField txtCedula = new JTextField();
    private JTextField txtNombre = new JTextField();
    private JTextField txtApellido = new JTextField();
    private JTextField txtPlaca = new JTextField();
    private JTextField txtMarca = new JTextField();
    private JTextField txtEstado = new JTextField();
    private JButton btnGuardarVehiculo = new JButton("Guardar Vehículo");

    private JTextField txtPlacaTurno = new JTextField();
    private JTextField txtAnden = new JTextField();
    private JTextField txtDia = new JTextField();
    private JTextField txtHora = new JTextField();
    private JButton btnRegistrarTurno = new JButton("Registrar Turno");

    public Inicio() {
        initComponents();
    }

    private void btnGuardarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {
        String cedula = txtCedula.getText();
        Logica logica = new Logica();
        Propietario p = logica.buscarPorCedula(cedula);

        if (p == null) {
            p = new Propietario();
            p.setCedula(cedula);
            p.setNombre(txtNombre.getText());
            p.setApellido(txtApellido.getText());
            new PropietarioJpaController().create(p);
        }

        Vehiculo v = new Vehiculo();
        v.setPlaca(txtPlaca.getText());
        v.setMarca(txtMarca.getText());
        v.setEstado(Integer.parseInt(txtEstado.getText()));
        v.setPropietario(p);

        new VehiculoJpaController().create(v);
        JOptionPane.showMessageDialog(this, "Vehículo guardado.");
    }

    private void btnRegistrarTurnoActionPerformed(java.awt.event.ActionEvent evt) {
        String placa = txtPlacaTurno.getText();
        Logica logica = new Logica();
        Vehiculo v = logica.buscarPorPlaca(placa);

        if (v == null) {
            JOptionPane.showMessageDialog(this, "Vehículo no encontrado.");
            return;
        }

        int dia = Integer.parseInt(txtDia.getText());
        int anden = Integer.parseInt(txtAnden.getText());
        int hora = Integer.parseInt(txtHora.getText());

        if (logica.tieneTurnoEnDia(v, dia)) {
            JOptionPane.showMessageDialog(this, "El vehículo ya tiene un turno en ese día.");
            return;
        }

        if (logica.existeTurnoEnAndenHoraDia(anden, hora, dia)) {
            JOptionPane.showMessageDialog(this, "Ya existe un turno en ese andén a esa hora y día.");
            return;
        }

        Turno t = new Turno();
        t.setAnden(anden);
        t.setDia(dia);
        t.setHora(hora);
        t.setVehiculo(v);

        logica.registrarTurno(t);
        JOptionPane.showMessageDialog(this, "Turno registrado.");
    }

    private void initComponents() {
        setTitle("Gestión de Turnos Vehiculares");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(null);

        // Vehículo
        addField("Cédula:", txtCedula, 20, 20);
        addField("Nombre:", txtNombre, 20, 50);
        addField("Apellido:", txtApellido, 20, 80);
        addField("Placa:", txtPlaca, 20, 110);
        addField("Marca:", txtMarca, 20, 140);
        addField("Estado:", txtEstado, 20, 170);
        btnGuardarVehiculo.setBounds(20, 200, 180, 30);
        add(btnGuardarVehiculo);

        // Turno
        addField("Placa Vehículo:", txtPlacaTurno, 320, 20);
        addField("Andén:", txtAnden, 320, 50);
        addField("Día:", txtDia, 320, 80);
        addField("Hora:", txtHora, 320, 110);
        btnRegistrarTurno.setBounds(320, 140, 180, 30);
        add(btnRegistrarTurno);

        btnGuardarVehiculo.addActionListener(this::btnGuardarVehiculoActionPerformed);
        btnRegistrarTurno.addActionListener(this::btnRegistrarTurnoActionPerformed);
    }

    private void addField(String label, JTextField field, int x, int y) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(x, y, 100, 20);
        field.setBounds(x + 100, y, 150, 20);
        add(jLabel);
        add(field);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Inicio().setVisible(true));
    }
}
