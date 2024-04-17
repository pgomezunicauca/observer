package com.unicauca.demoobserver.dominio.servicios;

import com.unicauca.demoobserver.dominio.entidades.Candidato;
import com.unicauca.demoobserver.dominio.entidades.Persona;
import com.unicauca.demoobserver.dominio.entidades.Voto;
import com.unicauca.demoobserver.infra.Subject;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


public class VotosServicio extends Subject{

    private LocalDate fecha;
    private static List<Voto> votos;
    private static List<Candidato> candidatos;
    private static List<Persona> personas;
    

    public VotosServicio()  {
        votos = new ArrayList<>();
        candidatos = new ArrayList<>();
        personas = new ArrayList<>();
    }

    public void adicionarVoto(Voto voto) {
        votos.add(voto);
        voto.getPersona().setYaVoto(true);
        notifyObservers();
    }

    public void adicionarCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }
    public void adicionarPesonas(Persona persona) {
        personas.add(persona);
    }
    public HashMap procesarVotos() {
        HashMap<String, Integer> data = new HashMap<>();

        for (Candidato candidato : candidatos) {
            data.put(candidato.getNombres(), calcularVotos(candidato.getNombres()));
        }
        return data;
    }

    private int calcularVotos(String nombres) {
        int totalVotos = 0;
        for (Voto voto : votos) {
            if (voto.getCandidato().getNombres().equals(nombres)){
                totalVotos++;
            }
        }
        return totalVotos;
    }
    public List<Candidato> getCandidatos(){
        return candidatos;
    }
    
    public Candidato getCandidatoByNombres(String nombres){
        for(Candidato candidato:candidatos){
            if (candidato.getNombres().equals(nombres)){
                return candidato;
            }
        }
        return null;
    }
    public Persona getPersonaByCedula(String cedula){
        for(Persona persona:personas){
            if (persona.getCedula().equals(cedula)){
                return persona;
            }
        }
        return null;
    }
    
}
