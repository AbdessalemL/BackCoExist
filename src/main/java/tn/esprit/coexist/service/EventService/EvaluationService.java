package tn.esprit.coexist.service.EventService;

import tn.esprit.coexist.entity.Evaluation;

import java.util.List;

public interface EvaluationService {

    public Evaluation addEvaluationAndAssignToEvent(Evaluation evaluation , Integer eventId);

    void deleteEvaluationById(Integer IdEvaluation);


    public Evaluation updateEvaluation(Evaluation evaluation , Integer eventId);

    public List<Evaluation> retrieveAllEvaluation();



}
