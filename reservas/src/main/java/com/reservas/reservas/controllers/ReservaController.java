package com.reservas.reservas.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.reservas.reservas.models.Reserva;
import com.reservas.reservas.mappers.ReservaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservas") //RUTA PRINCIPAL
public class ReservaController {

    private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);

    private final ReservaMapper reservaMapper;

    @Autowired
    public ReservaController(ReservaMapper reservaMapper) {
        this.reservaMapper = reservaMapper;
    }

    @GetMapping("/lista") // Mapeo para listar reservas
    public String listarReservas(Model model) {
        List<Reserva> reservas = reservaMapper.getAllReservas();
        logger.info("Número de reservas recuperadas: {}", reservas.size());

        for (Reserva reserva : reservas) {
            logger.info("Reserva ID: {}, Cliente: {}, Fecha Inicio: {}, Fecha Fin: {}, Estado: {}",
                    reserva.getId(), reserva.getNombreCliente(), reserva.getFechaInicio(), reserva.getFechaFin(),
                    reserva.getEstadoReserva());
        }

        model.addAttribute("reservas", reservas);
        return "listaReservas"; // nombre del template (HTML PRINCIPAL O INDEX) para la lista de reservas
    }




    @GetMapping("/nuevo") // Este es el mapeo para mostrar el formulario
    public String mostrarFormularioReserva(Model model) {
        model.addAttribute("reserva", new Reserva());
        return "nuevaReserva"; // nombre del template HTML PARA DAR DE ALTA UN NUEVO REGISTRO O RESERVA
    }

    @PostMapping("/nuevaReserva")
    public String guardarReserva(@ModelAttribute Reserva reserva, BindingResult result) {
        // Manejar errores
        if (result.hasErrors()) {
            logger.error("Errores en la reserva: {}", result.getAllErrors());
            return "nuevaReserva"; // Retornar el mismo template en caso de error A ESTA RUTA O PAGINA
        }

        try {
            reservaMapper.insertReserva(reserva);
            logger.info("Reserva guardada: {}", reserva); // Log de éxito
        } catch (Exception e) {
            logger.error("Error al guardar la reserva: {}", e.getMessage());
            return "nuevaReserva"; // Retornar en caso de error
        }

        return "redirect:/reservas/lista"; // Redirigir después de guardar A ESTA RUTA O PAGINA
    }




    @GetMapping("/{id}")
    public String obtenerReserva(@PathVariable Long id, Model model) {
        Reserva reserva = reservaMapper.getReservaById(id);
        model.addAttribute("reserva", reserva);
        return "detalleReserva"; // Vista del formulario de actualización SE ACTUALIZA CADA REGISTRO POR ID
    }

    @PostMapping("/actualizar")
    public String actualizarReserva(@ModelAttribute Reserva reserva, BindingResult result) { //SE GESTIONAN OBJETOS DEL MODELO RESERVA
        if (result.hasErrors()) {
            logger.error("Errores en la actualización de la reserva: {}", result.getAllErrors());
            return "detalleReserva";
        }

        reservaMapper.updateReserva(reserva); //METODOS DEL MAPPER
        return "redirect:/reservas/lista"; // Redirigir a la lista de reservas (OTRO TEMPLATE O HTML)
    }




    @GetMapping("/eliminar/{id}")
    public String eliminarReserva(@PathVariable Long id) {
        reservaMapper.deleteReserva(id);
        return "redirect:/reservas/lista"; // Redirige después de eliminar a la lista (OTRO TEMPLATE O HTML)
    }

}
