package com.upc.mjoc.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MjocProducerDto {
    private Long ocProducerId;
    private String ocName;
    private Double ocBudget;
    private LocalDate ocCreationDate;
    private String ocRuc;
    private String ocEmail;
}
