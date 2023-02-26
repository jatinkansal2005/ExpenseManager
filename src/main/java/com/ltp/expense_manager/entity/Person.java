package com.ltp.expense_manager.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Id;
import lombok.*;
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Person")
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
    @Column(name = "email_id",nullable = false)
    private String emailId;

    @NonNull
    @NotBlank(message = "Password Can't be Blank")
    @Column(name = "password", nullable = false)
    private String password;

    @NonNull
    @NotBlank(message = "Age Can't be Blank")
    @Column(name = "age", nullable = false)
    private Long age;
}
