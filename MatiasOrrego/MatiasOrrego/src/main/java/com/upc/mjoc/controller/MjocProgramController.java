package com.upc.mjoc.controller;

import com.upc.mjoc.dto.MjocProgramDto;
import com.upc.mjoc.service.MjocProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orregocespedes")
@RequiredArgsConstructor
public class MjocProgramController {

    private final MjocProgramService programService;

    @PostMapping("/matiasjoaquin/save-program")
    public MjocProgramDto saveProgram(@RequestBody MjocProgramDto dto) {
        return programService.saveProgram(dto);
    }

    @GetMapping("/matiasjoaquin/list-by-producer")
    public List<MjocProgramDto> listByProducer(@RequestParam String producerName) {
        return programService.listProgramsByProducerName(producerName);
    }

    @GetMapping("/matiasjoaquin/count-infant-programs")
    public long countInfantPrograms() {
        return programService.countInfantPrograms();
    }

    @GetMapping("/matiasjoaquin/total-budget")
    public double getTotalBudget() {
        return programService.getTotalBudget();
    }
}
