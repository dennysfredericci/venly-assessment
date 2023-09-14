package io.venly.assessment.controller;

import io.micrometer.common.util.StringUtils;
import io.venly.assessment.dto.RelationDTO;
import io.venly.assessment.service.RelationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RelationController {

    private final RelationService relationService;

    public RelationController(RelationService relationService) {
        this.relationService = relationService;
    }

    @GetMapping("/v1/relations")
    public List<RelationDTO> get(@RequestParam(required = false) String type) {

        if(StringUtils.isNotBlank(type)) {
            return relationService.listByType(type);
        } else {
            return relationService.listAll();
        }

    }

}
