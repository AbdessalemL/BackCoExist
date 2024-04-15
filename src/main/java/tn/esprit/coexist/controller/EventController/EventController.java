package tn.esprit.coexist.controller.EventController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.coexist.entity.Event;
import tn.esprit.coexist.entity.LikedEvents;
import tn.esprit.coexist.entity.User;
import tn.esprit.coexist.service.EventService.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "**")

@AllArgsConstructor
public class EventController {
    @Autowired
   EventService eventService;


    @PostMapping("/add-event")

    public ResponseEntity<Event> addEvent(
            @RequestParam("image") MultipartFile imageFile,
            @ModelAttribute Event event) {
        try {
            Event savedEvent = eventService.AddEvent(event, imageFile);
            return ResponseEntity.ok(savedEvent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/updateEvent/{eventId}")
    public Event updateEvent(@RequestBody Event event, @PathVariable Integer eventId) {
        return eventService.updateEvent(event, eventId);
    }

    @GetMapping("/findByIdEvent/{eventId}")
    public Event findById(@PathVariable Integer eventId) {
        return eventService.findById(eventId);
    }

    @GetMapping("/retrieveAllEvents")
    public List<Event> retrieveAllEvents() {
        List<Event> events = eventService.retrieveAllEvents();
        String baseUrl = "http://localhost:8000/images/"; // Change this to your actual base URL
        events.forEach(event -> {
            String relativeImageUrl = event.getImages();
            String fullImageUrl = baseUrl + relativeImageUrl;
            event.setImages(fullImageUrl);
        });
        return events;
    }
    @GetMapping("recherche/{keyword}")
    public List<Event> recherche(@PathVariable("keyword") String keyword) {
        return eventService.recherche (keyword);
    }
    @PostMapping("/addLikeToEvent/{eventId}")
    public LikedEvents addLikeToEvent(
            @RequestBody LikedEvents likedEvents,
            @PathVariable Integer eventId
            ) {
        return eventService.addLikeToEvent(likedEvents, eventId);
    }

    @DeleteMapping("/removeLikeFromEvent/{eventId}")
    public String removeLikeFromEvent(
            @PathVariable Integer eventId
            ) {
        eventService.removeLikeFromEvent(eventId);
        return "Like removed successfully";
    }

    @DeleteMapping("/deleteEventById/{eventId}")
    public String deleteEventById(@PathVariable Integer eventId) {
        eventService.deleteEventById(eventId);
        return "Event deleted successfully";
    }
}
