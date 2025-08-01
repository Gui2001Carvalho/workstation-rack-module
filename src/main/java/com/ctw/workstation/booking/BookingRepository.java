package com.ctw.workstation.booking;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.rack.entity.Rack;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class BookingRepository implements PanacheRepositoryBase<Booking, UUID> {
    public void addBooking(Booking booking) {
        persist(booking);
    }

    public void removeBooking(UUID id) {
        Booking booking = findById(id);
        if (booking != null) {
            delete(booking);
        }
    }

    public List<Booking> getBookings() {
        return listAll();
    }
}