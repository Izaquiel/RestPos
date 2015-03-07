/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pos.rest;

import entidade.Tarefa;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import service.ServiceFace;
import service.ServiceFace.UserFb;

/**
 * REST Web Service
 *
 * @author Izaquiel
 */
@Path("fbService")
public class FaceRest implements Serializable {
    @EJB
    ServiceFace sf;    
    
    public FaceRest() {
    }
    
    @GET
    @Path(value = "/friends")
    @Produces("application/json")
    public List<UserFb> getAmigos() {
        System.out.println(sf.getAmigos());
        return sf.getAmigos();
        
    }
    
    @GET
    @Path(value = "usuario")
    public UserFb getUsuario() {
        return sf.getUsuario();
    }
    
    @POST
    @Path(value = "publicar")
    @Consumes(value = "application/json")
    public String publicarNoFace() {
        String resp = sf.publicar();
        return resp;
    }
    
    @POST
    @Path(value = "logar")
    @Consumes({"application/x-www-form-urlencoded", "application/json"})
    public String logar(@FormParam(value = "token") String token) {
        return sf.logar(token);
    }
    
    @POST
    @Path(value = "criarTarefa")
    @Consumes({"application/x-www-form-urlencoded", "application/json"})
    public String salvar(@FormParam("nome") String nome, @FormParam("status") String status, 
            @FormParam("descricao") String descricao, @FormParam("idCriador") String idCriador, 
            @FormParam("dataLimite") String dataLimite, @FormParam("prioridade") String prioridade, 
            @FormParam("idRespon") String idRespon) {
        
        Tarefa t = new Tarefa();
        t.setIdCriador(idCriador);
        t.setDescricao(descricao);
        t.setNome(nome);
        t.setStatus(status);
        t.setDataCriacao(new Date());
        t.setIdResponsavel(idRespon);
        t.setPrioridade(prioridade);
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data;
        
        try {
            data = format.parse(dataLimite);
            t.setDataLimiteExecucao(data);
        } catch (ParseException | NullPointerException ex) {
            t.setDataLimiteExecucao(new Date());
        }
        
        System.out.println("Chegou antes do Salvar!");
        
        sf.salvarTarefa(t);
        
        System.out.println("Apos o salvar!");
        
        System.out.println(t);
        
        
        return null;
        
    }
//    
//    @POST
//    @Path(value = "teste")
//    @Consumes({"application/x-www-form-urlencoded","application/json"})
//    public String testar(@FormParam(value = "token") String token){
//        System.out.println(token);
//        return "O Teste deu certo " + token;
//    }


}
