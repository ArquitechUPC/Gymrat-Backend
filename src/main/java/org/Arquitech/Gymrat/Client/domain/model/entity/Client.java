package org.Arquitech.Gymrat.Client.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name = "clients")
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer plan_id;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private List<Goal> goals;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Measurement> measurements;
}
