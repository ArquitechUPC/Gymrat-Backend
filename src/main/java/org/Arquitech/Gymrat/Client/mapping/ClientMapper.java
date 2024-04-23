package org.Arquitech.Gymrat.Client.mapping;

import org.Arquitech.Gymrat.Client.domain.model.entity.Client;
import org.Arquitech.Gymrat.Client.resource.client.ClientResource;
import org.Arquitech.Gymrat.Client.resource.client.CreateClientResource;
import org.Arquitech.Gymrat.Client.resource.client.UpdateClientResource;
import org.Arquitech.Gymrat.Shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class ClientMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public Client toModel(CreateClientResource resource){ return this.mapper.map(resource, Client.class);}
    public Client toModel(UpdateClientResource resource){ return this.mapper.map(resource, Client.class);}
    public ClientResource toResource(Client client){ return this.mapper.map(client, ClientResource.class);}

}
