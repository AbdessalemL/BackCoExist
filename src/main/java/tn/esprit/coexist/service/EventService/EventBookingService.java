package tn.esprit.coexist.service.EventService;

import tn.esprit.coexist.entity.EventBooking;

import java.util.List;

public interface EventBookingService {
    public EventBooking addEBooking (EventBooking eventBooking);


    public List<EventBooking> getALLBooking();

    public void deleteEventBookingById(Integer bookingEventId);
}
