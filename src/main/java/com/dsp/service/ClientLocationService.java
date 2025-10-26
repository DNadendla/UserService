package com.dsp.service;

import com.dsp.dto.ClientLocationDTO;
import com.dsp.entity.ClientLocation;
import com.dsp.repo.ClientLocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientLocationService {

    private final ClientLocationRepository repo;

    public ClientLocationService(ClientLocationRepository repo) {
        this.repo = repo;
    }

    public List<ClientLocationDTO> getAll() {
        return repo.findAll().stream()
                .map(c -> new ClientLocationDTO(c.getId(), c.getName()))
                .collect(Collectors.toList());
    }

    public ClientLocationDTO getById(Long id) {
        ClientLocation c = repo.findById(id).orElseThrow();
        return new ClientLocationDTO(c.getId(), c.getName());
    }

    public ClientLocationDTO create(ClientLocationDTO dto) {
        ClientLocation clientLocation = new ClientLocation();
        clientLocation.setName(dto.getName());
        clientLocation = repo.save(clientLocation);
        return new ClientLocationDTO(clientLocation.getId(), clientLocation.getName());
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
