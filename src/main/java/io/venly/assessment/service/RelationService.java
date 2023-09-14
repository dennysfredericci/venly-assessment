package io.venly.assessment.service;

import io.venly.assessment.dto.CreateRelationDTO;
import io.venly.assessment.dto.RelationDTO;
import io.venly.assessment.entity.RelationEntity;
import io.venly.assessment.entity.WordEntity;
import io.venly.assessment.repository.RelationRepository;
import org.apache.commons.lang3.StringUtils;
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

    public void create(CreateRelationDTO createRelationDTO) {

        WordEntity word1 = new WordEntity();
        word1.setValue(trimAndLowerCase(createRelationDTO.word1()));

        WordEntity word2 = new WordEntity();
        word2.setValue(trimAndLowerCase(createRelationDTO.word2()));

        RelationEntity relationEntity = new RelationEntity();
        relationEntity.setType(trimAndLowerCase(createRelationDTO.relation()));
        relationEntity.setWord1(word1);
        relationEntity.setWord2(word2);

        this.relationRepository.save(relationEntity);
    }

    private String trimAndLowerCase(String value) {
        return StringUtils.trim(StringUtils.lowerCase(value));
    }

}
