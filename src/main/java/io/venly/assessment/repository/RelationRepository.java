package io.venly.assessment.repository;

import io.venly.assessment.entity.RelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationRepository extends JpaRepository<RelationEntity, Long> {

    List<RelationEntity> findByType(String relationType);
}
