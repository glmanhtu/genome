package com.ebi.genome.restapi;

import com.ebi.genome.persistence.domain.Taxonomy;
import com.ebi.genome.persistence.dto.TaxonomyDTO;
import com.ebi.genome.service.TaxonomiesService;
import com.ebi.genome.utils.DefaultResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/taxonomy")
public class TaxonomyHandler {

    @Autowired
    private TaxonomiesService taxonomiesService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> findAllTaxonomies() {
        Collection<Taxonomy> taxonomies = taxonomiesService.getTaxonomies();
        List<TaxonomyDTO> taxonomyDTOs = taxonomies.stream().map(this::convertToDTO).collect(Collectors.toList());
        return DefaultResponse.success(taxonomyDTOs);
    }

    private TaxonomyDTO convertToDTO(Taxonomy taxonomy) {
        return modelMapper.map(taxonomy, TaxonomyDTO.class);
    }
}
