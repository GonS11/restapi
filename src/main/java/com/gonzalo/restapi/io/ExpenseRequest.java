package com.gonzalo.restapi.io;

import lombok.AllArgsConstructor;  // Constructor con todos los parámetros
import lombok.Builder;            // Permite usar el patrón Builder
import lombok.Data;              // Genera getters, setters, toString(), equals(), hashCode()
import lombok.NoArgsConstructor; // Constructor sin parámetros

import java.math.BigDecimal;  // Para manejar montos con precisión
import java.sql.Date;        // Para trabajar con fechas en SQL

// Representa una solicitud de gasto
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseRequest {

    private String name;        // Nombre del gasto (ej. "Alquiler")
    private String description; // Descripción del gasto
    private String category;    // Categoría del gasto (ej. "Vivienda")
    private Date date;          // Fecha del gasto
    private BigDecimal amount;  // Monto del gasto
}
