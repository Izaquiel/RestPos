/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import entidade.Tarefa;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pos.rest.FaceRest;
import service.ServiceFace.UserFb;

/**
 *
 * @author Izaquiel
 */
@ManagedBean(name = "pag")
@ViewScoped
public class PagView implements Serializable {

    private List<UserFb> user;
    private Tarefa tarefa = new Tarefa();
    private FaceRest servico = new FaceRest();
    private UserFb amigo = new UserFb();
    private UserFb usuario = new UserFb();
    private String token;

    public UserFb getAmigo() {
        return amigo;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public void setAmigo(UserFb amigo) {
        this.amigo = amigo;
    }

    public UserFb getUsuario() {
        return usuario;
    }

    public void setUsuario(UserFb usuario) {
        this.usuario = usuario;
    }

    public UserFb mostraUsuario() {
        usuario = servico.getUsuario();
        return usuario;
    }

    public List<UserFb> getUser() {
        user = servico.getAmigos();
        return user;
    }

    public void salvarTarefa() {
        this.tarefa.setIdCriador(this.usuario.getUid());
        this.tarefa.setCriador(this.getUsuario());
        this.tarefa.setResponsavel(this.amigo);
        this.tarefa.setIdResponsavel(this.amigo.getUid());
        this.tarefa.setDataCriacao(new Date());

        ConsumerRest cons = new ConsumerRest();

    }

    public void publicar() {
        ConsumerRest cons = new ConsumerRest();

        servico.publicarNoFace(token);
        System.out.println("deu certo;");
    }

    public void logar() throws IOException {
        if (servico.logar(token).equalsIgnoreCase("logado"));
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");

    }

}
