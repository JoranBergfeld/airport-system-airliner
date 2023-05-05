package com.joranbergfeld.airportsystem.airliner;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airliner")
public class AirlinerController {
    private final AirlinerRepository airlinerRepository;
    private final AirlinerService airlinerService;

    public AirlinerController(AirlinerRepository airlinerRepository, AirlinerService airlinerService) {
        this.airlinerRepository = airlinerRepository;
        this.airlinerService = airlinerService;
    }

    @GetMapping
    public ResponseEntity<List<Airliner>> getAllAirliners() {
        List<Airliner> airliners = airlinerService.findAllActive();
        return new ResponseEntity<>(airliners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Airliner getAirlinerById(@PathVariable("id") Long id) {
        return airlinerService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Airliner> addAirliner(@RequestBody @Valid Airliner airliner) {
        Airliner savedAirliner = airlinerRepository.save(airliner);
        return new ResponseEntity<>(savedAirliner, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Airliner updateAirliner(@RequestBody @Valid Airliner airliner, @PathVariable("id") Long id) {
        return airlinerService.updateAirliner(id, airliner);
    }

    @DeleteMapping("/{id}")
    public Airliner deleteAirliner(@PathVariable("id") Long id) {
        return airlinerService.deleteAirliner(id);
    }
}
