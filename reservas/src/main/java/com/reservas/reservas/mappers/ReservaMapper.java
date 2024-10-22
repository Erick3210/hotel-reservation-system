package com.reservas.reservas.mappers;

import com.reservas.reservas.models.Reserva;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReservaMapper {

    @Insert("INSERT INTO Reserva (fecha_inicio, fecha_fin, id_habitacion, nombre_cliente, estado_reserva) " +
            "VALUES (#{fechaInicio}, #{fechaFin}, #{idHabitacion}, #{nombreCliente}, #{estadoReserva})")
    void insertReserva(Reserva reserva); //METODO INSERT

    @Select("SELECT * FROM Reserva WHERE id = #{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "fechaInicio", column = "fecha_inicio"),
        @Result(property = "fechaFin", column = "fecha_fin"),
        @Result(property = "idHabitacion", column = "id_habitacion"),
        @Result(property = "nombreCliente", column = "nombre_cliente"),
        @Result(property = "estadoReserva", column = "estado_reserva")
    })
    Reserva getReservaById(Long id);  //CONSULTA POR ID

    @Select("SELECT * FROM Reserva")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "fechaInicio", column = "fecha_inicio"),
        @Result(property = "fechaFin", column = "fecha_fin"),
        @Result(property = "idHabitacion", column = "id_habitacion"),
        @Result(property = "nombreCliente", column = "nombre_cliente"),
        @Result(property = "estadoReserva", column = "estado_reserva")
    })
    List<Reserva> getAllReservas(); //CONSULTA GENERAL

    @Update("UPDATE Reserva SET fecha_inicio = #{fechaInicio}, fecha_fin = #{fechaFin}, " +
            "id_habitacion = #{idHabitacion}, nombre_cliente = #{nombreCliente}, " +
            "estado_reserva = #{estadoReserva} WHERE id = #{id}")
    void updateReserva(Reserva reserva); //METODO PARA ACTUALIZAR

    @Delete("DELETE FROM Reserva WHERE id = #{id}")
    void deleteReserva(Long id); //METODO PARA BORRAR
}
