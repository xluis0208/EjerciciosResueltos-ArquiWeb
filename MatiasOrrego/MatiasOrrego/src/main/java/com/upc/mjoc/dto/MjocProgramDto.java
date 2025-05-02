package com.upc.mjoc.dto;

import lombok.Data;

@Data
public class MjocProgramDto {
    private String ocName;
    private String ocType;
    private String ocBroadcastDay;
    private String ocBroadcastTime;
    private Long ocProducerId;
}
