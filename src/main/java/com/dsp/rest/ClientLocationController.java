package com.dsp.rest;

import com.dsp.dto.ClientLocationDTO;
import com.dsp.service.ClientLocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientLocation")
public class ClientLocationController {

    private final ClientLocationService service;

    public ClientLocationController(ClientLocationService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClientLocationDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ClientLocationDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ClientLocationDTO create(@RequestBody ClientLocationDTO dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
