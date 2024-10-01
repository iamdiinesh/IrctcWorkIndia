package com.example.IrctcWorkIndia.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "source")
    private String source;

    @Column(name = "destination")
    private String destination;

    @Column(name = "total_seats", columnDefinition = "Integer")
    private Integer totalSeats;

    @Column(name = "available_seats", columnDefinition = "Integer")
    private Integer availableSeats;

}

