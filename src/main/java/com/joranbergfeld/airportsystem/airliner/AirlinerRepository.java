package com.joranbergfeld.airportsystem.airliner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirlinerRepository extends JpaRepository<Airliner, Long> {

    List<Airliner> findAllByActiveTrue();
}
