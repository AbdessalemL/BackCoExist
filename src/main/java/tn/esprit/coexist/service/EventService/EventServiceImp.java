package tn.esprit.coexist.service.EventService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.coexist.entity.Event;
import tn.esprit.coexist.entity.LikedEvents;
import tn.esprit.coexist.entity.User;
import tn.esprit.coexist.repository.EventRepository;
import tn.esprit.coexist.repository.LikedEventsRepository;
import tn.esprit.coexist.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImp implements EventService{
    public static final String uploadDirectory = "C:/xampp/htdocs/images/";


    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LikedEventsRepository likedEventsRepository;


    @Override
    public Event AddEvent(Event event, MultipartFile imageFile) {
        try {
            Path directoryPath = Paths.get(uploadDirectory);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            String originalFilename = imageFile.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + originalFilename;

            Path filePath = Paths.get(uploadDirectory, fileName);

            Files.write(filePath, imageFile.getBytes());

            event.setImages(fileName);

            return eventRepository.save(event);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload image");
        }
    }


    @Override
    public Event updateEvent(Event event, Integer eventId) {
        event.setEventId(eventId);
        return eventRepository.save(event);
    }

    @Override
    public Event findById(Integer eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    @Override
    public List<Event> retrieveAllEvents() {
        List<Event> listEvents = eventRepository.findAll();
        return listEvents;
    }

    @Override
    public LikedEvents addLikeToEvent(LikedEvents likedEvents, Integer eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);


        if (event != null ) {
            // set properties
            likedEvents.setEvent(event);

            likedEvents.setLikedAt(new Date()); // Set the likedAt
            likedEvents.setIsLiked(true); // set it as liked

            // save
            return likedEventsRepository.save(likedEvents);
        } else {
            // if user or event not found
            throw new IllegalArgumentException("Event or User not found with provided IDs");
        }
    }

    @Override
    public void removeLikeFromEvent(Integer eventId) {
        // tlawej aal liked events bl user walla bl event id
        LikedEvents likedEvent = likedEventsRepository.findFirstByEvent_EventId(eventId);

        if (likedEvent != null) {
            // Delete the retrieved liked event
            likedEventsRepository.delete(likedEvent);
        } else {
            throw new IllegalArgumentException("LikedEvent not found with provided Event ID");
        }
    }

    @Override
    public void deleteEventById(Integer eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> recherche(String keyword) {
        if (keyword != null) {
            return eventRepository.recherche(keyword);
        } else {
            return eventRepository.findAll();
        }
    }
    }




