package com.gonzalo.restapi.service.impl;

import com.gonzalo.restapi.dto.ExpenseDTO;           // Importa la clase DTO para representar los gastos
import com.gonzalo.restapi.entity.ExpenseEntity;     // Importa la entidad para interactuar con la base de datos
import com.gonzalo.restapi.repository.ExpenseRepository;  // Importa el repositorio para acceder a la base de datos
import com.gonzalo.restapi.service.ExpenseService;   // Interfaz que define los métodos del servicio
import lombok.RequiredArgsConstructor;              // Genera un constructor con los campos final
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;                  // Librería para mapear objetos entre entidades y DTOs
import org.springframework.stereotype.Service;           // Marca la clase como un servicio de Spring

import java.util.List;                               // Importa la clase List para manejar colecciones
import java.util.stream.Collectors;                  // Herramientas para trabajar con streams

/**
 * Service implementation for Expense module
 * @author Gonzalo
 * */
@Service                                        // Define la clase como un servicio de Spring
@RequiredArgsConstructor                        // Genera un constructor con los parámetros final
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;  // Repositorio para acceder a la base de datos
    private final ModelMapper modelMapper;              // Instancia de ModelMapper para convertir entidades a DTOs

    /**
     * It wil fetch the expenses from database
     * @return list
     * */
    @Override
    public List<ExpenseDTO> getAllExpenses() {
        // Llama al metodo del repositorio para obtener todos los gastos
        List<ExpenseEntity> list = expenseRepository.findAll();
        log.info("Printing the data from repository {}",list);

        // Convierte la lista de entidades a una lista de DTOs
        List<ExpenseDTO> listOfExpenses = list.stream()
                .map(expenseEntity -> mapToExpenseDTO(expenseEntity))
                .collect(Collectors.toList());

        // Devuelve la lista de DTOs
        return listOfExpenses;
    }

    /**
     * Mapper method to convert expense entity to expense DTO
     * @param expenseEntity
     * @return ExpenseDTO
     * */
    private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity, ExpenseDTO.class);  // Usa ModelMapper para la conversión
    }
}

