package tn.esprit.coexist.controller.EventController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.coexist.entity.Evaluation;
import tn.esprit.coexist.service.EventService.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "**")
@AllArgsConstructor
public class EvaluationController {
    @Autowired
    EvaluationService evaluationService;

    @PostMapping("/addEvaluationAndAssignToEvent/{eventId}")
    public Evaluation addEvaluationAndAssignToEvent(
            @RequestBody Evaluation evaluation,
            @PathVariable Integer eventId
    ) {
        return evaluationService.addEvaluationAndAssignToEvent(evaluation, eventId);
    }

    @DeleteMapping("/deleteEvaluationById/{evaluationId}")
    public void deleteEvaluationById(@PathVariable Integer evaluationId) {
        evaluationService.deleteEvaluationById(evaluationId);
    }

    @PutMapping("/updateEvaluation/{evaluationId}")
    public Evaluation updateEvaluation(
            @RequestBody Evaluation evaluation,
            @PathVariable Integer evaluationId
    ) {
        return evaluationService.updateEvaluation(evaluation, evaluationId);
    }

    @GetMapping("/retrieveAllEvaluation")
    public List<Evaluation> retrieveAllEvaluation() {
        return evaluationService.retrieveAllEvaluation();
    }
}
