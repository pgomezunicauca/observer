
package com.unicauca.demoobserver.dominio.entidades;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter  // Genera automáticamente getters para todos los campos
@Setter  // Genera automáticamente setters para todos los campos
@AllArgsConstructor  // Genera automáticamente un constructor con todos los argumentos
public class Voto {
    private Persona persona;
    private Candidato candidato;
    private LocalDate fecha;
    
}
