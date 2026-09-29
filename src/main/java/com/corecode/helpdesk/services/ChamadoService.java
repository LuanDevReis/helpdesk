package com.corecode.helpdesk.services;

import com.corecode.helpdesk.domain.Chamado;
import com.corecode.helpdesk.domain.Cliente;
import com.corecode.helpdesk.domain.Tecnico;
import com.corecode.helpdesk.domain.dtos.ChamadoCriadoEventDTO;
import com.corecode.helpdesk.domain.dtos.ChamadoDTO;
import com.corecode.helpdesk.domain.enums.Prioridade;
import com.corecode.helpdesk.domain.enums.Status;
import com.corecode.helpdesk.repositories.ChamadoRepository;
import com.corecode.helpdesk.services.exceptions.ObjectnotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private NotificacaoClientService notificacaoClientService;
    @Autowired
    private ChamadoEventPublisher chamadoEventPublisher;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
    }

    public Page<Chamado> findAll(Pageable pageable){

        if (pageable.getPageSize() > 50) {
            pageable = PageRequest.of(
                    pageable.getPageNumber(),
                    50,
                    pageable.getSort()
            );
        }

        return repository.findAll(pageable);

    }

    public Chamado create(@Valid ChamadoDTO objDTO) {
        Chamado chamado = newChamado(objDTO);

        Chamado chamadoSalvo = repository.save(chamado);

        ChamadoCriadoEventDTO event = new ChamadoCriadoEventDTO(
                chamadoSalvo.getId(),
                chamadoSalvo.getTitulo(),
                chamadoSalvo.getCliente().getEmail(),
                "Seu chamado foi criado com sucesso: " + chamadoSalvo.getTitulo()
        );

        chamadoEventPublisher.publicarChamadoCriado(event);

        return chamadoSalvo;
    }

    public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
        objDTO.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDTO);
        return repository.save(oldObj);
    }


    public Chamado newChamado(ChamadoDTO obj){
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if (obj.getId() != null){
            chamado.setId(obj.getId());
        }

        if (obj.getStatus().equals(2)){
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;
    }


}
