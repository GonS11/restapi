package com.gonzalo.restapi.repository;

import com.gonzalo.restapi.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JPA repository for Expense resource
 * @author gonzalo
 * */
public interface ExpenseRepository extends JpaRepository<ExpenseEntity,Long> {

    /**
     * It wil find the single expense from database
     * @param expenseId
     * @return Optional
     * */
    Optional<ExpenseEntity> findByExpenseId(String expenseId);
}
