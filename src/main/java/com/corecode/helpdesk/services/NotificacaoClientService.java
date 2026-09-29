package com.corecode.helpdesk.services;

import com.corecode.helpdesk.domain.dtos.NotificacaoRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class NotificacaoClientService {

    private static final Logger logger = LoggerFactory.getLogger(NotificacaoClientService.class);

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

        try {
            restClient.post()
                    .uri("/notificacoes")
                    .body(dto)
                    .retrieve()
                    .toBodilessEntity();

            logger.info("Notificação de chamado criado enviada com sucesso para {}", destinatario);

        } catch (RestClientException ex) {
            logger.warn(
                    "Falha ao enviar notificação de chamado criado para {}. Motivo: {}",
                    destinatario,
                    ex.getMessage()
            );
        }
    }

}
