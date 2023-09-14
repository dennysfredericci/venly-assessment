package io.venly.assessment.repository;

import io.venly.assessment.entity.RelationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RelationRepositoryTest {

    @Autowired
    private RelationRepository relationRepository;

    @Autowired
    private WordRepository wordRepository;

    @Test
    void shouldFindByType() {
        List<RelationEntity> relations = relationRepository.findByType("related");
        assertThat(relations).hasSize(2);
        assertThat(relations).extracting("type").containsOnly("related");
    }

    @Test
    void shouldReturnAll() {
        List<RelationEntity> relations = relationRepository.findAll();
        assertThat(relations).hasSize(5);
    }

    @Test
    void shouldStoreNewRelation() {
        RelationEntity relationEntity = new RelationEntity();
        relationEntity.setWord1(wordRepository.findByValue("good"));
        relationEntity.setWord2(wordRepository.findByValue("bad"));
        relationEntity.setType("antonym");

        RelationEntity saveRelation = relationRepository.save(relationEntity);
        assertThat(saveRelation.getId()).isNotNull();
    }

}