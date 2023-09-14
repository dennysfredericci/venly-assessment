package io.venly.assessment.service;

import io.venly.assessment.dto.CreateRelationDTO;
import jakarta.validation.Valid;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class RelationAlreadyExists extends ErrorResponseException {
    public RelationAlreadyExists(CreateRelationDTO createRelationDTO) {
        super(BAD_REQUEST, getProblemDetail(createRelationDTO), null);
    }


    private static ProblemDetail getProblemDetail(CreateRelationDTO createRelationDTO) {
        return ProblemDetail.forStatusAndDetail(BAD_REQUEST,
                String.format("Relation type '%s' already exists for words %s, %s", createRelationDTO.relation(), createRelationDTO.word1(), createRelationDTO.word2()));
    }
}
