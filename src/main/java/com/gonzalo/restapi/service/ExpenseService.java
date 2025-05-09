package com.gonzalo.restapi.service;

import com.gonzalo.restapi.dto.ExpenseDTO;

import java.util.List;

/**
 * Service interface for Expense module
 * @author Gonzalo
 * */
public interface ExpenseService {

    /**
     * It wil fetch the expenses from database
     * @return list
     * */
    List<ExpenseDTO> getAllExpenses();

    /**
     * It wil fetch the single expense details from database
     * @param ExpenseId
     * @return ExpenseDTO
     * */
    ExpenseDTO getExpenseByExpenseId(String ExpenseId);
}
