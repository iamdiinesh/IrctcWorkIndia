package com.example.IrctcWorkIndia.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_Id")
    private Long userId;

    @Column(name = "train_Id")
    private Long trainId;

    @Column(name = "booking_date")
    private Date bookingDate;

    @Column(name = "seats_booked")
    private int seatsBooked;


}

