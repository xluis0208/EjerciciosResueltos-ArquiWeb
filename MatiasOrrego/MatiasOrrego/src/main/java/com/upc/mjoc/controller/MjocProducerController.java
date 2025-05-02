package com.upc.mjoc.controller;

import com.upc.mjoc.dto.MjocProducerDto;
import com.upc.mjoc.service.MjocProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orregocespedes")
@RequiredArgsConstructor
public class MjocProducerController {
    @Autowired
    private final MjocProducerService mjocProducerService;



    @PostMapping("/matiasjoaquin/save-producer")
    public MjocProducerDto saveProducer(@RequestBody MjocProducerDto dto) {
        return producerService.saveProducer(dto);
    }
}

