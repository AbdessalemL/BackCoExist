package tn.esprit.coexist.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.coexist.entity.Evaluation;
import tn.esprit.coexist.entity.Event;
import tn.esprit.coexist.repository.EvaluationRepository;
import tn.esprit.coexist.repository.EventRepository;

import java.util.List;

@Service
public class EvaluationServiceImp implements EvaluationService{

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    EventRepository eventRepository;


    @Override
    public Evaluation addEvaluationAndAssignToEvent(Evaluation evaluation, Integer eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        evaluation.setEvent(event);
        return evaluationRepository.save(evaluation);
    }

    @Override
    public void deleteEvaluationById(Integer IdEvaluation) {
        evaluationRepository.deleteById(IdEvaluation);
    }

    @Override
    public Evaluation updateEvaluation(Evaluation evaluation, Integer IdEvaluation) {
        Evaluation existingEvaluation = evaluationRepository.findById(IdEvaluation).orElse(null);
        if (existingEvaluation != null) {
            existingEvaluation.setEventPosition(evaluation.getEventPosition());
            return evaluationRepository.save(existingEvaluation);
        }
        return null;
    }

    @Override
    public List<Evaluation> retrieveAllEvaluation() {
        List<Evaluation> ListEvaluation = evaluationRepository.findAll();
        return ListEvaluation;
    }


}
