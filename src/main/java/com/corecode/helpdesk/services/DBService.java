package com.corecode.helpdesk.services;


import com.corecode.helpdesk.domain.Chamado;
import com.corecode.helpdesk.domain.Cliente;
import com.corecode.helpdesk.domain.Tecnico;
import com.corecode.helpdesk.domain.enums.Perfil;
import com.corecode.helpdesk.domain.enums.Prioridade;
import com.corecode.helpdesk.domain.enums.Status;
import com.corecode.helpdesk.repositories.ChamadoRepository;
import com.corecode.helpdesk.repositories.ClienteRepository;
import com.corecode.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB(){


        Tecnico tec1 = new Tecnico(
                null,
                "Valdir Cezar",
                "126.963.070-91",
                "valdir@email.com",
                encoder.encode("123")
        );

// O construtor já adiciona TECNICO.
// Aqui adicionamos somente o perfil extra de ADMIN.
        tec1.addPerfis(Perfil.ADMIN);


        Cliente cli1 = new Cliente(
                null,
                "Linux Torvalds",
                "236.701.640-22",
                "torvalds@mail.com",
                encoder.encode("123")
        );

        Cliente cli2 = new Cliente(
                null,
                "Ana Paula",
                "509.876.280-46",
                "ana@mail.com",
                encoder.encode("123")
        );


        Chamado c1 = new Chamado(
                null,
                Prioridade.MEDIA,
                Status.ANDAMENTO,
                "Chamado 01",
                "Primeiro chamado",
                tec1,
                cli1
        );

        Chamado c2 = new Chamado(
                null,
                Prioridade.ALTA,
                Status.ABERTO,
                "Chamado 02",
                "Computador não liga",
                tec1,
                cli2
        );


        tecnicoRepository.save(tec1);

        clienteRepository.saveAll(Arrays.asList(
                cli1,
                cli2
        ));

        chamadoRepository.saveAll(Arrays.asList(
                c1,
                c2
        ));

    }
}
