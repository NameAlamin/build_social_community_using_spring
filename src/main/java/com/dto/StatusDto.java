package com.dto;

import lombok.Data;

@Data
public class StatusDto {
    String title;
    String description;
    String privacy;
    String location;
    Long id;
}
