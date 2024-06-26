package org.Arquitech.Gymrat.Client.resource.measurement;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateMeasurementResource {

    @NotNull
    private Double weight;

    private String comment;

    private Double chestCircumference;

    private Double waistCircumference;

    private Double hipCircumference;

    private Double armCircumference;

    private Double legCircumference;

}
