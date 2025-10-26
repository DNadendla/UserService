package com.dsp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private LocalDate dateOfStart;
    private int teamSize;
    private boolean active;
    private String status;
    private ClientLocationDTO clientLocation;
}