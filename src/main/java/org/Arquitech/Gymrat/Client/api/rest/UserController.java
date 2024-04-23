package org.Arquitech.Gymrat.Client.api.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.Arquitech.Gymrat.Client.domain.model.entity.Client;
import org.Arquitech.Gymrat.Client.domain.service.ClientService;
import org.Arquitech.Gymrat.Client.mapping.ClientMapper;
import org.Arquitech.Gymrat.Client.resource.client.ClientResource;
import org.Arquitech.Gymrat.Client.resource.client.CreateClientResource;
import org.Arquitech.Gymrat.Client.resource.client.UpdateClientResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clients", description = "Create, Read, Update and delete clients entities")
@RestController
@RequestMapping("api/v1/clients")
@AllArgsConstructor
public class UserController {

    private final ClientService clientService;
    private final ClientMapper mapper;

    @Operation(summary = "Get all registered clients", responses = {
            @ApiResponse(description = "Successfully fetched all clients",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientResource.class)))
    })
    @GetMapping
    public List<Client> fetchAll() {
        return clientService.fetchAll();
    }


    @Operation(summary = "Get an client by id", responses = {
            @ApiResponse(description = "Successfully fetched client by id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientResource.class)))
    })
    @GetMapping("{id}")
    public ClientResource fetchById(@PathVariable Integer id) {
        return this.mapper.toResource(clientService.fetchById(id).get());
    }


    @Operation(summary = "Save a client", responses = {
            @ApiResponse(description = "Client successfully created",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientResource.class)))
    })
    @PostMapping
    public ClientResource save(@RequestBody CreateClientResource resource){
        return mapper.toResource(clientService.save(mapper.toModel(resource)));
    }

    @Operation(summary = "Update a client's plan", responses = {
            @ApiResponse(description = "Client's plan successfully updated",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientResource.class)))
    })
    @PutMapping("{id}/plan/{planId}")
    public ResponseEntity<ClientResource> updatePlan(@PathVariable Integer id, @PathVariable Integer planId, @RequestBody UpdateClientResource resource) {

        if(id.equals(resource.getId())){
            ClientResource clientResource = mapper.toResource(
            clientService.updatePlan(mapper.toModel(resource), planId));
            return new ResponseEntity<>(clientResource, HttpStatus.OK);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }




}
