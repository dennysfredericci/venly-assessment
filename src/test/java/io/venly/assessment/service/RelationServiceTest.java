package io.venly.assessment.service;

import io.venly.assessment.dto.CreateRelationDTO;
import io.venly.assessment.dto.RelationDTO;
import io.venly.assessment.entity.RelationEntity;
import io.venly.assessment.entity.WordEntity;
import io.venly.assessment.repository.RelationRepository;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@SpringBootTest
class RelationServiceTest {

    @Autowired
    private RelationService relationService;
    @MockBean
    private RelationRepository relationRepository;

    @Captor
    private ArgumentCaptor<RelationEntity> relationEntityArgumentCaptor;

    @Test
    void shouldAcceptOnlyLettersSpace() {
        assertThatThrownBy(() -> relationService.create(new CreateRelationDTO("!@#$", "RELATED", "NORRIS")))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("create.createRelationDTO.word1: Only lowercase letters, uppercase letters, and spaces are allowed");
    }

    @Test
    void shouldSaveWordsAndRelationLowerCaseAndTrimmed() {
        relationService.create(new CreateRelationDTO("CHUCK", "RELATED", "NORRIS"));
        verify(relationRepository).save(relationEntityArgumentCaptor.capture());

        RelationEntity relationEntity = relationEntityArgumentCaptor.getValue();
        assertThat(relationEntity.getWord1().getValue()).isEqualTo("chuck");
        assertThat(relationEntity.getType()).isEqualTo("related");
        assertThat(relationEntity.getWord2().getValue()).isEqualTo("norris");
    }

    @Test
    void shouldCreateRelation() {
        relationService.create(new CreateRelationDTO("chuck", "related", "norris"));
        verify(relationRepository).save(Mockito.any(RelationEntity.class));
    }

    @Test
    void shouldReturnRelationsOfTypeRelated() {
        Mockito.when(relationRepository.findByType("related")).thenReturn(List.of(
                getRelation("road", "related", "street")
        ));

        List<RelationDTO> relationDTOS = relationService.listByType("related");

        assertThat(relationDTOS).hasSize(1);
        assertThat(relationDTOS).extracting("word1", "relation", "word2")
                .containsExactlyInAnyOrder(
                        Tuple.tuple("road", "related", "street"));

    }

    @Test
    void shouldReturnAllRelations() {

        Mockito.when(relationRepository.findAll()).thenReturn(List.of(
                getRelation("road", "related", "street"),
                getRelation("son", "antonym", "daughter"),
                getRelation("good", "synonym", "great")
        ));

        List<RelationDTO> relationDTOS = relationService.listAll();

        assertThat(relationDTOS).hasSize(3);
        assertThat(relationDTOS).extracting("word1", "relation", "word2")
                .containsExactlyInAnyOrder(
                        Tuple.tuple("road", "related", "street"),
                        Tuple.tuple("son", "antonym", "daughter"),
                        Tuple.tuple("good", "synonym", "great"));
    }

    private static RelationEntity getRelation(String word1, String relation, String word2) {
        WordEntity word1Entity = new WordEntity();
        word1Entity.setValue(word1);

        WordEntity word2Entity = new WordEntity();
        word2Entity.setValue(word2);

        RelationEntity relationEntity = new RelationEntity();
        relationEntity.setType(relation);
        relationEntity.setWord1(word1Entity);
        relationEntity.setWord2(word2Entity);
        return relationEntity;
    }

}