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

    public void instanciaDB(){

        System.out.println(">>> ENTROU NO INSTANCIA DB <<<");


        Tecnico tec1 = new Tecnico(null, "Valdir cezar", "63653230268", "valdir@email.com", "123");
        tec1.addPerfis(Perfil.ADMIN);
        Tecnico tec2 = new Tecnico(null, "Carlos Eduardo", "12345678901", "carlos@email.com", "123");
        tec2.addPerfis(Perfil.TECNICO);

        Tecnico tec3 = new Tecnico(null, "Marcos Silva", "23456789012", "marcos@email.com", "123");
        tec3.addPerfis(Perfil.TECNICO);

        Tecnico tec4 = new Tecnico(null, "Fernanda Souza", "34567890123", "fernanda@email.com", "123");
        tec4.addPerfis(Perfil.TECNICO);

        Tecnico tec5 = new Tecnico(null, "Rafael Oliveira", "45678901234", "rafael@email.com", "123");
        tec5.addPerfis(Perfil.TECNICO);

        Tecnico tec6 = new Tecnico(null, "Juliana Costa", "56789012345", "juliana@email.com", "123");
        tec6.addPerfis(Perfil.TECNICO);

        Cliente cli1 = new Cliente(null, "Linux Torvalds", "80527954780", "torvalds@mail.com", "123");
        Cliente cli2 = new Cliente(null, "Ana Paula", "12345678909", "ana@mail.com", "123");

        Cliente cli3 = new Cliente(null, "Joao Santos", "23456789010", "joao@mail.com", "123");

        Cliente cli4 = new Cliente(null, "Maria Oliveira", "34567890121", "maria@mail.com", "123");

        Cliente cli5 = new Cliente(null, "Pedro Henrique", "45678901232", "pedro@mail.com", "123");

        Cliente cli6 = new Cliente(null, "Camila Ferreira", "56789012343", "camila@mail.com", "123");

        Chamado c1 = new Chamado(null,
                Prioridade.MEDIA,
                Status.ANDAMENTO,
                "Chamado 01",
                "Primeiro chamado",
                tec1,
                cli1);

        Chamado c2 = new Chamado(
                null,
                Prioridade.ALTA,
                Status.ABERTO,
                "Chamado 02",
                "Computador não liga",
                tec2,
                cli2
        );

        Chamado c3 = new Chamado(
                null,
                Prioridade.BAIXA,
                Status.ENCERRADO,
                "Chamado 03",
                "Solicitação de instalação de software",
                tec3,
                cli3
        );

        Chamado c4 = new Chamado(
                null,
                Prioridade.ALTA,
                Status.ANDAMENTO,
                "Chamado 04",
                "Problema de conexão com a internet",
                tec4,
                cli4
        );

        Chamado c5 = new Chamado(
                null,
                Prioridade.MEDIA,
                Status.ABERTO,
                "Chamado 05",
                "Impressora não está funcionando",
                tec5,
                cli5
        );

        Chamado c6 = new Chamado(
                null,
                Prioridade.BAIXA,
                Status.ENCERRADO,
                "Chamado 06",
                "Solicitação de troca de senha",
                tec6,
                cli6
        );


        tecnicoRepository.saveAll(Arrays.asList(
                tec1,
                tec2,
                tec3,
                tec4,
                tec5,
                tec6
        ));

        clienteRepository.saveAll(Arrays.asList(
                cli1,
                cli2,
                cli3,
                cli4,
                cli5,
                cli6
        ));

        chamadoRepository.saveAll(Arrays.asList(
                c1,
                c2,
                c3,
                c4,
                c5,
                c6
        ));
    }
}
