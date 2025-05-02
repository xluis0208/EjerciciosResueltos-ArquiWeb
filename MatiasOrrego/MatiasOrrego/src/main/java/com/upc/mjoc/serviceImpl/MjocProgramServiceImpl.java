package com.upc.mjoc.serviceImpl;

import com.upc.mjoc.dto.MjocProgramDto;
import com.upc.mjoc.model.MjocProducer;
import com.upc.mjoc.model.MjocProgram;
import com.upc.mjoc.repository.MjocProducerRepository;
import com.upc.mjoc.repository.MjocProgramRepository;
import com.upc.mjoc.service.MjocProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MjocProgramServiceImpl implements MjocProgramService {

    private final MjocProgramRepository programRepository;
    private final MjocProducerRepository producerRepository;

    @Override
    public MjocProgramDto saveProgram(MjocProgramDto dto) {
        MjocProgram program = new MjocProgram();
        program.setOcName(dto.getOcName());
        program.setOcType(dto.getOcType());
        program.setOcBroadcastDay(dto.getOcBroadcastDay());
        program.setOcBroadcastTime(dto.getOcBroadcastTime());

        MjocProducer producer = producerRepository.findById(dto.getOcProducerId())
                .orElseThrow(() -> new RuntimeException("Producer not found"));
        program.setProducer(producer);

        programRepository.save(program);
        return dto;
    }

    @Override
    public List<MjocProgramDto> listProgramsByProducerName(String ocName) {
        return programRepository.findByProducer_OcName(ocName).stream().map(program -> {
            MjocProgramDto dto = new MjocProgramDto();
            dto.setOcName(program.getOcName());
            dto.setOcType(program.getOcType());
            dto.setOcBroadcastDay(program.getOcBroadcastDay());
            dto.setOcBroadcastTime(program.getOcBroadcastTime());
            dto.setOcProducerId(program.getProducer().getOcProducerId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public long countInfantPrograms() {
        return programRepository.countByOcTypeIgnoreCase("Infantil");
    }

    @Override
    public double getTotalBudget() {
        return producerRepository.findAll().stream()
                .mapToDouble(MjocProducer::getOcBudget)
                .sum();
    }
}
