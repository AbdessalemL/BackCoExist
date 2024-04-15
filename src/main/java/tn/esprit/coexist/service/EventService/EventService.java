package tn.esprit.coexist.service.EventService;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.coexist.entity.Event;
import tn.esprit.coexist.entity.LikedEvents;
import tn.esprit.coexist.entity.User;

import java.util.List;

public interface EventService {




    public Event AddEvent(Event event, MultipartFile imageFile);


    public Event updateEvent(Event event, Integer eventId);


    public Event findById(Integer eventId);

    public List<Event> retrieveAllEvents();

    public LikedEvents addLikeToEvent(LikedEvents likedEvents , Integer eventId);


    public void removeLikeFromEvent(Integer eventId);


    public void deleteEventById(Integer eventId);


    public List<Event> recherche(String keyword);


}
