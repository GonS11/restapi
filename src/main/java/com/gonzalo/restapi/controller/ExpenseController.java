package com.gonzalo.restapi.controller;

import com.gonzalo.restapi.dto.ExpenseDTO; // DTO para la información de los gastos
import com.gonzalo.restapi.io.ExpenseResponse; // Respuesta final que se devuelve al cliente
import com.gonzalo.restapi.service.ExpenseService; // Servicio que maneja la lógica de los gastos
import lombok.RequiredArgsConstructor; // Lombok para generar el constructor automáticamente
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper; // Biblioteca para mapear entre objetos
import org.springframework.web.bind.annotation.GetMapping; // Anotación para manejar solicitudes GET
import org.springframework.web.bind.annotation.RestController; // Marca la clase como un controlador REST

import java.util.List;
import java.util.stream.Collectors; // Para transformar listas usando streams

/**
 * This is controller class for Expense module
 * @author Gonzalo
 * */
@RestController // Define que esta clase es un controlador REST
@RequiredArgsConstructor // Genera automáticamente un constructor con los campos final
@Slf4j //Se usa para los logs
public class ExpenseController {

    private final ExpenseService expenseService; // Servicio para obtener los gastos
    private final ModelMapper modelMapper; // Objeto que realiza la conversión entre DTOs y entidades

    /**
     * It will fetch the expenses from database
     * @return list
     * */
    @GetMapping("/expenses") // Define el punto de acceso HTTP GET a /expenses
    public List<ExpenseResponse> getExpenses(){
        log.info("API GET /expenses called");

        // Llama al servicio para obtener todos los gastos en forma de DTO
        List<ExpenseDTO> list = expenseService.getAllExpenses();
        log.info("Printing the data from service {}",list);

        // Convierte la lista de ExpenseDTO a ExpenseResponse usando modelMapper
        List<ExpenseResponse> response = list.stream()
                .map(expenseDTO -> mapToExpenseResponse(expenseDTO)) // Convierte cada DTO a Response
                .collect(Collectors.toList()); // Recoge los resultados en una lista

        // Devuelve la lista de respuestas al cliente
        return response;
    }

    /**
     * Mapper method for converting expense dto object to expense response object
     * @param expenseDTO
     * @return ExpenseResponse
     * */
    private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO){
        // Usando ModelMapper para convertir entre tipos
        return modelMapper.map(expenseDTO, ExpenseResponse.class);
    }
}
