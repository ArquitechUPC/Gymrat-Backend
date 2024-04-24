package org.Arquitech.Gymrat.Client.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("clientMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ClientMapper  clientMapper(){ return new ClientMapper(); }

    @Bean
    public PlanMapper planMapper(){ return new PlanMapper(); }

    @Bean
    public MeasurementMapper measurementMapper(){ return new MeasurementMapper(); }

    @Bean
    public GoalMapper goalMapper(){ return new GoalMapper(); }

}
