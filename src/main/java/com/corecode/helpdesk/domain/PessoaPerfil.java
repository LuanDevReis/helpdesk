package com.corecode.helpdesk.domain;

import com.corecode.helpdesk.domain.enums.Perfil;
import jakarta.persistence.*;

@Entity
@Table(
        name = "perfis",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"pessoa_id", "perfil"})
        }
)
public class PessoaPerfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @Column(name = "perfil", nullable = false)
    private Integer perfil;

    public PessoaPerfil() {
    }

    public PessoaPerfil(Pessoa pessoa, Perfil perfil) {
        this.pessoa = pessoa;
        this.perfil = perfil.getCodigo();
    }

    public Integer getId() {
        return id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Perfil getPerfil() {
        return Perfil.toEnum(perfil);
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil.getCodigo();
    }
}