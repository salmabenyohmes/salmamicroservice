package com.esprit.microservice.reservation.Services;
import com.esprit.microservice.reservation.Repositories.ReservationRepository;
import com.esprit.microservice.reservation.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservation(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.orElse(null);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        if (reservationRepository.existsById(reservation.getId())) {
            return reservationRepository.save(reservation);
        }
        return null;
    }

    @Override
    public void deleteReservation(long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> searchByLieuDepart(String lieuDepart) {
        return reservationRepository.findByLieuDepartContainingIgnoreCase(lieuDepart);
    }

    @Override
    public List<Reservation> searchByLieuArrivee(String lieuArrivee) {
        return reservationRepository.findByLieuArriveeContainingIgnoreCase(lieuArrivee);
    }
}