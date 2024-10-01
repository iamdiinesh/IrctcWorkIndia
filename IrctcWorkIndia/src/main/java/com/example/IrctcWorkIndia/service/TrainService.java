package com.example.IrctcWorkIndia.service;

import com.example.IrctcWorkIndia.entity.Booking;
import com.example.IrctcWorkIndia.entity.BookingRequest;
import com.example.IrctcWorkIndia.entity.Train;
import com.example.IrctcWorkIndia.repository.BookingRepository;
import com.example.IrctcWorkIndia.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public List<Train> getTrainAvailability(String source, String destination) {
        List<Train> availableTrains = trainRepository.findBySourceAndDestination(source, destination);

        if (availableTrains.isEmpty()) {
            throw new RuntimeException("No trains available for this route.");
        }

        return availableTrains;
    }

    public String bookSeat(BookingRequest bookingRequest) {
        Optional<Train> trainOpt = trainRepository.findById(bookingRequest.getTrainId());

        if (trainOpt.isPresent()) {
            Train train = trainOpt.get();
            if (train.getAvailableSeats() >= bookingRequest.getNumberOfSeats()) {
                Booking booking = new Booking();
                booking.setTrainId(train.getId());
                booking.setBookingDate(bookingRequest.getBookingdate());
                booking.setUserId(bookingRequest.getUserId());
                booking.setSeatsBooked(bookingRequest.getNumberOfSeats());

                bookingRepository.save(booking);

                train.setAvailableSeats(train.getAvailableSeats() - bookingRequest.getNumberOfSeats());
                trainRepository.save(train);

                return "Booking successful!";
            } else {
                return "Not enough seats available.";
            }
        } else {
            return "Train not found.";
        }
    }

    public List<Booking> getUserBookings(Long userId) {
        List<Booking> userBookings = bookingRepository.findByUserId(userId);
        if (userBookings.isEmpty()) {
            throw new RuntimeException("No bookings found for user ID: " + userId);
        }
        return userBookings;
    }

    public Train addNewTrain(Train train) {
        return trainRepository.save(train);
    }
}
