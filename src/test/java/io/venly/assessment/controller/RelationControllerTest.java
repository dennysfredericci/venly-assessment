package io.venly.assessment.controller;

import io.venly.assessment.dto.CreateRelationDTO;
import io.venly.assessment.dto.RelationDTO;
import io.venly.assessment.service.RelationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RelationController.class)
class RelationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RelationService relationService;

    @Test
    void shouldCreateRelation() throws Exception {

        mockMvc.perform(post("/v1/relations")
                .contentType("application/json")
                .content("{\"word1\":\"chuck\",\"relation\":\"related\",\"word2\":\"norris\"}")
        ).andExpect(status().is2xxSuccessful());

        verify(relationService).create(Mockito.any(CreateRelationDTO.class));

    }

    @Test
    void shouldReturnRelationsOfTypeRelated() throws Exception {

        when(relationService.listByType("related")).thenReturn(getRelatedRelations());

        mockMvc.perform(get("/v1/relations?type=related")
                        .contentType("application/json"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("[{\"word1\":\"road\",\"relation\":\"related\",\"word2\":\"street\"},{\"word1\":\"synonym\",\"relation\":\"related\",\"word2\":\"match\"}]"));
    }

    @Test
    void shouldReturnAllRelations() throws Exception {

        when(relationService.listAll()).thenReturn(getRelations());

        mockMvc.perform(get("/v1/relations")
                        .contentType("application/json"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("[{\"word1\":\"road\",\"relation\":\"related\",\"word2\":\"street\"},{\"word1\":\"son\",\"relation\":\"antonym\",\"word2\":\"daughter\"},{\"word1\":\"good\",\"relation\":\"synonym\",\"word2\":\"great\"}]"));
    }

    private List<RelationDTO> getRelations() {
        return List.of(
                getRelation("road", "related", "street"),
                getRelation("son", "antonym", "daughter"),
                getRelation("good", "synonym", "great")
        );
    }

    private List<RelationDTO> getRelatedRelations() {
        return List.of(
                getRelation("road", "related", "street"),
                getRelation("synonym", "related", "match")
        );
    }

    private RelationDTO getRelation(String word1, String relationType, String word2) {
        return new RelationDTO(
                word1,
                relationType,
                word2
        );
    }

}