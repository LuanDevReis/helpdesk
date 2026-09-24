package com.corecode.helpdesk.resources;

import com.corecode.helpdesk.domain.Chamado;
import com.corecode.helpdesk.domain.dtos.ChamadoDTO;
import com.corecode.helpdesk.domain.dtos.PageResponse;
import com.corecode.helpdesk.services.ChamadoService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado obj = service.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ChamadoDTO>> findAll(
            @ParameterObject
            @PageableDefault(
                    size = 10,
                    page = 0,
                    sort = "id",
                    direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Chamado> page = service.findAll(pageable);
        Page<ChamadoDTO> pageDTO = page.map(obj -> new ChamadoDTO(obj));
        PageResponse<ChamadoDTO> response = new PageResponse<>(pageDTO);

        return ResponseEntity.ok().body(response);

    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@RequestBody @Valid ChamadoDTO objDTO){
        Chamado obj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO objDTO){
        Chamado newObj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(newObj));

    }

}
