package org.Arquitech.Gymrat.Client.domain.persistence;

import org.Arquitech.Gymrat.Client.domain.model.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {
    List<Goal> findByName(String name);
}
