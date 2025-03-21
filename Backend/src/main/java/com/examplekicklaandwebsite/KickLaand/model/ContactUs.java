package com.examplekicklaandwebsite.KickLaand.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContactUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    private String name;

    @Column(nullable = false)
    @NotNull(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "(\\+27|0)[0-9]{9}", message = "Please provide a valid South African phone number")
    private String phoneNumber;

    @Column(nullable = false)
    @NotNull(message = "Message is required")
    private String message;

}
