package entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import service.ServiceFace.UserFb;

/**
 *
 * @author Izaquiel
 */
@Entity
@XmlRootElement
public class Tarefa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String descricao;

    @Temporal(TemporalType.DATE)
    private Date dataCriacao;

    @Temporal(TemporalType.DATE)
    private Date dataLimiteExecucao;

    @Temporal(TemporalType.DATE)
    private Date dataExecucao;

    private String prioridade;

    private String status;

    @Transient
    private UserFb responsavel;
    
    @Transient
    private UserFb criador;

    private String idResponsavel;
    private String idCriador;

    public Tarefa() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataLimiteExecucao() {
        return dataLimiteExecucao;
    }

    public void setDataLimiteExecucao(Date dataLimiteExecucao) {
        this.dataLimiteExecucao = dataLimiteExecucao;
    }

    public Date getDataExecucao() {
        return dataExecucao;
    }

    public void setDataExecucao(Date dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

    @XmlTransient

    public UserFb getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(UserFb usuario) {
        this.responsavel = usuario;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient

    public UserFb getCriador() {
        return criador;
    }

    public void setCriador(UserFb criador) {
        this.criador = criador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(String idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public String getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(String idCriador) {
        this.idCriador = idCriador;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Status[] getVetorStatus(){
        return Status.values();
    }
    
    public Prioridade[] getVetorPrioridade(){
        return Prioridade.values();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tarefa other = (Tarefa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tarefa{" + "nome=" + nome + ", descricao=" + descricao + ", dataCriacao=" + dataCriacao + ", dataLimiteExecucao=" + dataLimiteExecucao + ", prioridade=" + prioridade + ", status=" + status + ", idResponsavel=" + idResponsavel + ", idCriador=" + idCriador + '}';
    }
    
    
    
}
