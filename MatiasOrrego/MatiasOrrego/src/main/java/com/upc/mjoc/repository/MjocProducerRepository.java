package com.upc.mjoc.repository;

import com.upc.mjoc.model.MjocProducer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MjocProducerRepository extends JpaRepository<MjocProducer, Long> {
    MjocProducer findByOcName(String ocName);
}
