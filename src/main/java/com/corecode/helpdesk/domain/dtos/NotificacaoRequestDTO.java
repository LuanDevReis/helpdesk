package com.corecode.helpdesk.domain.dtos;

public class NotificacaoRequestDTO {

    private String tipo;
    private String destinatario;
    private String mensagem;

    public NotificacaoRequestDTO() {
    }

    public NotificacaoRequestDTO(String tipo, String destinatario, String mensagem) {
        this.tipo = tipo;
        this.destinatario = destinatario;
        this.mensagem = mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
