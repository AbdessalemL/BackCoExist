package tn.esprit.coexist.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.coexist.entity.EventBooking;
import tn.esprit.coexist.repository.EventBookingRepository;

import java.util.List;

@Service
public class EventBookingServiceImp implements EventBookingService{
    @Autowired
    EventBookingRepository eventBookingRepository;

    @Override
    public EventBooking addEBooking(EventBooking eventBooking) {
        return eventBookingRepository.save(eventBooking); }

    @Override
    public List<EventBooking> getALLBooking() {
        return eventBookingRepository.findAll();
    }

    @Override
    public void deleteEventBookingById(Integer bookingEventId) {
        eventBookingRepository.deleteById(bookingEventId);
    }



}
