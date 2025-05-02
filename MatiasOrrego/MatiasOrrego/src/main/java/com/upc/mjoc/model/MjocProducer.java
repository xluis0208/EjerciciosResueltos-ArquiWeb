package com.upc.mjoc.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class MjocProducer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ocProducerId;

    @Column(unique = true)
    private String ocName;

    private Double ocBudget;
    private LocalDate ocCreationDate;
    private String ocRuc;
    private String ocEmail;
}
