package io.venly.assessment.repository;

import io.venly.assessment.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Long> {
    WordEntity findByValue(String value);
}
