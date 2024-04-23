package org.Arquitech.Gymrat.Client.service;

import jakarta.validation.ConstraintViolation;
import org.Arquitech.Gymrat.Authentication.domain.model.entity.User;
import org.Arquitech.Gymrat.Authentication.domain.persistence.UserRepository;
import org.Arquitech.Gymrat.Client.domain.model.entity.Client;
import org.Arquitech.Gymrat.Client.domain.model.entity.Plan;
import org.Arquitech.Gymrat.Client.domain.persistence.ClientRepository;
import org.Arquitech.Gymrat.Client.domain.persistence.GoalRepository;
import org.Arquitech.Gymrat.Client.domain.persistence.MeasurementRepository;
import org.Arquitech.Gymrat.Client.domain.persistence.PlanRepository;
import org.Arquitech.Gymrat.Client.domain.service.ClientService;
import org.Arquitech.Gymrat.Shared.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.validation.Validator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private Validator validator;

    public ClientServiceImpl(ClientRepository clientRepository, UserRepository userRepository, GoalRepository goalRepository, MeasurementRepository measurementRepository, Validator validator) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
        this.measurementRepository = measurementRepository;
        this.validator = validator;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Client> fetchAll() {
        return clientRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Client> fetchById(Integer Id) {
        if (clientRepository.existsById(Id)) {
            return clientRepository.findById(Id);
        } else {
            throw new CustomException("El cliente no existe",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Client save(Client client) {
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        if (!violations.isEmpty()) {
            throw new CustomException("Error",HttpStatus.NOT_FOUND);
        }

        User user = userRepository.findById(client.getUserId())
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));


        return clientRepository.save(client);
    }

    @Override
    public Client updatePlan(Client client, Integer planId) {
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        if (!violations.isEmpty()) {
            throw new CustomException("Error", HttpStatus.NOT_FOUND);
        }

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new CustomException("Plan not found", HttpStatus.NOT_FOUND));

        client.setPlan_id(planId);

        return clientRepository.save(client);

    }

    @Override
    public boolean deleteById(Integer id) {
        var clientToDelete = clientRepository.findById(id).orElseThrow(() -> new CustomException("Error",HttpStatus.NOT_FOUND));

        clientRepository.delete(clientToDelete);
        return true;
    }
}
