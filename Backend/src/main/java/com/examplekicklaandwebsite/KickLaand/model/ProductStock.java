package com.examplekicklaandwebsite.KickLaand.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor // For JPA
@AllArgsConstructor // For Builder
@Data
public class ProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;

    @Column(nullable = false)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

    @Column(nullable = false)
    @NotNull(message = "Size3 must have a value")
    @Min(value = 0, message = "Size3 cannot go below 0")
    private Integer Size3;

    @Column(nullable = false)
    @NotNull(message = "Size4 must have a value")
    @Min(value = 0, message = "Size4 cannot go below 0")
    private Integer Size4;

    @Column(nullable = false)
    @NotNull(message = "Size5 must have a value")
    @Min(value = 0, message = "Size5 cannot go below 0")
    private Integer Size5;

    @Column(nullable = false)
    @NotNull(message = "Size6 must have a value")
    @Min(value = 0, message = "Size6 cannot go below 0")
    private Integer Size6;

    @Column(nullable = false)
    @NotNull(message = "Size7 must have a value")
    @Min(value = 0, message = "Size7 cannot go below 0")
    private Integer Size7;

    @Column(nullable = false)
    @NotNull(message = "Size8 must have a value")
    @Min(value = 0, message = "Size8 cannot go below 0")
    private Integer Size8;

    @Column(nullable = false)
    @NotNull(message = "Size9 must have a value")
    @Min(value = 0, message = "Size9 cannot go below 0")
    private Integer Size9;

    @Column(nullable = false)
    @NotNull(message = "Size10 must have a value")
    @Min(value = 0, message = "Size10 cannot go below 0")
    private Integer Size10;

    @Column(nullable = false)
    @NotNull(message = "Size11 must have a value")
    @Min(value = 0, message = "Size11 cannot go below 0")
    private Integer Size11;

    @Column(nullable = false)
    @NotNull(message = "Size12 must have a value")
    @Min(value = 0, message = "Size12 cannot go below 0")
    private Integer Size12;

}
