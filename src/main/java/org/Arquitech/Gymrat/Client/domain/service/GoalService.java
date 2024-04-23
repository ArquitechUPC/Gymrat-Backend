package org.Arquitech.Gymrat.Client.domain.service;

import org.Arquitech.Gymrat.Client.domain.model.entity.Goal;
import org.Arquitech.Gymrat.Client.domain.model.entity.Measurement;

import java.util.List;
import java.util.Optional;

public interface GoalService {
    List<Goal> fetchAll();
    Optional<Goal> fetchById(Integer Id);
    Goal save(Goal goal, Measurement measurement);
    Goal update(Goal goal, Measurement measurement);
    boolean deleteById(Integer id);
}
