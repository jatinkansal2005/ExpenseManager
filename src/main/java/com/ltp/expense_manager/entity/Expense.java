package com.ltp.expense_manager.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

}
