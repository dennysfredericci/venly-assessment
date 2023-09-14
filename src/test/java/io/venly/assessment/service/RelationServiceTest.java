package io.venly.assessment.service;

import io.venly.assessment.dto.RelationDTO;
import io.venly.assessment.entity.RelationEntity;
import io.venly.assessment.entity.WordEntity;
import io.venly.assessment.repository.RelationRepository;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RelationServiceTest {

    @InjectMocks
    private RelationService relationService;
    @Mock
    private RelationRepository relationRepository;

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