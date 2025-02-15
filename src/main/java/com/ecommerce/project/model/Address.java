package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adressId;

    @NotBlank
    @Size(min = 5, message = "Street name must be at least 5 characters")
    private String street;

    @NotBlank
    @Size(min = 5, message = "Building name must be at least 5 characters")
    private String buildingName;

    @NotBlank
    @Size(min = 4, message = "City name must be at least 4 characters")
    private String city;

    @NotBlank
    @Size(min = 2, message = "State name must be at least 4 characters")
    private String state;

    @NotBlank
    @Size(min = 2, message = "Country name must be at least 4 characters")
    private String country;

    @NotBlank
    @Column(name = "pincode")
    @Size(min = 6, message = "Pin code name must be at least 6 characters")
    private String pinCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;





}
