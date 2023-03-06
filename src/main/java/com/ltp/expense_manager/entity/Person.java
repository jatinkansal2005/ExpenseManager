package com.ltp.expense_manager.entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ltp.expense_manager.validation.Age;
import com.ltp.expense_manager.validation.Email;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @NotBlank(message = "Name Can't be Blank")
    @Column(name ="name", nullable = false)
    private String name;

    @NonNull
    @NotBlank(message = "EmailId Can't be Blank")
    @Email
    @Column(name = "email_id",nullable = false, unique = true)
    private String emailId;
    
    @NonNull
    @NotBlank(message = "Password Can't be Blank")
    @Column(name = "password", nullable = false)
    private String password;

    @NonNull
    @NotBlank(message = "Age Can't be Blank")
    @Age
    @Column(name = "age", nullable = false)
    private String age;

    @JsonIgnore
    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    List<Expense> expenses;
}
