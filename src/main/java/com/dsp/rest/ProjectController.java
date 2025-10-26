package com.dsp.rest;

import com.dsp.dto.ProjectDTO;
import com.dsp.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProjectDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProjectDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/clientLocation/{clientLocationId}")
    public List<ProjectDTO> getByClientLocation(@PathVariable Long clientLocationId) {
        return service.getByClientLocation(clientLocationId);
    }

    @PostMapping
    public ProjectDTO create(@RequestBody ProjectDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public ProjectDTO update(@RequestBody ProjectDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
