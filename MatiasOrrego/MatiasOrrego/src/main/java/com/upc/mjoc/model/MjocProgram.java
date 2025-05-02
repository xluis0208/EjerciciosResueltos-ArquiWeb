package com.upc.mjoc.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MjocProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ocProgramId;

    private String ocName;
    private String ocType;
    private String ocBroadcastDay;
    private String ocBroadcastTime;

    @ManyToOne
    @JoinColumn(name = "ocProducerId")
    private MjocProducer producer;
}
