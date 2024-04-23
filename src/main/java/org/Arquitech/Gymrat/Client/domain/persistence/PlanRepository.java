package org.Arquitech.Gymrat.Client.domain.persistence;

import org.Arquitech.Gymrat.Client.domain.model.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

}
