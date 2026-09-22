package com.corecode.helpdesk.services;

import com.corecode.helpdesk.domain.dtos.NotificacaoRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NotificacaoClientService {

    private final RestClient restClient;

    public NotificacaoClientService(@Value("${notificacao.service.url}") String notificacaoServiceUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(notificacaoServiceUrl)
                .build();
    }

    public void notificarChamadoCriado(String destinatario, String tituloChamado) {
        NotificacaoRequestDTO dto = new NotificacaoRequestDTO(
                "CHAMADO_CRIADO",
                destinatario,
                "Seu chamado foi criado com sucesso: " + tituloChamado
        );

        restClient.post()
                .uri("/notificacoes")
                .body(dto)
                .retrieve()
                .toBodilessEntity();
    }
}
