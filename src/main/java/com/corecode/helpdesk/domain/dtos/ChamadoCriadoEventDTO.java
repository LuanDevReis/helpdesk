package com.corecode.helpdesk.domain.dtos;

public class ChamadoCriadoEventDTO {

    private Integer chamadoId;
    private String titulo;
    private String clienteEmail;
    private String mensagem;

    public ChamadoCriadoEventDTO() {
    }

    public ChamadoCriadoEventDTO(Integer chamadoId, String titulo, String clienteEmail, String mensagem) {
        this.chamadoId = chamadoId;
        this.titulo = titulo;
        this.clienteEmail = clienteEmail;
        this.mensagem = mensagem;
    }

    public Integer getChamadoId() {
        return chamadoId;
    }

    public void setChamadoId(Integer chamadoId) {
        this.chamadoId = chamadoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
