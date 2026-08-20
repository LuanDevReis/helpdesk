package com.corecode.helpdesk.services;

import com.corecode.helpdesk.domain.Chamado;
import com.corecode.helpdesk.repositories.ChamadoRepository;
import com.corecode.helpdesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Chamado> findAll() {
        return repository.findAll();

    }
}
