package org.Arquitech.Gymrat.Client.mapping;

import org.Arquitech.Gymrat.Client.domain.model.entity.Measurement;
import org.Arquitech.Gymrat.Client.resource.measurement.CreateMeasurementResource;
import org.Arquitech.Gymrat.Client.resource.measurement.MeasurementResource;
import org.Arquitech.Gymrat.Client.resource.measurement.UpdateMeasurementResource;
import org.Arquitech.Gymrat.Shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class MeasurementMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public Measurement toModel(CreateMeasurementResource resource){ return this.mapper.map(resource, Measurement.class);}
    public Measurement toModel(UpdateMeasurementResource resource){ return this.mapper.map(resource, Measurement.class);}
    public MeasurementResource toResource(Measurement measurement){ return this.mapper.map(measurement, MeasurementResource.class);}

}
