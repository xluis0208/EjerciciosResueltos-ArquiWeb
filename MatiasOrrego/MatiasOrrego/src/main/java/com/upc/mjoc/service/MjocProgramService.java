package com.upc.mjoc.service;

import com.upc.mjoc.dto.MjocProgramDto;

import java.util.List;

public interface MjocProgramService {
    MjocProgramDto saveProgram(MjocProgramDto dto);
    List<MjocProgramDto> listProgramsByProducerName(String ocName);
    long countInfantPrograms();
    double getTotalBudget();
}
