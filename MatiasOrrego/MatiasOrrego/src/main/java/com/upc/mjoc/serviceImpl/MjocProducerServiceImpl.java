package com.upc.mjoc.serviceImpl;

import com.upc.mjoc.dto.MjocProducerDto;
import com.upc.mjoc.model.MjocProducer;
import com.upc.mjoc.repository.MjocProducerRepository;
import com.upc.mjoc.service.MjocProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MjocProducerServiceImpl implements MjocProducerService {

    private final MjocProducerRepository producerRepository;

    @Override
    public MjocProducerDto saveProducer(MjocProducerDto dto) {
        MjocProducer producer = new MjocProducer();
        producer.setOcName(dto.getOcName());
        producer.setOcBudget(dto.getOcBudget());
        producer.setOcCreationDate(dto.getOcCreationDate());
        producer.setOcRuc(dto.getOcRuc());
        producer.setOcEmail(dto.getOcEmail());

        producer = producerRepository.save(producer);
        dto.setOcProducerId(producer.getOcProducerId());
        return dto;
    }
}
