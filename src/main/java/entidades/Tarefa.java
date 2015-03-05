/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import Enums.Prioridade;
import Enums.Status;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Joew
 */
@Entity
public class Tarefa {
    
    @Id
    private String id;
    private String nomeCriador;
    private String nomeAmigo;
    private String idAmigo;
    private String descricao;
    private String mensagem;
    
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCriacao;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataLimite;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nomeCriador
     */
    public String getNomeCriador() {
        return nomeCriador;
    }

    /**
     * @param nomeCriador the nomeCriador to set
     */
    public void setNomeCriador(String nomeCriador) {
        this.nomeCriador = nomeCriador;
    }

    /**
     * @return the nomeAmigo
     */
    public String getNomeAmigo() {
        return nomeAmigo;
    }

    /**
     * @param nomeAmigo the nomeAmigo to set
     */
    public void setNomeAmigo(String nomeAmigo) {
        this.nomeAmigo = nomeAmigo;
    }

    /**
     * @return the idAmigo
     */
    public String getIdAmigo() {
        return idAmigo;
    }

    /**
     * @param idAmigo the idAmigo to set
     */
    public void setIdAmigo(String idAmigo) {
        this.idAmigo = idAmigo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the prioridade
     */
    public Prioridade getPrioridade() {
        return prioridade;
    }

    /**
     * @param prioridade the prioridade to set
     */
    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the dataCriacao
     */
    public Date getDataCriacao() {
        return dataCriacao;
    }

    /**
     * @param dataCriacao the dataCriacao to set
     */
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    /**
     * @return the dataLimite
     */
    public Date getDataLimite() {
        return dataLimite;
    }

    /**
     * @param dataLimite the dataLimite to set
     */
    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }
    
    
    
}
