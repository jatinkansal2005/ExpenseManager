package com.ltp.expense_manager.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "expense")
@RequiredArgsConstructor
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long expenseId;

    @NonNull
    @NotBlank(message = "Title cannot be blank")
    @Column(name = "title")
    String title;

    @NonNull
    @Past(message = "Date cannot be in future")
    @Column(name = "amount_date")
    Date amountDate;

    @NonNull
    @NotBlank(message = "Expense must have a type")
    @Column(name = "type")
    String type;

    public Long getExpenseId() {
        return this.expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getAmountDate() {
        return this.amountDate;
    }

    public void setAmountDate(Date amountDate) {
        this.amountDate = amountDate;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
