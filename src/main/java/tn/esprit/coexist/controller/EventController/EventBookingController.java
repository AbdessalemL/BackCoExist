package tn.esprit.coexist.controller.EventController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.coexist.entity.EventBooking;
import tn.esprit.coexist.service.EventService.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class EventBookingController {
    @Autowired
    EventBookingService eventBookingService;

    @PostMapping("/addEventBooking")
    public EventBooking addEventBooking(@RequestBody EventBooking eventBooking) {
        return eventBookingService.addEBooking(eventBooking);
    }

    @GetMapping("/getAllEventBookings")
    public List<EventBooking> getAllEventBookings() {
        return eventBookingService.getALLBooking();
    }

    @DeleteMapping("/deleteEventBooking/{bookingEventId}")
    public String deleteEventBookingById(@PathVariable Integer bookingEventId) {
        eventBookingService.deleteEventBookingById(bookingEventId);
        return "Event Booking deleted successfully";
    }
}
