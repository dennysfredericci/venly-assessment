package io.venly.assessment.service;

import io.venly.assessment.dto.RelationDTO;
import io.venly.assessment.entity.RelationEntity;
import io.venly.assessment.repository.RelationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelationService {

    private final RelationRepository relationRepository;

    public RelationService(RelationRepository relationRepository) {
        this.relationRepository = relationRepository;
    }

    public List<RelationDTO> listAll() {
        return this.relationRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<RelationDTO> listByType(String relationType) {
        return this.relationRepository.findByType(relationType).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private RelationDTO toDTO(RelationEntity relationEntity) {
        return new RelationDTO(
                relationEntity.getWord1().getValue(),
                relationEntity.getType(),
                relationEntity.getWord2().getValue()
        );
    }

}
