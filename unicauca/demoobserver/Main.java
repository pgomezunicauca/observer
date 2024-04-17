
package com.unicauca.demoobserver;

import com.unicauca.demoobserver.dominio.entidades.Candidato;
import com.unicauca.demoobserver.dominio.entidades.Persona;
import com.unicauca.demoobserver.dominio.entidades.Voto;
import com.unicauca.demoobserver.dominio.servicios.VotosServicio;
import com.unicauca.demoobserver.presentacion.GUINuevoVoto;
import com.unicauca.demoobserver.presentacion.GUIPieChart;
import java.time.LocalDate;
import java.util.HashMap;
import javax.swing.JFrame;

/**
 *
 * @author libardo
 */
public class Main {

    public static void main(String[] args) {
        HashMap<String, Integer> data = new HashMap<>();
        
        VotosServicio servicio = new VotosServicio();
        servicio.adicionarCandidato(new Candidato(1, "Petro"));
        servicio.adicionarCandidato(new Candidato(2, "Duque"));
        servicio.adicionarCandidato(new Candidato(3, "Fajardo"));
        
        servicio.adicionarPesonas(new Persona("89000000", "Francisco", "Arias", false));
        servicio.adicionarPesonas(new Persona("90000000", "Juan", "Perrez", false));
        servicio.adicionarPesonas(new Persona("91000000", "Maria", "Sanchez", false));
        servicio.adicionarPesonas(new Persona("92000000", "Jose", "Castro", false));
        servicio.adicionarPesonas(new Persona("93000000", "Carlos", "Ortega", false));
        servicio.adicionarPesonas(new Persona("94000000", "Antonieta", "Vega", false));
        servicio.adicionarPesonas(new Persona("95000000", "Rosario", "Herrera", false));
        
        servicio.adicionarVoto(new Voto(servicio.getPersonaByCedula("89000000"), servicio.getCandidatoByNombres("Petro"), LocalDate.now()));
        data = servicio.procesarVotos();
        
        GUINuevoVoto guiNuevoVoto = new GUINuevoVoto(servicio);
        guiNuevoVoto.setSize(400, 300);
        guiNuevoVoto.setVisible(true);
        
        JFrame frame = new JFrame("Pie Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        GUIPieChart pieChart = new GUIPieChart(data);
        frame.add(pieChart);
        frame.setVisible(true);
        
        servicio.addObserver(pieChart);
        
        
    }
}
