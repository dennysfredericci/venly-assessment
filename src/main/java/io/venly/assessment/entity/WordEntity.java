package io.venly.assessment.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "word")
public class WordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "word_sequence")
    @SequenceGenerator(name = "word_sequence", sequenceName = "word_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "word", unique = true)
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordEntity that = (WordEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "WordEntity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
