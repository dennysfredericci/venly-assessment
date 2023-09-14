package io.venly.assessment.repository;

import io.venly.assessment.entity.RelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationRepository extends JpaRepository<RelationEntity, Long> {

    List<RelationEntity> findByType(String relationType);

    @Query(value = "select case when count(a)> 0 then true else false end from RelationEntity a where a.type = :relation AND ((a.word1.value = :word1 AND a.word2.value = :word2) OR (a.word1.value = :word2 AND a.word2.value = :word1))")
    boolean alreadyExists(String word1, String relation, String word2);
}
