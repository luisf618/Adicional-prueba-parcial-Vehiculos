/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebavehiculo.Logica;

import com.mycompany.pruebavehiculo.Clases.Propietario;
import com.mycompany.pruebavehiculo.Clases.Turno;
import com.mycompany.pruebavehiculo.Clases.Vehiculo;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author lcord
 */
public class Logica {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_PruebaVehiculo_jar_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public Propietario buscarPorCedula(String cedula) {
        TypedQuery<Propietario> query = em.createQuery(
                "SELECT p FROM Propietario p WHERE p.cedula = :cedula", Propietario.class);
        query.setParameter("cedula", cedula);

        List<Propietario> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    public Vehiculo buscarPorPlaca(String placa) {
        TypedQuery<Vehiculo> query = em.createQuery(
                "SELECT v FROM Vehiculo v WHERE v.placa = :placa", Vehiculo.class);
        query.setParameter("placa", placa);

        List<Vehiculo> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    public boolean tieneTurnoEnDia(Vehiculo vehiculo, int dia) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(t) FROM Turno t WHERE t.vehiculo = :vehiculo AND t.dia = :dia", Long.class);
        query.setParameter("vehiculo", vehiculo);
        query.setParameter("dia", dia);

        Long count = query.getSingleResult();
        return count > 0;
    }

    public void registrarTurno(Turno turno) {
        try {
            tx.begin();
            em.persist(turno);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public boolean existeTurnoEnAndenHoraDia(int anden, int hora, int dia) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(t) FROM Turno t WHERE t.anden = :anden AND t.hora = :hora AND t.dia = :dia", Long.class);
        query.setParameter("anden", anden);
        query.setParameter("hora", hora);
        query.setParameter("dia", dia);

        Long count = query.getSingleResult();
        return count > 0;
    }

    public void cerrar() {
        em.close();
        emf.close();
    }
}
