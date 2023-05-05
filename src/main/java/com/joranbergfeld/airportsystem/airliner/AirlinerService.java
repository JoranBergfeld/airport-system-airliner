package com.joranbergfeld.airportsystem.airliner;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlinerService {

    private final AirlinerRepository airlinerRepository;

    public AirlinerService(AirlinerRepository airlinerRepository) {
        this.airlinerRepository = airlinerRepository;
    }

    public List<Airliner> findAllActive() {
        return airlinerRepository.findAllByActiveTrue();
    }

    public Airliner findById(Long id) {
        return airlinerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Airliner not found with id " + id));
    }

    public Airliner storeAirliner(Airliner airliner) {
        return airlinerRepository.save(airliner);
    }

    public Airliner updateAirliner(Long id, Airliner updatedAirliner) {
        return airlinerRepository.findById(id)
                .map(airliner -> {
                    airliner.setName(updatedAirliner.getName());
                    airliner.setCountry(updatedAirliner.getCountry());
                    return airlinerRepository.save(airliner);
                }).orElseThrow(() -> new ResourceNotFoundException("Airliner not found with id " + id));
    }

    public Airliner deleteAirliner(Long id) {
        return airlinerRepository.findById(id)
                .map(airliner -> {
                    airliner.setActive(false);
                    return airlinerRepository.save(airliner);
                }).orElseThrow(() -> new ResourceNotFoundException("Airliner not found with id " + id));
    }
}
