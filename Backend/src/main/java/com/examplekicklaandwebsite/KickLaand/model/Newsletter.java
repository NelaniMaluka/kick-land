package com.examplekicklaandwebsite.KickLaand.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int newsletterId;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    @Column(unique = true)
    private String email;

}
