package io.venly.assessment.entity;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "relation")
public class RelationEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "relation_sequence")
    @SequenceGenerator(name = "relation_sequence", sequenceName = "relation_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private WordEntity word1;

    @Column(nullable = false)
    private String type;
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private WordEntity word2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WordEntity getWord1() {
        return word1;
    }

    public void setWord1(WordEntity word1) {
        this.word1 = word1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WordEntity getWord2() {
        return word2;
    }

    public void setWord2(WordEntity word2) {
        this.word2 = word2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationEntity that = (RelationEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
