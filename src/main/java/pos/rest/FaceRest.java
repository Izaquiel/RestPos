/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pos.rest;

import com.restfb.json.JsonObject;
import com.restfb.types.User;
import entidade.Status;
import entidade.Tarefa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import service.ServiceFace;
import service.ServiceFace.UserFb;

/**
 * REST Web Service
 *
 * @author Izaquiel
 */
@Path("fbService")
public class FaceRest implements Serializable{

    ServiceFace sf = new ServiceFace(); 

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
    public UserFb getUsuario(){
        return sf.getUsuario();
    }
    
    @POST
    @Path(value = "publicar")
    @Consumes(value = "application/json")
    public String publicarNoFace(){
        String resp = sf.publicar();
        return resp;
    }
    
    @POST
    @Path(value = "logar")
    @Consumes(value = "application/json")
    public String logar(String token){
        return sf.logar(token);
    }
    
    @POST
    @Path(value = "criarTarefa")
    @Consumes(value = "application/json")
    public String salvar(@PathParam("nome") String nome,@PathParam("status") String status, @PathParam("idCriador")
            String idCriador, @PathParam("dataCriacao") String dataCriacao, @PathParam("dataLimite") String dataLimite,
            @PathParam("dataExecucao") String dataExec, @PathParam("prioridade") String prioridade, @PathParam("idRespon") String idRespon){
        
        Tarefa t = new Tarefa();
        t.getCriador().setUid(idCriador);
        t.setNome(nome);
        t.setIdCriador(idCriador);
        t.setStatus(Status.ABERTO);
        
        
        return null;
        
    }

}
