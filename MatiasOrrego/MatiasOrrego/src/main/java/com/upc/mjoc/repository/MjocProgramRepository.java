package com.upc.mjoc.repository;

import com.upc.mjoc.model.MjocProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MjocProgramRepository extends JpaRepository<MjocProgram, Long> {
    List<MjocProgram> findByProducer_OcName(String ocName);
    long countByOcTypeIgnoreCase(String ocType);
}
