package org.Arquitech.Gymrat.Client.resource.plan;

import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class PlanResource {
    private Integer id;

    private String name;

    private String description;

    private Double cost;

    private Integer duration;
}
