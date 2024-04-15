package tn.esprit.coexist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.coexist.entity.LikedEvents;

import java.util.List;

@Repository
public interface LikedEventsRepository extends JpaRepository<LikedEvents,Integer> {


    LikedEvents findFirstByEvent_EventId(Integer eventId);


}
