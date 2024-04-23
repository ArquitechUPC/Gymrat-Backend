package org.Arquitech.Gymrat.Client.resource.client;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientResource {
    @NotNull
    private Integer userId;

    @NotNull
    private Integer givenPlanId;

}
