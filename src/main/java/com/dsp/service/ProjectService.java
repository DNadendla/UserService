package com.dsp.service;

import com.dsp.dto.ClientLocationDTO;
import com.dsp.dto.ProjectDTO;
import com.dsp.entity.ClientLocation;
import com.dsp.entity.Project;
import com.dsp.repo.ClientLocationRepository;
import com.dsp.repo.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepo;
    private final ClientLocationRepository locationRepo;

    public ProjectService(ProjectRepository projectRepo, ClientLocationRepository locationRepo) {
        this.projectRepo = projectRepo;
        this.locationRepo = locationRepo;
    }

    public List<ProjectDTO> getAll() {
        return projectRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ProjectDTO getById(Long id) {
        Project p = projectRepo.findById(id).orElseThrow();
        return toDTO(p);
    }

    public ProjectDTO create(ProjectDTO dto) {
        ClientLocation clientLocation = locationRepo.findById(dto.getClientLocation().getId())
                .orElseThrow(() -> new RuntimeException("ClientLocation not found"));

        Project project = new Project();
        project.setName(dto.getName());
        project.setDateOfStart(dto.getDateOfStart());
        project.setTeamSize(dto.getTeamSize());
        project.setActive(dto.isActive());
        project.setStatus(dto.getStatus());
        project.setClientLocation(clientLocation);

        project = projectRepo.save(project);
        return toDTO(project);
    }

    public ProjectDTO update(ProjectDTO dto) {
        ClientLocation clientLocation = locationRepo.findById(dto.getClientLocation().getId())
                .orElseThrow(() -> new RuntimeException("ClientLocation not found"));

        Project project = projectRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(dto.getName());
        project.setDateOfStart(dto.getDateOfStart());
        project.setTeamSize(dto.getTeamSize());
        project.setActive(dto.isActive());
        project.setStatus(dto.getStatus());
        project.setClientLocation(clientLocation);

        project = projectRepo.save(project);
        return toDTO(project);
    }


    public void delete(Long id) {
        projectRepo.deleteById(id);
    }

    public List<ProjectDTO> getByClientLocation(Long clientLocationId) {
        return projectRepo.findByClientLocationId(clientLocationId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    private ProjectDTO toDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDateOfStart(project.getDateOfStart());
        dto.setTeamSize(project.getTeamSize());
        dto.setActive(project.isActive());
        dto.setStatus(project.getStatus());
        ClientLocationDTO clientLocationDTO = new ClientLocationDTO(project.getClientLocation().getId(), project.getClientLocation().getName());
        dto.setClientLocation(clientLocationDTO);
        return dto;
    }
}
