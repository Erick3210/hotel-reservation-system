package com.reservas.reservas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    private Long id; // ID de la reserva
    private String fechaInicio; // Fecha de inicio como String
    private String fechaFin; // Fecha de fin como String
    private Long idHabitacion; // ID de la habitación
    private String nombreCliente; // Nombre del cliente
    private String estadoReserva; // Estado de la reserva
}


//NO HACE FALTA USAR GETTERS AND SETTERS, YA QUE SE ESTA USANDO LA DEPENDENCIA LOMBOK