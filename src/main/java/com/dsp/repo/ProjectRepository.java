package com.dsp.repo;

import com.dsp.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByClientLocationId(Long clientLocationId);
}
