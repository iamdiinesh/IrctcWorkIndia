package com.example.IrctcWorkIndia.controller;

import com.example.IrctcWorkIndia.entity.Booking;
import com.example.IrctcWorkIndia.entity.BookingRequest;
import com.example.IrctcWorkIndia.entity.Train;
import com.example.IrctcWorkIndia.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {
    @Autowired
    private TrainService trainService;

    @GetMapping("/availability")
    public ResponseEntity<List<Train>> getSeatAvailability(@RequestParam String source, @RequestParam String destination) {
        return ResponseEntity.ok(trainService.getTrainAvailability(source, destination));
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookSeat(@RequestBody BookingRequest bookingRequest) {
        String response = trainService.bookSeat(bookingRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bookings/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long userId) {
        return ResponseEntity.ok(trainService.getUserBookings(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<Train> addNewTrain(@RequestBody Train train) {
        Train createdTrain =    trainService.addNewTrain(train);
        return ResponseEntity.ok(createdTrain);
    }
}
