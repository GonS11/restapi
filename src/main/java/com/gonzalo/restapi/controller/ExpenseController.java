package com.gonzalo.restapi.controller;

import com.gonzalo.restapi.dto.ExpenseDTO; // DTO de gastos
import com.gonzalo.restapi.io.ExpenseResponse; // Respuesta para el cliente
import com.gonzalo.restapi.service.ExpenseService; // Lógica de los gastos
import lombok.RequiredArgsConstructor; // Constructor automático para campos final
import lombok.extern.slf4j.Slf4j; // Para registro de logs
import org.modelmapper.ModelMapper; // Para mapear entre objetos
import org.springframework.web.bind.annotation.CrossOrigin; // Permite solicitudes CORS
import org.springframework.web.bind.annotation.GetMapping; // Para manejar solicitudes GET
import org.springframework.web.bind.annotation.RestController; // Define la clase como controlador REST

import java.util.List;
import java.util.stream.Collectors; // Para transformar listas con streams

/**
 * Controlador para la gestión de gastos.
 * @author Gonzalo
 */
@RestController // Clase como controlador REST
@RequiredArgsConstructor // Constructor con campos final
@Slf4j // Para registrar información
@CrossOrigin("*") // Permite solicitudes desde cualquier origen
public class ExpenseController {

    private final ExpenseService expenseService; // Servicio para obtener los gastos
    private final ModelMapper modelMapper; // Mapper para convertir entre DTOs y Response

    /**
     * Obtiene la lista de todos los gastos.
     * @return Lista de gastos convertidos a Response.
     */
    @GetMapping("/expenses") // Punto de acceso para obtener los gastos
    public List<ExpenseResponse> getExpenses() {
        log.info("API GET /expenses called");

        // Obtiene los gastos como DTOs
        List<ExpenseDTO> list = expenseService.getAllExpenses();
        log.info("Data from service: {}", list);

        // Convierte los DTOs a Response
        return list.stream()
                .map(this::mapToExpenseResponse) // Mapea cada DTO a Response
                .collect(Collectors.toList()); // Recoge los resultados en una lista
    }

    /**
     * Convierte un ExpenseDTO a ExpenseResponse.
     * @param expenseDTO El DTO del gasto
     * @return ExpenseResponse
     */
    private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseResponse.class); // Convierte usando ModelMapper
    }
}
