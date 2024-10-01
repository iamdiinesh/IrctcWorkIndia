package com.example.IrctcWorkIndia.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookingRequest {
    private Long trainId;
    private Long userId;
    private Integer numberOfSeats;
    private Date bookingdate;

    public BookingRequest() {}

    public BookingRequest(Long trainId, Long userId, Integer numberOfSeats,Date bookingdate) {
        this.trainId = trainId;
        this.userId = userId;
        this.numberOfSeats = numberOfSeats;
        this.bookingdate=bookingdate;
    }

}

